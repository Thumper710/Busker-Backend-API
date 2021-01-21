package net.javaguides.springboot.webUtils;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.model.Item;
import net.javaguides.springboot.model.Services;
import net.javaguides.springboot.model.User;

import net.javaguides.springboot.model.Venue;
import net.javaguides.springboot.repository.ItemRepository;
import net.javaguides.springboot.repository.ServicesRepository;

import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.repository.VenueRepository;



@Component
public class WebUtils{

	

//	private static final Logger log=LoggerFactory.getLogger("WebUtils.class");
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private VenueRepository venueRepository;
	
	@Autowired
	private ServicesRepository servicesRepository;

	@Autowired
	HttpServletRequest request;

	private static final String UPLOADED_FOLDER = "static"+ File.separator +"img";

	private JavaMailSender sender;
	
	@Autowired    
	public WebUtils(JavaMailSender sender, UserRepository userRepository, ItemRepository itemRepository) {
		super();
		this.sender = sender;
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
	}

	public void sendMail(String to, String msg, String subject) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setText(msg);
			helper.setSubject(subject);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		try {
			sender.send(message);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addFile(MultipartFile file) {
		
	}

	public void addProfilePhoto(MultipartFile file, long id, String folder) throws IllegalStateException, IOException {
		System.out.println(1);
		System.out.println("FILE="+file.getOriginalFilename() + "ID=" + id + "folder=" + folder);
		try {
			///Users/alexgrant/Documents/JavaGuides/react-frontend/src/photos
			
			System.out.println(":> "+file.getOriginalFilename());
			// save dir
			//String destDir = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER)+ 
			//		File.separator +id+ File.separator +"profile";
			
			String destDir = "/Users/alexgrant/Documents/JavaGuides/react-frontend/public/photos" + File.separator +id+ File.separator +"profile";
			
			//String resourceDir = WebUtils.class.getClassLoader().getResource(UPLOADED_FOLDER).toURI().getPath();
			//System.out.println(":::> Resource directory "+resourceDir);
			//String destDir = UPLOADED_FOLDER+File.separator;
			System.out.println(":::> Destination directory :: "+destDir);
			//innitalize file
			
			File dir = new File(destDir);
			//if folder does not exist create it
			System.out.println(2);
			if(!dir.exists()){                                     
				new File(destDir).mkdirs();        
				System.out.println("---> Directory created");
			}
			System.out.println("Full path ::> "+dir.getAbsolutePath());
			//get file from form
			MultipartFile multipartFile = (MultipartFile) file; 
			String fileName = file.getOriginalFilename(); //set
			File doc = new File(destDir + File.separator + fileName);
			//rename file
			File rename = new File(destDir + File.separator + "user_" + id+".jpg");
			//File rename = new File(destDir);
			doc.renameTo(rename);
			System.out.println(3);
			//save to file system
			System.out.println("Absolute path ::> "+Paths.get(destDir).toAbsolutePath());
			//Files.copy(file.getInputStream(), Paths.get(destDir+ File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
			multipartFile.transferTo(rename.getAbsoluteFile());
			User user=userRepository.findById(id).get();
			System.out.println("FILENAME=" + fileName);
			user.setPicture("user_"+id+".jpg");
			userRepository.save(user);
			System.out.println(4);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void addItemPhoto(MultipartFile file, long id, String folder) throws IllegalStateException, IOException {
		System.out.println(1);
		System.out.println("FILE="+file.getOriginalFilename() + "ID=" + id + "folder=" + folder);
		try {
			///Users/alexgrant/Documents/JavaGuides/react-frontend/src/photos
			
			System.out.println(":> "+file.getOriginalFilename());
			// save dir
			//String destDir = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER)+ 
			//		File.separator +id+ File.separator +"profile";
			
			String destDir = "/Users/alexgrant/Documents/JavaGuides/react-frontend/public/itempics" + File.separator +id+ File.separator +"item";
			
			//String resourceDir = WebUtils.class.getClassLoader().getResource(UPLOADED_FOLDER).toURI().getPath();
			//System.out.println(":::> Resource directory "+resourceDir);
			//String destDir = UPLOADED_FOLDER+File.separator;
			System.out.println(":::> Destination directory :: "+destDir);
			//innitalize file
			
			File dir = new File(destDir);
			//if folder does not exist create it
			System.out.println(2);
			if(!dir.exists()){                                     
				new File(destDir).mkdirs();        
				System.out.println("---> Directory created");
			}
			System.out.println("Full path ::> "+dir.getAbsolutePath());
			//get file from form
			MultipartFile multipartFile = (MultipartFile) file; 
			String fileName = file.getOriginalFilename(); //set
			File doc = new File(destDir + File.separator + fileName);
			//rename file
			File rename = new File(destDir + File.separator + "item_" + id+".jpg");
			//File rename = new File(destDir);
			doc.renameTo(rename);
			System.out.println(3);
			//save to file system
			System.out.println("Absolute path ::> "+Paths.get(destDir).toAbsolutePath());
			//Files.copy(file.getInputStream(), Paths.get(destDir+ File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
			multipartFile.transferTo(rename.getAbsoluteFile());
			Item item=itemRepository.findById(id).get();
			System.out.println("FILENAME=" + fileName);
			item.setPicture("item_"+id+".jpg");
			itemRepository.save(item);
			System.out.println(4);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void addServicePhoto(MultipartFile file, long id, String folder) throws IllegalStateException, IOException {
		System.out.println(1);
		System.out.println("FILE="+file.getOriginalFilename() + "ID=" + id + "folder=" + folder);
		try {
			///Users/alexgrant/Documents/JavaGuides/react-frontend/src/photos
			
			System.out.println(":> "+file.getOriginalFilename());
			// save dir
			//String destDir = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER)+ 
			//		File.separator +id+ File.separator +"profile";
			
			String destDir = "/Users/alexgrant/Documents/JavaGuides/react-frontend/public/servicepics" + File.separator +id+ File.separator +"service";
			
			//String resourceDir = WebUtils.class.getClassLoader().getResource(UPLOADED_FOLDER).toURI().getPath();
			//System.out.println(":::> Resource directory "+resourceDir);
			//String destDir = UPLOADED_FOLDER+File.separator;
			System.out.println(":::> Destination directory :: "+destDir);
			//innitalize file
			
			File dir = new File(destDir);
			//if folder does not exist create it
			System.out.println(2);
			if(!dir.exists()){                                     
				new File(destDir).mkdirs();        
				System.out.println("---> Directory created");
			}
			System.out.println("Full path ::> "+dir.getAbsolutePath());
			//get file from form
			MultipartFile multipartFile = (MultipartFile) file; 
			String fileName = file.getOriginalFilename(); //set
			File doc = new File(destDir + File.separator + fileName);
			//rename file
			File rename = new File(destDir + File.separator + "service_" + id+".jpg");
			//File rename = new File(destDir);
			doc.renameTo(rename);
			System.out.println(3);
			//save to file system
			System.out.println("Absolute path ::> "+Paths.get(destDir).toAbsolutePath());
			//Files.copy(file.getInputStream(), Paths.get(destDir+ File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
			multipartFile.transferTo(rename.getAbsoluteFile());
			Services service=servicesRepository.findById(id).get();
			System.out.println("FILENAME=" + fileName);
			service.setPicture("service_"+id+".jpg");
			servicesRepository.save(service);
			System.out.println(4);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void addVenuePhoto(MultipartFile file, long id, String folder) throws IllegalStateException, IOException {
		System.out.println(1);
		System.out.println("FILE="+file.getOriginalFilename() + "ID=" + id + "folder=" + folder);
		try {
			///Users/alexgrant/Documents/JavaGuides/react-frontend/src/photos
			
			System.out.println(":> "+file.getOriginalFilename());
			// save dir
			//String destDir = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER)+ 
			//		File.separator +id+ File.separator +"profile";
			
			String destDir = "/Users/alexgrant/Documents/JavaGuides/react-frontend/public/venuepics" + File.separator +id+ File.separator +"venue";
			
			//String resourceDir = WebUtils.class.getClassLoader().getResource(UPLOADED_FOLDER).toURI().getPath();
			//System.out.println(":::> Resource directory "+resourceDir);
			//String destDir = UPLOADED_FOLDER+File.separator;
			System.out.println(":::> Destination directory :: "+destDir);
			//innitalize file
			
			File dir = new File(destDir);
			//if folder does not exist create it
			System.out.println(2);
			if(!dir.exists()){                                     
				new File(destDir).mkdirs();        
				System.out.println("---> Directory created");
			}
			System.out.println("Full path ::> "+dir.getAbsolutePath());
			//get file from form
			MultipartFile multipartFile = (MultipartFile) file; 
			String fileName = file.getOriginalFilename(); //set
			File doc = new File(destDir + File.separator + fileName);
			//rename file
			File rename = new File(destDir + File.separator + "venue_" + id+".jpg");
			//File rename = new File(destDir);
			doc.renameTo(rename);
			System.out.println(3);
			//save to file system
			System.out.println("Absolute path ::> "+Paths.get(destDir).toAbsolutePath());
			//Files.copy(file.getInputStream(), Paths.get(destDir+ File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
			multipartFile.transferTo(rename.getAbsoluteFile());
			Venue venue=venueRepository.findById(id).get();
			System.out.println("FILENAME=" + fileName);
			venue.setPicture("venue_"+id+".jpg");
			venueRepository.save(venue);
			System.out.println(4);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	public void multiplesave(MultipartFile[] files, long id) throws IllegalStateException, IOException {

		try {
			String realPathtoUploads = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);
			File dir = new File(realPathtoUploads+ File.separator + id);
			List<String> fileNames = new ArrayList<String>();

			if (!dir.exists())
				dir.mkdirs();
			if (files != null && files.length > 0) {
				for (MultipartFile file : files) {
					String fileName = file.getOriginalFilename();
					String filePath = realPathtoUploads + File.separator + id+ File.separator + fileName;
//					logger.info("################## Dest {}", filePath); 
					File destination = new File(filePath);
					try {
						file.transferTo(destination);
					} catch (Exception e) {
						e.printStackTrace();
					}
					fileNames.add(fileName);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void removefiles(long id, String image) {
		try {
			String realPathtoUploads = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);

			File file = new File(realPathtoUploads + File.separator 
					+ id+ File.separator +image+"");   
			if(file.delete()){
				if(userRepository.findById(id).get().getPicture() != null) {
					User cus=userRepository.findById(id).get();
					cus.setPicture("");
					userRepository.save(cus);
				}
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation is failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getFiles(Model model, long id) {
		List<String> results = new ArrayList<String>();
		try {
			String realPathtoUploads = request.getSession().getServletContext().getRealPath(UPLOADED_FOLDER);
			File[] files = new File(realPathtoUploads + File.separator + id).listFiles();
			for (File file : files) {
				if (file.isFile()) {
					results.add(file.getName());
					model.addAttribute("filesname", results);
				}    
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}