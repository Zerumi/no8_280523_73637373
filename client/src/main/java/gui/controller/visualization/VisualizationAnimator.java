package gui.controller.visualization;

import gui.component.VisualisationComponent;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VisualizationAnimator {

    private final VisualisationComponent component;

    public VisualizationAnimator(VisualisationComponent component) {
        this.component = component;
    }

    public synchronized void animate(VisualAnimation animation, int ticks) {
        AtomicInteger tickCount = new AtomicInteger();
        Timer timer = new Timer(100, (e) -> {
            if (tickCount.incrementAndGet() == ticks)
                ((Timer) e.getSource()).stop();
            EventQueue.invokeLater(() -> {
                animation.animate();
                component.repaint();
            });
        });
        timer.start();
    }

}
