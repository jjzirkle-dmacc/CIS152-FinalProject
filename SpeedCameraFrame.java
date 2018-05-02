import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Jonah Zirkle The SpeedCameraFrame is a class that extends JFrame and
 *         creates a GUI. The GUI contains two JTextField's and four buttons.
 *         Input is a string for the License Plate and an integer for the speed.
 *         Clicking "Add Entry" will put the key and value into a HashMap and
 *         print them and the calculated fine to a JTextArea. Clicking "Undo
 *         Last" will remove the last known entry from the HashMap. Clicking
 *         "Print Database" will display all the current entries of the HashMap
 *         onto a JOptionPane window. Clicking "Delete Entry" will delete a
 *         specific entry based on the entered License Plate.
 *
 */

public class SpeedCameraFrame extends JFrame {

	// Constants used for speeds for ranges
	private static final int FIFTY_FIVE = 55;
	private static final int SIXTY_FIVE = 65;
	private static final int SEVENTY_FIVE = 75;
	private static final int EIGHTY_FIVE = 85;
	private static final int NINETY_FIVE = 95;

	// Constants used for fines
	private static final int FINE_50 = 50;
	private static final int FINE_70 = 70;
	private static final int FINE_90 = 90;
	private static final int FINE_100 = 100;
	private static final int FINE_150 = 150;

	// Constants used for the frame
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 300;

	// Constants used for JTextArea
	private static final int AREA_ROWS = 10;
	private static final int AREA_COLUMNS = 30;

	// Constant used for JTextField
	private static final int FIELD_WIDTH = 10;

	// Constant used for the initial database entry
	private static final String INITIAL_MESSAGE = "No entries in the database";

	public String undoLast = null;

	// Various swing elements for GUI
	private JLabel keyLabel;
	private JLabel valueLabel;
	private JTextField keyField;
	private JTextField valueField;
	private JButton addButton;
	private JButton undoButton;
	private JButton deleteButton;
	private JButton printButton;
	private JTextArea resultField;

	// HashMap for the database
	protected static SpeedDatabase database = new SpeedDatabase();

	// Default constructor
	public SpeedCameraFrame() {

		resultField = new JTextArea(AREA_ROWS, AREA_COLUMNS);
		resultField.setText(INITIAL_MESSAGE + "\n");
		resultField.setEditable(false);

		createKeyField();
		createValueField();
		createAddButton();
		createUndoButton();
		createDeleteButton();
		createPrintButton();
		createPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Creates key field and label (License Plate)
	 */
	private void createKeyField() {
		keyLabel = new JLabel("License Plate: ");
		keyField = new JTextField(FIELD_WIDTH);
	}

	/**
	 * Creates value field and label (Speed)
	 */
	private void createValueField() {
		valueLabel = new JLabel("Speed of vehicle: ");
		valueField = new JTextField(FIELD_WIDTH);
	}

	/**
	 * Creates Add Entry button and has click listener
	 */
	private void createAddButton() {
		addButton = new JButton("Add Entry");

		ActionListener listener = new AddToDatabaseListener();
		addButton.addActionListener(listener);
	}

	/**
	 * Creates Undo Last button and has click listener
	 */
	private void createUndoButton() {
		undoButton = new JButton("Undo Last");

		ActionListener listener = new UndoFromDatabaseListener();
		undoButton.addActionListener(listener);
	}

	/**
	 * Creates Delete Entry button and has click listener
	 */
	private void createDeleteButton() {
		deleteButton = new JButton("Delete Entry");

		ActionListener listener = new DelFromDatabaseListener();
		deleteButton.addActionListener(listener);
	}

	/**
	 * Creates Print Database button and has click listener
	 */
	private void createPrintButton() {
		printButton = new JButton("Print Database");

		ActionListener listener = new PrintDatabaseListener();
		printButton.addActionListener(listener);
	}

	/**
	 * Add Entry button listener. Validates/handles user input. Calculates fine, if
	 * any, and adds the entry to the HashMap and the JTextArea.
	 */
	class AddToDatabaseListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String keyData = keyField.getText();
			String valueString = valueField.getText();
			try {

				int valueData = Integer.parseInt(valueString);

				if (keyData == null || valueString == null) {

					JOptionPane.showMessageDialog(null, "Input cannot be empty.");

				}

				else if (valueData <= FIFTY_FIVE) {
					JOptionPane.showMessageDialog(null,
							"Speed of " + valueString + " does not constitue a fine.  Entry not added." + "\n");
				}

				else if (valueData > FIFTY_FIVE && valueData < SIXTY_FIVE) {
					database.putIntoDatabase(keyData, valueData);
					resultField
							.append("License Plate: " + keyData + " Speed: " + valueData + " Fine : " + FINE_50 + "\n");
					undoLast = keyData;
				}

				else if (valueData >= SIXTY_FIVE && valueData < SEVENTY_FIVE) {
					database.putIntoDatabase(keyData, valueData);
					resultField
							.append("License Plate: " + keyData + " Speed: " + valueData + " Fine : " + FINE_70 + "\n");
					undoLast = keyData;
				}

				else if (valueData >= SEVENTY_FIVE && valueData < EIGHTY_FIVE) {
					database.putIntoDatabase(keyData, valueData);
					resultField
							.append("License Plate: " + keyData + " Speed: " + valueData + " Fine : " + FINE_90 + "\n");
					undoLast = keyData;
				}

				else if (valueData >= EIGHTY_FIVE && valueData < NINETY_FIVE) {
					database.putIntoDatabase(keyData, valueData);
					resultField.append(
							"License Plate: " + keyData + " Speed: " + valueData + " Fine : " + FINE_100 + "\n");
					undoLast = keyData;
				}

				else if (valueData >= NINETY_FIVE) {
					database.putIntoDatabase(keyData, valueData);
					resultField.append(
							"License Plate: " + keyData + " Speed: " + valueData + " Fine : " + FINE_150 + "\n");
					undoLast = keyData;
				}
			}

			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Speed is not a number.");
			}
		}
	}

	/**
	 * Undo Entry button listener. Removes the last entry from the HashMap.
	 */
	class UndoFromDatabaseListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			database.deleteFromDatabase(undoLast);
			resultField.append(undoLast + " has been removed." + "\n");

		}
	}

	/**
	 * Delete Entry button listener. Removes specifc key (license plate) and value
	 * from the HashMap.
	 */
	class DelFromDatabaseListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			String keyData = keyField.getText();
			database.deleteFromDatabase(keyData);
			resultField.append(keyData + " has been removed." + "\n");

		}
	}

	/**
	 * Print Database button listener. Puts all HashMap entries into a TreeMap.
	 * Sorts ascending by key. Creates a JOptionPane pop-up that contains all the
	 * current key/value pairs in the HashMap.
	 */
	class PrintDatabaseListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			Map<String, Integer> treemap = new TreeMap<String, Integer>(database.getAll());
			JOptionPane.showMessageDialog(null, "Database contents: " + treemap);

		}
	}

	/**
	 * Creates the panel for the frame. All swing elements added to pane here.
	 */
	private void createPanel() {
		JPanel panel = new JPanel();
		panel.add(keyLabel);
		panel.add(keyField);
		panel.add(valueLabel);
		panel.add(valueField);
		panel.add(addButton);
		panel.add(undoButton);
		panel.add(printButton);
		panel.add(deleteButton);

		JScrollPane scrollPane = new JScrollPane(resultField);
		panel.add(scrollPane);

		add(panel);
	}

}