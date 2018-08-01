package ca.utoronto.utm.paint;
import java.awt.Desktop;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.io.*;

/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private PaintModel model;

	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;
	KeyStroke keyStrokeToUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke keyStrokeToRedo = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke keyStrokeToCut = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke keyStrokeToCopy = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
	KeyStroke keyStrokeToPaste = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);

	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		this.paintPanel = new PaintPanel(model, this);


		Container c=this.getContentPane();
		// c.add(new JButton("North"),BorderLayout.NORTH);
		// c.add(new JButton("South"),BorderLayout.SOUTH);
		// c.add(new JButton("East"),BorderLayout.EAST);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
		this.colorChooserPanel = new ColorChooserPanel(this);
		c.add(this.colorChooserPanel,BorderLayout.SOUTH);
		this.model=model;

		c.add(this.paintPanel, BorderLayout.CENTER);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.pack();
		this.setVisible(true);
	}

	public PaintPanel getPaintPanel(){
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	public ColorChooserPanel getColorChooserPanel(){
		return colorChooserPanel;
	}
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(keyStrokeToCut); // adding shortkey CTRL-X
		
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(keyStrokeToCopy); // adding shortkey CTRL-C
		
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(keyStrokeToPaste); // adding shortkey CTRL-V
		
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(keyStrokeToUndo); // adding shortkey CTRL-Z
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menuItem.setAccelerator(keyStrokeToRedo); // adding shortkey CTRL-R
		
		menu.add(menuItem);
		menuBar.add(menu);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if(e.getActionCommand() == "Exit") {
			System.exit(0);
		}
		else if(e.getActionCommand() == "New"){
			model.clear();
			paintPanel.repaint();
		}
		else if(e.getActionCommand() == "Open") {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(new File("C:\\Users "));
			} catch (IOException e1) {
				JFrame f = new JFrame();
				f.isVisible();
				JLabel l = new JLabel();
				l.setText("No C drive found");
			}
		}
		else if(e.getActionCommand() == "Save") {
			//todo
		}
		else if(e.getActionCommand() == "Undo") {
			this.model.removeCommand();
			repaint();
		}
		else if (e.getActionCommand() == "Redo"){
			this.model.redoCommand();
			repaint();
		}
	}
}
