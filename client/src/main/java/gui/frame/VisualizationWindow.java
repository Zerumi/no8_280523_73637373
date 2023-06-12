package gui.frame;


import utils.ImageUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class VisualizationWindow extends JFrame {
    public VisualizationWindow() throws IOException {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        JPanel centerPanel = new JPanel();

        BufferedImage img = ImageIO.read
                (Objects.requireNonNull(this.getClass().getResourceAsStream("/Biome_Map.png")));

        Dimension resizedImageDim = ImageUtilities.getScaledDimension(new Dimension(img.getWidth(), img.getHeight()),
                new Dimension((int) (dimension.width / 1.1), (int) (dimension.height / 1.1)));

        Image image = img.getScaledInstance(resizedImageDim.width, resizedImageDim.height, 0);
        ImageIcon imageIcon = new ImageIcon(image);

        JLabel label = new JLabel(imageIcon);

        centerPanel.add(label);

        this.add(centerPanel);
        this.setLocationByPlatform(true);
        this.pack();
    }
}
