package ca.utoronto.utm.paint;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about its effort
 * to parse a file. After a successful parse, an instance will have an ArrayList
 * of PaintCommand suitable for rendering. If there is an error in the parse,
 * the instance stores information about the error. For more on the format of
 * Version 1.0 of the paint save file format, see the associated documentation.
 * 
 * @author
 *
 */
public class PaintSaveFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage = ""; // error encountered during parse
	private ArrayList<PaintCommand> commands; // created as a result of the parse

	/**
	 * Below are Patterns used in parsing
	 */
	private Pattern pFileStart = Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd = Pattern.compile("^EndPaintSaveFile$");

	private Pattern pColor = Pattern.compile("^color:[(]?([-]?\\d+),([-]?\\d+),([-]?\\d+)[)]?$");// Pattern.compile("^color:(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5])),(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5])),(([1]?[0-9]?[0-9])|([2]?[0-5]?[0-5]))$");
	private Pattern pFilled = Pattern.compile("^filled:(true|false)$");

	private Pattern pCircleStart = Pattern.compile("^Circle$");
	private Pattern pCircleCenter = Pattern.compile("^center:[(]?([-]?\\d+),([-]?\\d+)[)]?$");
	private Pattern pCircleRadius = Pattern.compile("^radius:[(]?([-]?\\d+)[)]?$");// Pattern.compile("^radius:([1-9]?[0-9]?[0-9])$");
	private Pattern pCircleEnd = Pattern.compile("^EndCircle$");

	private Pattern pRectangleStart = Pattern.compile("^Rectangle$");
	private Pattern pP1Rectangle = Pattern.compile("^p1:[(](\\d+),(\\d+)[)]$");// Pattern.compile("^p1:\\(([1-9]?[0-9]?[0-9]),([1-9]?[0-9]?[0-9])\\)$");
	private Pattern pP2Rectangle = Pattern.compile("^p2:[(](\\d+),(\\d+)[)]$");// Pattern.compile("^p2:\\(([1-9]?[0-9]?[0-9]),([1-9]?[0-9]?[0-9])\\)$");
	private Pattern pRectangleEnd = Pattern.compile("^EndRectangle$");

	private Pattern pSquiggleStart = Pattern.compile("^Squiggle$");
	private Pattern pPointStartSquiggle = Pattern.compile("^points$");
	private Pattern pPointSquiggle = Pattern.compile("^point:[(](\\d+),(\\d+)[)]$"); // Pattern.compile("^point:(([1-9]?[0-9]?[0-9]),([1-9]?[0-9]?[0-9]))$");
	private Pattern pPointEndSquiggle = Pattern.compile("^endpoints$");
	private Pattern pSquiggleEnd = Pattern.compile("^EndSquiggle$");

	/**
	 * Store an appropriate error message in this, including lineNumber where the
	 * error occurred.
	 * 
	 * @param mesg
	 */
	private void error(String mesg) {
		this.errorMessage = "Error in line " + lineNumber + " " + mesg;
	}

	/**
	 * 
	 * @return the PaintCommands resulting from the parse
	 */
	public ArrayList<PaintCommand> getCommands() {
		return this.commands;
	}

	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Parse the inputStream as a Paint Save File Format file. The result of the
	 * parse is stored as an ArrayList of Paint command. If the parse was not
	 * successful, this.errorMessage is appropriately set, with a useful error
	 * message.
	 * 
	 * @param inputStream
	 *            the open file to parse
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream) {
		this.commands = new ArrayList<PaintCommand>();
		this.errorMessage = "";

		// During the parse, we will be building one of the
		// following shapes. As we parse the file, we modify
		// the appropriate shape.

		Circle circle = null;
		Rectangle rectangle = null;
		Squiggle squiggle = null;

		try {
			int state = 0;
			Matcher m;
			String l1;
			boolean fileStartFound = false, fileEndFound = false;

			this.lineNumber = 0;

			while ((l1 = inputStream.readLine()) != null) {

				this.lineNumber++;
				if (l1.length() > 0) { // Ignore empty lines
					String l = l1.replaceAll("\\s+", ""); // Remove all white space in that line

					System.out.println(lineNumber + " " + l + " " + state);

					switch (state) {
					case 0:
						m = pFileStart.matcher(l);
						if (m.matches()) {
							if (fileStartFound == false) {
								state = 1;
								fileStartFound = true;
								break;
							}
							// else {
							// error("duplicate file start message found");
							// return false;
							// }
						}
						error("Expected Start of Paint Save File");
						return false;
					case 1: // Looking for the start of a new object or end of the save file
						m = pCircleStart.matcher(l);
						if (m.matches()) {
							circle = new Circle();
							state = 2;
							break;
						}
						m = pRectangleStart.matcher(l);
						if (m.matches()) {
							rectangle = new Rectangle();
							state = 7;
							break;
						}
						m = pSquiggleStart.matcher(l);
						if (m.matches()) {
							squiggle = new Squiggle();
							state = 12;
							break;
						}
						m = pFileEnd.matcher(l);
						if (m.matches()) {
							fileEndFound = true;
							System.out.println("Hello from file end");
							state = 17;
							break;
						}
					case 2:
						m = pColor.matcher(l);
						if (m.matches()) {
							int red = Integer.parseInt(m.group(1)), green = Integer.parseInt(m.group(2)),
									blue = Integer.parseInt(m.group(3));
							if ((red >= 0 && red <= 255) && (green >= 0 && green <= 255)
									&& (blue >= 0 && blue <= 255)) {
								Color color = new Color(red, green, blue);
								circle.setColor(color);
								state = 3;
								System.out.println("case 2 after color");
								break;
							} else {
								error("Color specified out of range");
							}
						} else {
							error("Color format incorrect");
							return false;
						}

					case 3:
						m = pFilled.matcher(l);
						if (m.matches()) {
							circle.setFill(Boolean.parseBoolean(m.group(0)));
							state = 4;
							break;
						} else {
							error("Filled format incorrect");
							return false;
						}

					case 4:
						m = pCircleCenter.matcher(l);
						if (m.matches()) {
							circle.setCentre(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state = 5;
							break;
						} else {
							error("Center format incorrect");
							return false;
						}

					case 5:
						m = pCircleRadius.matcher(l);
						if (m.matches()) {
							int r = Integer.parseInt(m.group(1));
							if (r >= 0) {
								circle.setRadius(r);
								state = 6;
								break;
							} else {
								error(": circle's radius is smaller than or equal to zero");
								return false;
							}
						} else {
							error("Radius format incorrect");
							return false;
						}

					case 6:
						m = pCircleEnd.matcher(l);
						if (m.matches()) {
							this.commands.add(new CircleCommand(circle));
							state = 1;
							break;
						} else {
							error("Circle end expected");
							return false;
						}

					case 7:
						m = pColor.matcher(l);
						if (m.matches()) {
							Color color = new Color(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
									Integer.parseInt(m.group(3)));
							rectangle.setColor(color);
							state = 8;
							break;
						} else {
							error("Color format incorrect");
							return false;
						}

					case 8:
						m = pFilled.matcher(l);
						if (m.matches()) {
							rectangle.setFill(Boolean.parseBoolean(m.group(0)));
							state = 9;
							break;
						} else {
							error("Filled format incorrect");
							return false;
						}

					case 9:
						m = pP1Rectangle.matcher(l);
						if (m.matches()) {
							System.out
									.println("Case 9 - g0:" + m.group(0) + ", g1:" + m.group(1) + ", g2:" + m.group(2));
							rectangle.setP1(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state = 10;
							break;
						} else {
							error("Expected p1 for rectangle");
							return false;
						}

					case 10:
						m = pP2Rectangle.matcher(l);
						if (m.matches()) {
							rectangle.setP2(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state = 11;
							break;
						} else {
							error("Expected p2 for rectangle");
							return false;
						}

					case 11:
						m = pRectangleEnd.matcher(l);
						if (m.matches()) {
							this.commands.add(new RectangleCommand(rectangle));
							state = 1;
							break;
						} else {
							error("Expected rectangle end");
							return false;
						}

					case 12:
						m = pColor.matcher(l);
						if (m.matches()) {
							Color color = new Color(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
									Integer.parseInt(m.group(3)));
							squiggle.setColor(color);
							state = 13;
							break;
						} else {
							error("Color format incorrect");
							return false;
						}

					case 13:
						m = pFilled.matcher(l);
						if (m.matches()) {
							squiggle.setFill(Boolean.parseBoolean(m.group(0)));
							state = 14;
							break;
						} else {
							error("Filled format incorrect");
							return false;
						}

					case 14:
						m = pPointStartSquiggle.matcher(l);
						if (m.matches()) {
							state = 15;
							break;
						} else {
							error("Expected points to start");
							return false;
						}

					case 15:
						System.out.println("hello from case 15");
						m = pPointSquiggle.matcher(l);
						System.out.println("case 15 point squiggle, l=" + l + ", " + m.matches());
						if (m.matches()) {
							System.out.println("case 15 point squiggle");
							squiggle.add(new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
							state = 15;
							break;
						}
						m = pPointEndSquiggle.matcher(l);
						System.out.println("case 15 point end squiggle, " + m.matches());
						if (m.matches()) {
							System.out.println("case 15 point end squiggle");
							state = 16;
							break;
						} else {
							error("Point or end of points expected");
						}

					case 16:
						m = pSquiggleEnd.matcher(l);
						if (m.matches()) {
							this.commands.add(new SquiggleCommand(squiggle));
							state = 1;
							break;
						} else {
							error("Expected squiggle to end");
							return false;
						}

					case 17:
						System.out.println("hello from case 17");
						// System.out.println("Hello from case 1: " + l);
						if (fileStartFound == true && fileEndFound == true) {
							if (l != null && !("").equalsIgnoreCase(l)) {
								error("Content found after end of file");
								return false;
							}
						}
						// if(fileEndFound == false) {
						// state=1;
						// fileEndFound = true;
						// }
						// break;
						error("Expected file end");
						return false;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
