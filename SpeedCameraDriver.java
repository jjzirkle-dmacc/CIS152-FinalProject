import javax.swing.JFrame;

/**
 * @author Jonah Zirkle This program creates a GUI that a user can input a
 *         license plate and speed. The input data will be stored in memory.
 *         Every action is logged to the JTextArea in the GUI. Includes the
 *         calculated fine for the speed. If the speed is 55 MPH or less there
 *         is no fine and user is prompted.
 *
 */
public class SpeedCameraDriver {

	/**
	 * @param args
	 * Main method to launch the GUI.
	 */
	
	public static void main(String[] args) {
		JFrame frame = new SpeedCameraFrame();							// Create instance of SpeedCameraFrame
		frame.setTitle("Speed Camera Database and Fine Calculator");    // Add a title to the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// Close window and exit program when user clicks the "X"
		frame.setLocationRelativeTo(null);								// Put GUI window in middle of users screen
		frame.setVisible(true);											// Allows frame to be visible on the screen
	}
}