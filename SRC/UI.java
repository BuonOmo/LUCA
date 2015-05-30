import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class UI extends JFrame {

    Timer timer;
    static long time;
    BufferedImage ArrierePlan;
    Graphics buffer;
    Rectangle Ecran;
    Mouse mouse;
    Key key;

    Canvas canvas;
    SideBand panelBandeau;

    JFrame frame;

    public UI() {
        canvas = new Canvas();
        mouse = new Mouse(canvas.P1);
        key = new Key(canvas.P1);
        frame = new JFrame();
        //frame.addMouseListener(mouse);
        //frame.addKeyListener(key);
        //frame.addMouseMotionListener(mouse);
        
        // Plein ecran
        frame.setUndecorated(true);
        GraphicsEnvironment gE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gE.getDefaultScreenDevice().setFullScreenWindow(frame);

        /*
        //Location and size of the frame
        frame.setSize(Finals.screenWidth, Finals.screenHeight); //Wide screen
        frame.setResizable(false);
        frame.setLocation(0,0);
        */

        //Set the background on YELLOW : if you see stg yellow it means the jpanel ain't working
        frame.getContentPane().setBackground(Color.YELLOW);

        panelBandeau = new SideBand();
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(canvas);
        frame.getContentPane().add(panelBandeau);
        
        //JFrame properties
        frame.setTitle("LUCA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        timer = new Timer(50, new TimerAction());
    }


    private class TimerAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Game.run();

            //Scrolling
            Point mouse = MouseInfo.getPointerInfo().getLocation();

            if (mouse.x <= Finals.SCROLL_BORDER + 50) {
                canvas.cam.moveCamera(-Finals.CAMERA_SPEED, 0); //scroll à gauche
            } else if (mouse.x >= Finals.screenWidth * 5 / 6 - Finals.SCROLL_BORDER) {
                canvas.cam.moveCamera(Finals.CAMERA_SPEED, 0); //scroll à droite
            }
            if (mouse.y <= Finals.SCROLL_BORDER) {
                canvas.cam.moveCamera(0, -Finals.CAMERA_SPEED); //scroll en haut
            } else if (mouse.y >= Finals.screenHeight - Finals.SCROLL_BORDER) {
                canvas.cam.moveCamera(0, Finals.CAMERA_SPEED); //scroll en bas
            }

            canvas.repaint();
            panelBandeau.repaint();
            time++;
        }
    }

    public static void main(String[] args) {
        UI gui = new UI();
        gui.timer.start();
    }

}
