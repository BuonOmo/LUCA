import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class OptionsFrame extends JFrame {

	JFrame optFrame;
	int optFrameHeight;
	int optFrameWidth;
	private JButton saveChanges;
	private JButton cancel;
	private JLabel colorPlayerLabel;
	private JLabel colorIALabel;
	private JComboBox colorPlayerChoice;
	private JComboBox colorIAChoice;
	private JCheckBox music;
	private JCheckBox fog;
	
	public static String colorPlayer;
	public static String colorIA;
	
	
	public OptionsFrame() {

		optFrame = new JFrame();
		optFrame.setSize(Finals.screenWidth * 3 / 4, Finals.screenHeight * 3 / 4);
		optFrame.setLocation(Finals.screenWidth / 8, Finals.screenHeight / 8);
		
		
		//Initializing the colors
    	colorPlayer = "blue";
    	colorIA = "orange";
    	
    	
		//get the size of the created frame
		Rectangle r = optFrame.getBounds();
		optFrameHeight = r.height;
    	optFrameWidth = r.width;
    	
    	
    	//put the JPanel on (null) in order to locate the buttons wherever we want
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	
    	
    	//Initializing the components
    	String[] colors = {"blue", "green", "orange", "pink"};
    	this.colorPlayerChoice = new JComboBox(colors);
    	this.colorIAChoice = new JComboBox(colors);
    	this.colorPlayerLabel = new JLabel("Player's Color :");
    	this.colorIALabel = new JLabel("IA's Color :");
    	this.saveChanges = new JButton("Save Changes");
    	this.cancel = new JButton("Cancel");
    	
    	//Bounds of the components
    	Dimension size = new Dimension(200, 40);
    	colorPlayerChoice.setBounds(optFrameWidth *7/12 - size.width /2, optFrameHeight * 4/12 - size.height /2, size.width, size.height);
    	colorIAChoice.setBounds(optFrameWidth *7/12 - size.width /2, optFrameHeight * 6/12 - size.height /2, size.width, size.height);
    	colorPlayerLabel.setBounds(optFrameWidth *5/12 - size.width /2, optFrameHeight * 4/12 - size.height /2, size.width, size.height);
    	colorIALabel.setBounds(optFrameWidth *5/12 - size.width /2, optFrameHeight * 6/12 - size.height /2, size.width, size.height);
    	saveChanges.setBounds(optFrameWidth *5/12 - size.width /2, optFrameHeight * 10/12 - size.height /2, 150, size.height);
    	cancel.setBounds(optFrameWidth *7/12 - size.width /2, optFrameHeight * 10/12 - size.height /2, 150, size.height);
    	
    	
    	//Adding the components to the frame
    	panel.add(colorPlayerChoice);
    	panel.add(colorIAChoice);
    	panel.add(colorPlayerLabel);
    	panel.add(colorIALabel);
    	panel.add(saveChanges);
    	panel.add(cancel);
    	optFrame.add(panel);
    	
    	
    	//Action Listeners of the components
    	ActionListener colorPlayerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorPlayer = (String) colorPlayerChoice.getSelectedItem();
            }
        };
        
    	ActionListener colorIAListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                colorIA = (String) colorIAChoice.getSelectedItem();
            }
        };
        
        ActionListener savingChanges = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String []s = {colorPlayer, colorIA};
        		Game.setOptions(s);
        	}
        };
        
        ActionListener cancelling = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//reinitialize options
        	}
        };
        
        colorPlayerChoice.addActionListener(colorPlayerListener);
        colorIAChoice.addActionListener(colorIAListener);
        saveChanges.addActionListener(savingChanges);
        cancel.addActionListener(cancelling);
        
        
    	//JFrame properties
    	optFrame.setTitle("OPTIONS");
    	optFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	optFrame.setVisible(true);
    	
    	
	}

}
