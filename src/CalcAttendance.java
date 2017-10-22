import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class CalcAttendance {
	private BufferedImage image = null;
	private int studentCount = 0;
	
	public CalcAttendance() throws IOException {
		try {
		    image = ImageIO.read(new File("c:\\"));
		} catch (IOException e) {
			throw new IOException("Image not found.");
		}
	}
	
	// Parse through photos of students, and determine whether student is present or not.
	public void takeAttendance(BufferedImage image, Map<String, BufferedImage> studentsHere) {
		Map<String, Boolean> isPresent = new HashMap<String, Boolean>();
		for (String student : studentsHere.keySet()) {
			if (/* if student is present */) {
				isPresent.put(student, true);
				studentCount++;
			} else {
				isPresent.put(student, false);
			}
		}
	}
	
	// Get count of students who are currently here.
	public int getStudentCount() {
		return studentCount;
	}
}
