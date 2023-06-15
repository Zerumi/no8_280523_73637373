package gui.component;

import gui.controller.main.callback.GetCollectionFromModelCallback;
import gui.controller.visualization.component.VisualizationCollectionUpdatedController;
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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

public class VisualisationComponent extends JComponent implements VisualisationCallback {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final Dimension preferredDim;
    private final HashSet<Route> collection;
    private final Image image;
    private final double scale;

    private final double centerX;
    private final double centerY;

    public VisualisationComponent(GetCollectionFromModelCallback callback) {
        this.collection = callback.getCollection();

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


        // scale = max((width / 2) / maxXFromCollection(), (height / 2) / maxYFromCollection())

        scale = Math.min((preferredDim.width / 2d) / maxXFromCollection(), (preferredDim.height / 2d) / maxYFromCollection());

        logger.info(maxXFromCollection());
        logger.info(maxYFromCollection());
        logger.info(scale);

        new VisualizationCollectionUpdatedController(this);
    }

    private double maxXFromCollection() {
        return Math.max(collection.stream().map(x -> Optional.ofNullable(x.getFrom()).orElse(new Location()).getX()).max(Float::compareTo).orElse(0f),
                collection.stream().map(x -> Optional.ofNullable(x.getTo()).orElse(new Location()).getX()).max(Float::compareTo).orElse(0f));
    }

    private double maxYFromCollection() {
        Location zeroLoc = new Location();
        zeroLoc.setY(0L);
        return Math.max(collection.stream().map(x -> Optional.ofNullable(x.getFrom()).orElse(zeroLoc).getY()).max(Long::compareTo).orElse(0L),
                collection.stream().map(x -> Optional.ofNullable(x.getTo()).orElse(zeroLoc).getY()).max(Long::compareTo).orElse(0L));
    }

    @Override
    protected void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;

        g2.drawImage(image, 0, 0, (img1, infoflags, x, y, width, height) -> false);

        // draw axes
        var xAxe = new Line2D.Double(0, preferredDim.height / 2d, preferredDim.width, preferredDim.height / 2d);
        var yAxe = new Line2D.Double(preferredDim.width / 2d, 0, preferredDim.width / 2d, preferredDim.height);

        g2.draw(xAxe);
        g2.draw(yAxe);

        for (Route route : collection) {

            Location from = route.getFrom();
            Location to = route.getTo();

            if (from == null || to == null) continue;

            double fromX = getCenterX(from.getX());
            double fromY = getCenterY(from.getY());
            double toX = getCenterX(to.getX());
            double toY = getCenterY(to.getY());

            double radius = 15;

            var circleFrom = getEllipseFromCenter(fromX, fromY, radius, radius);
            var circleTo = getEllipseFromCenter(toX, toY, radius, radius);

            var connectLine = new Line2D.Double(circleFrom.getCenterX(), circleFrom.getCenterY(), circleTo.getCenterX(), circleTo.getCenterY());

            logger.trace("Drawing from (" + fromX + ";" + fromY + ") to (" + toX + ";" + toY + ").");

            g2.draw(circleFrom);
            g2.draw(circleTo);
            g2.draw(connectLine);

            g2.setPaint(Color.GREEN);
            g2.fill(circleFrom);

            g2.setPaint(Color.BLUE);
            g2.fill(circleTo);
        }

        super.paintComponent(g2);
    }

    private Ellipse2D getEllipseFromCenter(double x, double y, double width, double height) {
        double newX = x - width / 2.0;
        double newY = y - height / 2.0;

        return new Ellipse2D.Double(newX, newY, width, height);
    }

    private double getCenterX(double x) {
        logger.trace("Calculated x " + (x * scale + centerX));
        return x * scale + centerX;
    }

    private double getCenterY(double y) {
        logger.trace("Calculated y " + (centerY - y * scale));
        return centerY - y * scale;
    }

    @Override
    public void acceptException(Exception e) {
        logger.error(e);
    }

    @Override
    public void fireNewRoutes(AddCollectionAction action) {
        collection.addAll(action.getNewElements());
        logger.info("vizual repaint?");
        this.repaint();
    }

    @Override
    public void fireUpdateRoutes(UpdateCollectionAction action) {
        // run animation
    }

    @Override
    public void fireRemoveRoutes(RemoveCollectionAction action) {
        Arrays.stream(action.getRemoved_ids()).forEach(id -> collection.removeIf(x -> x.getId().equals(id)));
        logger.info("vizual repaint?");
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredDim;
    }
}
