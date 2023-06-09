package gui.component;

import gui.component.util.ColorGenerator;
import gui.component.util.RoutePoint;
import gui.controller.main.callback.GetCollectionFromModelCallback;
import gui.controller.visualization.VisualizationAnimator;
import gui.controller.visualization.callback.PrintObjInfoCallback;
import gui.controller.visualization.component.VisualizationCollectionUpdatedController;
import gui.controller.visualization.component.VisualizationComponentProvider;
import gui.controller.visualization.component.VisualizationMouseHandler;
import gui.controller.visualization.component.callback.VisualisationCallback;
import model.Location;
import model.Route;
import model.collection.actions.AddCollectionAction;
import model.collection.actions.RemoveCollectionAction;
import model.collection.actions.UpdateCollectionAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ImageUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class VisualisationComponent extends JComponent implements VisualisationCallback, VisualizationComponentProvider {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final Dimension preferredDim;
    private final HashSet<Route> collection;
    private final HashMap<Long, Long> ownership;
    private final ArrayList<RoutePoint> routePoints;
    private final Image image;
    public static final int UPDATES_COUNT = 100;
    private double scaleX;
    private double scaleY;

    private final double centerX;
    private final double centerY;

    public VisualisationComponent(GetCollectionFromModelCallback callback, PrintObjInfoCallback windowCallback) {
        this.collection = callback.getCollection();
        this.ownership = callback.getOwnership();
        this.routePoints = new ArrayList<>();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        var preferResize = new Dimension((int) (dimension.width / 1.1), (int) (dimension.height / 1.1));

        BufferedImage img = null;
        try {
            img = ImageIO.read
                    (Objects.requireNonNull(this.getClass().getResourceAsStream("/Biome_Map.png")));
        } catch (IOException e) {
            logger.error(e);
        }

        assert img != null;
        Dimension resizedImageDim = ImageUtilities.getScaledDimension(new Dimension(img.getWidth(), img.getHeight()),
                preferResize);

        this.image = img.getScaledInstance(resizedImageDim.width, resizedImageDim.height, 0);

        this.preferredDim = resizedImageDim;

        this.centerX = preferredDim.width / 2d;
        this.centerY = preferredDim.height / 2d;

        scaleX = (preferredDim.width / 2d) / maxXFromCollection();
        scaleY = (preferredDim.height / 2d) / maxYFromCollection();

        logger.info(collection.size());

        fillCircles();

        new VisualizationCollectionUpdatedController(this);

        this.addMouseListener(new VisualizationMouseHandler(this, windowCallback));
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;

        //logger.info(collection.size());

        // scale = max((width / 2) / maxXFromCollection(), (height / 2) / maxYFromCollection())
        // by x and y

        var lastScaleX = scaleX;
        var lastScaleY = scaleY;

        scaleX = (preferredDim.width / 2d) / maxXFromCollection();
        scaleY = (preferredDim.height / 2d) / maxYFromCollection();

        if (lastScaleX != scaleX || lastScaleY != scaleY)
            fillCircles();

        //logger.trace(maxXFromCollection());
        //logger.trace(maxYFromCollection());
        logger.trace(scaleX);
        logger.trace(scaleY);

        g2.drawImage(image, 0, 0, (img1, infoflags, x, y, width, height) -> false);

        // draw axes
        drawArrow(g2, 0, preferredDim.height / 2d, preferredDim.width, preferredDim.height / 2d);
        drawArrow(g2, preferredDim.width / 2d, preferredDim.height, preferredDim.width / 2d, 0);

        // draw single elem
        var line1 = new Line2D.Double(3d / 2 * centerX, centerY - 10, 3d / 2 * centerX, centerY + 10);
        var line2 = new Line2D.Double(centerX - 10, 1d / 2 * centerY, centerX + 10, 1d / 2 * centerY);

        g2.drawString(String.valueOf(preferredDim.width / scaleX / 4), (int) (3f / 2 * centerX), (int) (centerY - 10));
        g2.drawString(String.valueOf(preferredDim.height / scaleY / 4), (int) (centerX + 10), (int) (1d / 2 * centerY));

        g2.draw(line1);
        g2.draw(line2);

        for (RoutePoint point : routePoints) {
            var connectLine = new Line2D.Double(point.getRouteFrom().getCenterX(), point.getRouteFrom().getCenterY(), point.getRouteTo().getCenterX(), point.getRouteTo().getCenterY());
            paintFillByOwner(g2, Optional.ofNullable(ownership.get(point.getRoute().getId())).orElse(1L), point.getRouteFrom(), point.getRouteTo(), connectLine);
        }

        super.paintComponent(g2);
    }

    private void fillCircles() {
        routePoints.clear();
        for (Route route : collection) {
            addSingleCircle(route);
        }
    }

    private void paintFillByOwner(Graphics2D g2, long id, Ellipse2D circleFrom, Ellipse2D circleTo, Line2D connectLine) {
        g2.setPaint(ColorGenerator.getColor(id));
        g2.draw(circleFrom);
        g2.draw(circleTo);
        g2.fill(circleFrom);
        g2.fill(circleTo);
        g2.draw(connectLine);
    }

    private double maxXFromCollection() {
        return Math.max(collection.stream().map(x -> Math.abs(Optional.ofNullable(x.getFrom()).orElse(new Location()).getX())).max(Float::compareTo).orElse(0f),
                collection.stream().map(x -> Math.abs(Optional.ofNullable(x.getTo()).orElse(new Location()).getX())).max(Float::compareTo).orElse(0f));
    }

    private void drawArrow(Graphics g1, double x1, double y1, double x2, double y2) {
        Graphics2D ga = (Graphics2D) g1.create();
        ga.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

        double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));//  line length
        double d = l / 50; // arrowhead distance from end of line. you can use your own value.

        double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from end of the line.
        double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from end of the line.

        double dx = x2 - x1, dy = y2 - y1;
        double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter clockwise)
        angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand what we need to do.
        if (angle < 0) {
            angle = 360 + angle; // convert negative degrees to posative degree
        }
        angle = (-1) * angle; // convert to counter clockwise mode
        angle = Math.toRadians(angle);//  convert Degree to Radians
        AffineTransform at = new AffineTransform();
        at.translate(newX, newY);// transport cursor to draw arrowhead position.
        at.rotate(angle);
        ga.transform(at);

        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(5, 0);
        arrowHead.addPoint(-5, 5);
        arrowHead.addPoint(-2, -0);
        arrowHead.addPoint(-5, -5);
        ga.fill(arrowHead);
        ga.drawPolygon(arrowHead);
    }

    private double maxYFromCollection() {
        Location zeroLoc = new Location();
        zeroLoc.setY(0L);
        return Math.max(collection.stream().map(x -> Math.abs(Optional.ofNullable(x.getFrom()).orElse(zeroLoc).getY())).max(Long::compareTo).orElse(0L),
                collection.stream().map(x -> Math.abs(Optional.ofNullable(x.getTo()).orElse(zeroLoc).getY())).max(Long::compareTo).orElse(0L));
    }

    private Ellipse2D getEllipseFromCenter(double x, double y, double width, double height) {
        double newX = x - width / 2.0;
        double newY = y - height / 2.0;

        return new Ellipse2D.Double(newX, newY, width, height);
    }

    private double getCenterX(double x) {
        logger.trace("Calculated x " + (x * scaleX + centerX));
        return x * scaleX + centerX;
    }

    private double getCenterY(double y) {
        logger.trace("Calculated y " + (centerY - y * scaleY));
        return centerY - y * scaleY;
    }

    @Override
    public void acceptException(Exception e) {
        logger.error(e);
    }

    @Override
    public void fireNewRoutes(AddCollectionAction action) {
        collection.addAll(action.getNewElements());
        logger.info("visual repaint?");
        this.repaint();
    }

    @Override
    public void fireUpdateRoutes(UpdateCollectionAction action) {
        logger.info("FIRE UPDATE ROUTE!!");
        logger.info(action.getUpdatedFiled() + " " + action.getUpdatedValue());
        Route routeToEdit = collection.stream()
                .filter(x -> x.getId().equals(action.getElementId()))
                .findAny().orElse(new Route());
        String objStr = String.valueOf(action.getUpdatedValue());
        VisualizationAnimator animator = new VisualizationAnimator(this);
        switch (action.getUpdatedFiled()) {
            case FROM_X -> {
                float startX = routeToEdit.getFrom().getX();
                float endX = Float.parseFloat(objStr);
                float fade = (endX - startX) / UPDATES_COUNT;
                animator.animate(() -> {
                    routePoints.removeIf(x -> x.getRoute().equals(routeToEdit));
                    routeToEdit.getFrom().setX(routeToEdit.getFrom().getX() + fade);
                    addSingleCircle(routeToEdit);
                }, UPDATES_COUNT);
            }
            case FROM_Y -> {
                long startY = routeToEdit.getFrom().getY();
                long endY = Long.parseLong(objStr);
                long fade = (endY - startY) / UPDATES_COUNT;
                animator.animate(() -> {
                    routePoints.removeIf(x -> x.getRoute().equals(routeToEdit));
                    routeToEdit.getFrom().setY(routeToEdit.getFrom().getY() + fade);
                    addSingleCircle(routeToEdit);
                }, UPDATES_COUNT);
            }
            case TO_X -> {
                float startX = routeToEdit.getTo().getX();
                float endX = Float.parseFloat(objStr);
                float fade = (endX - startX) / UPDATES_COUNT;
                animator.animate(() -> {
                    routePoints.removeIf(x -> x.getRoute().equals(routeToEdit));
                    routeToEdit.getTo().setX(routeToEdit.getTo().getX() + fade);
                    addSingleCircle(routeToEdit);
                }, UPDATES_COUNT);
            }
            case TO_Y -> {
                long startY = routeToEdit.getTo().getY();
                long endY = Long.parseLong(objStr);
                long fade = (endY - startY) / UPDATES_COUNT;
                animator.animate(() -> {
                    routePoints.removeIf(x -> x.getRoute().equals(routeToEdit));
                    routeToEdit.getTo().setY(routeToEdit.getTo().getY() + fade);
                    addSingleCircle(routeToEdit);
                }, UPDATES_COUNT);
            }
        }
    }

    private void addSingleCircle(Route route) {

        Location from = route.getFrom();
        Location to = route.getTo();

        if (from == null || to == null) return;

        double fromX = getCenterX(from.getX());
        double fromY = getCenterY(from.getY());
        double toX = getCenterX(to.getX());
        double toY = getCenterY(to.getY());

        double radius = 15;

        var circleFrom = getEllipseFromCenter(fromX, fromY, radius, radius);
        var circleTo = getEllipseFromCenter(toX, toY, radius, radius);

        routePoints.add(new RoutePoint(route, circleTo, circleFrom));
    }

    @Override
    public void fireRemoveRoutes(RemoveCollectionAction action) {
        Arrays.stream(action.getRemoved_ids()).forEach(id -> collection.removeIf(x -> x.getId().equals(id)));
        logger.info("visual repaint?");
        fillCircles();
        this.repaint();
    }

    public RoutePoint find(Point2D point2D) {
        for (var point : routePoints) {
            if (point.getRouteFrom().contains(point2D) || point.getRouteTo().contains(point2D)) return point;
        }
        return null;
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredDim;
    }
}
