package com.mycodefu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class StarlightSkyMain extends JPanel implements KeyListener {

    public static void main(String[] args) {
        JFrame jframe = new JFrame();

        jframe.add(new StarlightSkyMain());
        jframe.pack();

        jframe.setVisible(true);
        jframe.getDefaultCloseOperation();
    }

    public StarlightSkyMain() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawStars(g);
    }

    private void drawStars(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(Color.WHITE);

        Random random = new Random();
        int numberOfStars = random.nextInt(500) + 500;
        for( int starNo = 0; starNo < numberOfStars; starNo++) {
            int diameter = random.nextInt(5) + 1;
            int starX = random.nextInt(this.getWidth() - 15);
            int starY = random.nextInt(this.getHeight() - 15);


            g.fillOval(starX, starY, diameter, diameter);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            drawStars(this.getGraphics());
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            saveScreenshot();
        }
    }

    private void saveScreenshot() {
        try {
            Point topLeft = new Point(0, 0);
            SwingUtilities.convertPointToScreen(topLeft, this);
            Rectangle bounds = this.getBounds();
            bounds.setLocation(topLeft);

            BufferedImage capture = new Robot().createScreenCapture(bounds);
            ImageIO.write(capture, "png", new File("sky.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
