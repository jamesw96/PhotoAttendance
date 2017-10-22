import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import javax.imageio.ImageIO;

public class CalcAttendance {
	private BufferedImage image = null;
	private Map<String, Boolean> isPresent;
	private int studentCount = 0;
	private int notInRoster = 0;
	
	public CalcAttendance() throws IOException {
		try {
		    image = ImageIO.read(new File("c:\\"));
		} catch (IOException e) {
			throw new IOException("Image not found.");
		}
	}
	
	// Parse through photos of students, and determine whether student is present or not.
	public void takeAttendance(BufferedImage image, Map<String, BufferedImage> studentsHere) {
		// api call in order to get the total number of faces in an image. 
		int total = 0; //api call
		isPresent = new HashMap<String, Boolean>();
		for (String student : studentsHere.keySet()) {
			if ( /* if student is present */ ) {
				isPresent.put(student, true);
				studentCount++;
			} else {
				isPresent.put(student, false);
			}
		}
		// at the end get the total number of students in the image and then see how many left. 
		notInRoster = total - studentCount;
	}
	
	// Returns whether or not the room is at capacity
	public boolean getCapacity() {
		// get information about the room and determine if we are above capacity. 
	}
	
	// Get count of students who are currently here.
	public int getStudentCount() {
		return studentCount;
	}
	
	// Writes data out to a .csv file
	public File writeToCSV() throws FileNotFoundException {
		File attendanceSheet = new File("attendence_" + 
				Calendar.getInstance().toString() + ".csv");
		PrintStream out = new PrintStream(attendanceSheet);
		out.println("Student name, Attendance");
		for (String student : isPresent.keySet()) {
			out.print(student + ", ");
			if (isPresent.get(student)) {
				out.println("Here");
			} else {
				out.println("Absent");
			}
		}
		return attendanceSheet;
	}
}
