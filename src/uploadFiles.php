<?php
	
	#include("common.php");
	$uploadOk = 0;
	#checks if the name and password parameters exist 
	if(!isset($_POST["individualPictures"]) || !isset($_POST["classPicture"])) {
		die("Error, no picture file or zip folder");
	} else {
		//allow only zip and jpg files
		
		$ind = $_POST["individualPictures"];
		$classPic = $_POST["classPicture"];
		//still need info from James
		$target_dir = "uploads/";
		$ind_file = $target_dir . basename($ind);
		$class_file = $target_dir . basename($classPic);
		$zipFileType = pathinfo($ind_file,PATHINFO_EXTENSION);
		$jpgFileType = pathinfo($class_file,PATHINFO_EXTENSION);
		$zipFile = $_FILES["individualPictures"]["name"];
		$jpgFile = $_FILES["classPicture"]["name"];
		$successZip = move_uploaded_file($zipFile,$ind_file);
		$successJpg = move_uploaded_file($jpgFile, $class_file);
			
		if(!$successZip || !$successJpg) {
			echo "<p>Unable to save the file.</p>";
			exit;
		}



		if($zipFileType != "zip" && $jpgFileType != "jpg") {
			echo "Sorry, only JPG files are allowed.";
			$uploadOk = 0;
			die("Error, no picture file or zip folder");
			exit;
		} else {
			echo "upload success";
			exec("java -jar AttendanceMain.jar");
			$command = escapeshellcmd('/usr/unzip.py');
			$output = shell_exec($command);
			exit;
		}
		

		




		
	}
	
?>
