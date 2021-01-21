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

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.Venue;
import net.javaguides.springboot.repository.VenueRepository;
import net.javaguides.springboot.webUtils.WebUtils;

@CrossOrigin									 	//=Puts spring on same port as react
@RestController										//-Creates rest controller	
@RequestMapping("/api/v1/")	
public class VenueController {

		@Autowired
		private VenueRepository venueRepository;		//-Autowire allows us to write userRepository once and use throughout
		
		@Autowired
		private WebUtils webUtils;
		
		@GetMapping("venues")							//This code creates a simple rest endpoint that 
		public List<Venue> getVenues(){					 //returns a list of all users in the @Autowired userRepository
			return this.venueRepository.findAll();		 //object, created from UserRepository class
		}
		
		@PostMapping("searchVenue")
		public ResponseEntity<List<Venue>> searchUser(@RequestParam String searchQuery){
			return new ResponseEntity<List<Venue>>(venueRepository.searchForVenues(searchQuery), HttpStatus.OK);

		}
		
//		@PostMapping("venue")
//		public ResponseEntity<Venue> setVenue(@RequestBody Venue venue){
//			Optional<Venue> existingVenue = venueRepository.findByVenueName(venue.getVenueName());
//			if(existingVenue.isEmpty()) {
//				return new ResponseEntity<Venue>(this.venueRepository.save(venue), HttpStatus.OK);
//			}
//			return new ResponseEntity<Venue>(existingVenue.get(), HttpStatus.OK);
//		
//			
//		}
		
		@RequestMapping(value="venue", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
		public ResponseEntity<Venue> setVenue(Venue venue){
			Optional<Venue> existingVenue = venueRepository.findByEmail(venue.getEmail());
			if(existingVenue.isEmpty()) {
				System.out.println(":::> VENUE ::> "+venue.toString() );
				Venue savedVenue = this.venueRepository.save(venue);
				System.out.println(savedVenue.getFile().getOriginalFilename()+ " hello test *****>>>>>");
				try {
					webUtils.addVenuePhoto(venue.getFile(), savedVenue.getId(), "venue");
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ResponseEntity<Venue>( savedVenue, HttpStatus.OK);
			}
			return new ResponseEntity<Venue>(existingVenue.get(), HttpStatus.OK);
		
			
		}
		
}
