package com.numpyninja.lms.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserPictureEntity;
import com.numpyninja.lms.repository.UserPictureRepository;
import com.numpyninja.lms.repository.UserRepository;


@Service
public class UserPictureService {
	
	@Autowired
	private UserPictureRepository userpicturerepo;
	
	@Autowired
	private UserRepository userrepository;
	
	//private String uploadFolderpath = "/Users/Desktop/uploaded_" ;
	//private String uploadFolderpath ="C:\\Users\\swati\\git\\LMS_2\\src\\main\\resources\\static\\logo";
	
	private final String uploadFolderpath = new ClassPathResource("static/logo/").getFile().getAbsolutePath();
	
	
	public UserPictureService() throws IOException
	{
		
	}
	
	
	
public void uploadLocal(MultipartFile file) throws IOException{
		
		//byte[] data = file.getBytes();
	
	     Path path = Paths.get(uploadFolderpath + file.getOriginalFilename());
		//Files.write(path, data);
		Files.copy(file.getInputStream(), path); 
		
}
	
	
	public void uploadtoDB(MultipartFile file ,@PathVariable String id) throws IOException{
		
		 User uid = userrepository.findById(id).get();
		 System.out.println("uid is "+uid);
		
		UserPictureEntity userpicture = new UserPictureEntity();
		userpicture.setUserFileType(file.getContentType());
		userpicture.setUserFilePath(uploadFolderpath + file.getOriginalFilename());
		userpicture.setUser1(uid);
		//userpicture.setUid(user1);
		//userpicture.setUserFileId(1);;
		//userpicture.setUser1(User);;
		userpicturerepo.save(userpicture);
		
			
	}
	
	public UserPictureEntity downloadFile(String fileid) {
		
		UserPictureEntity userpicture1 = userpicturerepo.getById(fileid);
		
			
		return userpicture1;
	}
	
	
	
	
	
	
	public void updateFile( MultipartFile multipartFile , String userid) throws IOException
	{

		UserPictureEntity userpicture = new UserPictureEntity();
	 
	  Boolean result =userpicturerepo.existsById(userid);
	  if(result != null)
	  {
 	
 	//userpicture.setUserFileData(multipartFile.getBytes());
 	userpicture.setUserFileType(multipartFile.getContentType());
 	userpicture.setUserFilePath(uploadFolderpath + multipartFile.getOriginalFilename());
 	
 	userpicturerepo.save(userpicture);
	  }
	  
	  }
		
		
	public void DeleteFile(String userid) throws IOException
	{
		 
		  Boolean result =userpicturerepo.existsById(userid);
		  if(result != null)
		  {
			  userpicturerepo.deleteById(userid);
		  
		  }
	  
	} 
	
	
	
	
	
	
	
	//post  Upload Picture
/*	 public String UploadImage( String path ,MultipartFile file) throws IOException {
	
		//get original file name
		//abc.png
		 String name = file.getOriginalFilename();
		 
		 
		 
		 //generate random image name
		 
		 String randomID = UUID.randomUUID().toString();
		 String FileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		 
		 //full path
		 
	    String filepath = path + File.separator +FileName1;
		
		UserPictureEntity userpicture = new UserPictureEntity();
		
		//userpicture.setUserFileData(file.getBytes());
		userpicture.setUserFileType(file.getContentType());
		userpicture.setUserFilePath(path + file.getOriginalFilename());
		//userpicture.setUserId();
		//userpicture.setUserId("U01");
		userpicture.setUserFileId(25);
		//userpicture.setUserId(filepath);
		userpicturerepo.save(userpicture);
		 
		 //create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
	
		 //copy
		
		Files.copy(file.getInputStream(), Paths.get(filepath)); 
		
		
		return name;
		 
	} 
	
	  
	 
	 
	 //get image 
	 
	 
	 
	/* public UserPictureEntity downloadFile(String fileid) {
		 UserPictureEntity userpicture = userpicturerepo.getById(fileid);
			//userpicture.setUserFileId(fileid);
					
			return userpicture;
				
		} 
*/
	 
	 
}


