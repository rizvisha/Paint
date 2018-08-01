package ca.utoronto.utm.paint;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Paint extends JFrame implements ActionListener {
	private static final long serialVersionUID = -4031525251752065381L;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Paint();
			}
		});
	}

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;

	public Paint() {
		super("Paint"); // set the title and do other JFrame init
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());

		Container c = this.getContentPane();
		this.paintPanel = new PaintPanel();
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.paintPanel, BorderLayout.CENTER);
		c.add(this.shapeChooserPanel, BorderLayout.WEST);
		this.pack();
		this.setVisible(true);
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
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

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Open") {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String name = file.getName();
				try {

					BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()));
					PaintSaveFileParser p = new PaintSaveFileParser();

					if (p.parse(in)) {
						this.paintPanel.setCommands(p.getCommands());
						repaint();

					} else {
						String error = p.getErrorMessage();
						JOptionPane.showMessageDialog(null, error);
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				System.out.println("Opening: " + file.getName() + "." + "\n");

			} else {
				System.out.println("Open command cancelled by user." + "\n");
			}
		} else if (e.getActionCommand() == "Save") {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// This is where a real application would open the file.
				System.out.println("Saving: " + file.getName() + "." + "\n");
				System.out.println("Dir: " + fc.getCurrentDirectory());
				writeContentToFile(this.paintPanel.getCommands(), fc.getCurrentDirectory() + System.getProperty("file.separator") +file.getName());
			} else {
				System.out.println("Save command cancelled by user." + "\n");
			}
		} else if (e.getActionCommand() == "New") {
			this.paintPanel.reset();
			this.shapeChooserPanel.reset();
		}
		System.out.println(e.getActionCommand());
	}

	public static void writeContentToFile(ArrayList<PaintCommand> paintCommands, String filename) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			StringBuilder content = new StringBuilder("Paint Save File Version 1.0\n");
			for (Iterator iterator = paintCommands.iterator(); iterator.hasNext();) {
				PaintCommand paintCommand = (PaintCommand) iterator.next();
				content.append(paintCommand.fileDefinition() +"\n");
				System.out.println("fileDef: " +paintCommand.fileDefinition());
			}
			content.append("\nEnd Paint Save File\n");
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(content.toString());

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

	}

}
