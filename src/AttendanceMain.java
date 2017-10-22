import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.*;
import com.microsoft.projectoxford.face.*;
import com.microsoft.projectoxford.face.contract.PersonGroup;
import com.microsoft.projectoxford.face.rest.ClientException;



public class AttendanceMain {
	
	public static final String IMG_PATH = "CSEChairs03.jpg";
	public static final String STUDENT_DIRECTORY = "sandbox/students";
	
	public static void main(String args[]) throws IOException, ClientException{
		
		FaceServiceClient faceServiceClient =  
	             new FaceServiceRestClient("9bf27b78c01a42f88b82c7086a9ffee1");  
		

		faceServiceClient.deletePersonGroup("g1");
		faceServiceClient.createPersonGroup("g1", "classphoto", "no data");

		
		
		PersonGroup g1 = faceServiceClient.getPersonGroup("g1");
		System.out.println(g1.name);
	

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
					UUID person = new UUID(removeExtension.hashCode(), 0);
					faceServiceClient.createPerson("g1", removeExtension, "");
					faceServiceClient.getPerson("g1", person);
					//faceServiceClient.addPersonFace("g1", person , new FileInputStream(file), "", null);
					
					studentPhotos.put(removeExtension, ImageIO.read(file));
				}
			}
		}
		
		System.out.println(studentPhotos.size());
		
		c.takeAttendance(classImage, studentPhotos);
	}
	
	
	
}
