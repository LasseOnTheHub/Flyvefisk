package com.CDIO;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.video.ImageListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by lasse on 3/22/17.
 */
public class TutorialVideoListener extends JFrame
{
    private BufferedImage image = null;

    public TutorialVideoListener(final IARDrone drone)
    {
        super("YADrone Tutorial");

        setSize(1280,720);
        setVisible(true);
        getVideoFeed(drone);
    }

    public void paint(Graphics g)
    {
        if (image != null)
            g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    private void getVideoFeed(IARDrone drone)
    {

        drone.getVideoManager().addImageListener(new ImageListener() {
            public void imageUpdated(BufferedImage newImage)
            {
                image = newImage;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run()
                    {
                        repaint();
                    }
                });
            }
        });
    }
}