package net.javaguides.springboot.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Services;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.Venue;
import net.javaguides.springboot.repository.ServicesRepository;
import net.javaguides.springboot.webUtils.WebUtils;


@CrossOrigin									 	//=Puts spring on same port as react
@RestController										//-Creates rest controller	
@RequestMapping("/api/v1/")							//-Sets request mapping
public class ServiceController {
	
	@Autowired
	private ServicesRepository servicesRepository;			//-Autowire allows us to write userRepository once and use throughout
	
	@Autowired
	private WebUtils webUtils;
	
	@GetMapping("services")							//This code creates a simple rest endpoint that 
	public List<Services> getServices(){					 //returns a list of all users in the @Autowired userRepository
		return this.servicesRepository.findAll();		 //object, created from UserRepository class
	}
	
	@PostMapping("searchServices")
	public ResponseEntity<List<Services>> searchServices(@RequestParam String searchQuery){
		return new ResponseEntity<List<Services>>(servicesRepository.searchForServices(searchQuery), HttpStatus.OK);

	}
	
	@PostMapping("service")
	public ResponseEntity<Services> setServices(@RequestBody Services service){
		Optional<Services> existingService = servicesRepository.findByServiceType(service.getServiceType());
		if(existingService.isEmpty()) {
			return new ResponseEntity<Services>(this.servicesRepository.save(service), HttpStatus.OK);
		}
		return new ResponseEntity<Services>(existingService.get(), HttpStatus.OK);
	
		
	}
	
	@RequestMapping(value="service", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Services> setService(Services service){
		Optional<Services> existingService = servicesRepository.findByEmail(service.getEmail());
		if(existingService.isEmpty()) {
			System.out.println(":::> SERVICE ::> "+service.toString() );
			Services savedService = this.servicesRepository.save(service);
			System.out.println(savedService.getFile().getOriginalFilename()+ " hello test *****>>>>>");
			try {
				webUtils.addServicePhoto(service.getFile(), savedService.getId(), "service");
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Services>( savedService, HttpStatus.OK);
		}
		return new ResponseEntity<Services>(existingService.get(), HttpStatus.OK);
	
		
	}
}
