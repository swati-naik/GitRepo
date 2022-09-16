package com.numpyninja.lms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.numpyninja.lms.entity.UserPictureEntity;
import com.numpyninja.lms.repository.UserPictureRepository;
import com.numpyninja.lms.services.UserPictureService;

@RestController
@RequestMapping("/file")
public class UserPictureController {

	@Autowired
	private UserPictureService userpictureservice;
	private Object ServletUriComponentBuilder;
	
	
	private UserPictureRepository userpicturerepo;
	
	//@Value("${project.image}")
	//String path = "C:\\Users\\swati\\git\\LMS_2\\src\\main\\resources\\static\\logo";
	//UserPictureEntity userpicture = (UserPictureEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	/*@PostMapping("/userpicture")

	public ResponseEntity<FileResponse> fileUpload(UserPictureEntity userpicture 
			,@RequestParam ("image") MultipartFile image ){
		
		String filename = null;
		try 
		{
		 filename = this.userpictureservice.UploadImage(path, image);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			//return new ResponseEntity<>(new FileResponse(null,"image not uploaded"), HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<FileResponse>(new FileResponse(null,"img not upload"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<FileResponse>(new FileResponse(filename,"image uploaded"), HttpStatus.OK);
		
		
		
		
	} 
	
	@GetMapping("/download/{id}")
	public UserPictureEntity downloadFile(@PathVariable("UserPictureEntity") String Id) {
		
		return userpictureservice.downloadFile(Id);
	} 
	
	*/
	
	
	
	

    @PostMapping("/upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile multipartFile ) throws IOException {

    	//User user1 = userpicturerepo.findById(uid).get();
    	userpictureservice.uploadLocal(multipartFile);

    }
    
    
    
    
    @PostMapping("/userpicture")
    public void uploadDB(@RequestParam("file")MultipartFile multipartFile) throws IOException
    {
          
    userpictureservice.uploadtoDB(multipartFile);
    	// userpictureservice.uploadtoDB(multipartFile);
    }
    
    
    
    
  /*  @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id)
    {
        UploadedFile uploadedFileToRet =  fileUploadService.downloadFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= "+uploadedFileToRet.getFileName())
                .body(new ByteArrayResource(uploadedFileToRet.getFileData()));
    }*/
  
   // @GetMapping("/userpicture/{id}")
    //public UserPictureEntity downloadFile(@PathVariable String FileId )
    {
    	
    	//UserPictureEntity userpicture =  userpicture.downloadFile(FileId);
    	//UserPictureEntity uploadedFileToRet=	userpictureservice.downloadFile(FileId);
    	
    	
    			
        //return ResponseEntity.ok();
    	// String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
          //       .path("/downloadFile/")
            //     .path(uploadedFileToRet.getUserFilePath())
              //   .toUriString();
		//return uploadedFileToRet;
		
		
    	// return fileDownloadUri;
                
    	//return userpictureservice.downloadFile(fileid,userid);
    //return userpictureservice.downloadFile(FileId);
    } 
    
    
   /* @PutMapping("/userpicture/{userID}")
    public void updateFile(@RequestParam("file") MultipartFile multipartFile ,String userid) throws IOException
    
    {
    	
    	 userpictureservice.updateFile(multipartFile, userid);
    	
    }
	
    @DeleteMapping("/userpicture/{userID}")
	public void DeleteFile(@PathVariable String userid) throws IOException
	{
    	userpictureservice.DeleteFile(userid);
	} */ 
	
	
	
    
    
    
    
 // UserPictureEntity userpicture =  userpicture.downloadFile(id);
    //return ResponseEntity.ok()
      //      .contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
        //    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= "+uploadedFileToRet.getFileName())
          //  .body(new ByteArrayResource(uploadedFileToRet.getFileData()));
	
		
	
}	
	
	
  
