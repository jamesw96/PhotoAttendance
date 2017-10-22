import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import org.apache.commons.io.*;

public class AttendanceMain {
	
	public static final String IMG_PATH = "CSEChairs03.jpg";
	public static final String STUDENT_DIRECTORY = "sandbox/students";
	
	public static void main(String args[]) throws IOException{
		
		
		
		BufferedImage classImage = ImageIO.read(new File("sandbox/" + IMG_PATH));
		CalcAttendance c = new CalcAttendance(classImage);
		Map<String, BufferedImage> studentPhotos = new HashMap<String, BufferedImage>();
		
		File dir = new File(STUDENT_DIRECTORY);
		File[] directoryListing = dir.listFiles();
		
		// System.out.println(Arrays.toString(directoryListing));
		
		if(directoryListing!=null){
			for(File file: directoryListing){
				String fileName = file.toString();
				
				if(FilenameUtils.getExtension(fileName).equals("jpg")){
					
					String removeExtension = fileName.substring(0,fileName.length()-4);
					studentPhotos.put(removeExtension, ImageIO.read(file));
				}
			}
		}
		
		System.out.println(studentPhotos.size());
		
		c.takeAttendance(classImage, studentPhotos);
	}
}
