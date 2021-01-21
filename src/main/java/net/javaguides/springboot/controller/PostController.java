//package net.javaguides.springboot.controller;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import net.javaguides.springboot.model.*;
//import net.javaguides.springboot.repository.ItemRepository;
//import net.javaguides.springboot.repository.ServicesRepository;
//import net.javaguides.springboot.repository.UserPostsRepository;
//import net.javaguides.springboot.repository.UserRepository;
//
//
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/v1")
//public class PostController {
//
//	@Autowired
//	private UserPostsRepository userPostsRepository;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private ItemRepository itemRepository;;
//	@Autowired 
//	private ServicesRepository servicesRepository;
//
//	
//	@RequestMapping(value="userPost", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<UserPosts> setUserPosts(UserPosts userPost){
//		Optional<UserPosts> existingUserPost = (Optional<UserPosts>) userPostsRepository.findById(userPost.getId());
//		if(existingUserPost.isEmpty()) {
//			System.out.println(":::> ITEM ::> "+userPost.toString() );
//			UserPosts savedPost = this.itemRepository.save(userPost);
//			System.out.println(savedPost.getFile().getOriginalFilename()+ " hello test *****>>>>>");
//			try {
//				webUtils.addUserProfilePicture(userPost.getFile(), savedPost.getId(), "post");
//			} catch (IllegalStateException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return new ResponseEntity<UserPosts>( savedPost, HttpStatus.OK);
//		}
//		return new ResponseEntity<UserPosts>(existingUserPost.get(), HttpStatus.OK);
//	
//		
//	}
//
////	//find user pantry
//	@PostMapping("/pantry/veggie")
//	public ResponseEntity<List<UserPantry>> findUserPantry(@RequestParam Long veggieId, @RequestParam Long userId) {
//		userRepo.findById(userId).ifPresent(user -> {
//			veggieRepo.findById(veggieId).ifPresent(veggie -> {
//				UserPantry pantry = new UserPantry();
//				pantry.setUser(user);
//				pantry.setVeggie(veggie);
//				pantryRepo.save(pantry);
//			});
//		});
//		return new ResponseEntity<>( pantryRepo.findByUser(userRepo.findById(userId).get()) , HttpStatus.OK);
//	}
//	
//	@PostMapping("/pantry/fruit")
//	public ResponseEntity<List<UserPantry>> findUserPantryFruit(@RequestParam Long fruitId, @RequestParam Long userId) {
//		userRepo.findById(userId).ifPresent(user -> {
//			fruitRepo.findById(fruitId).ifPresent(fruit -> {
//				UserPantry pantry = new UserPantry();
//				pantry.setUser(user);
//				pantry.setFruit(fruit);
//				pantryRepo.save(pantry);
//			});
//		});
//		return new ResponseEntity<>( pantryRepo.findByUser(userRepo.findById(userId).get()) , HttpStatus.OK);
//	}
//	
//	@PostMapping("/pantry/veggies")
//	public ResponseEntity<List<UserPantry>> findUserPantryVeggie(@RequestParam Long veggieId, @RequestParam Long userId) {
//		userRepo.findById(userId).ifPresent(user -> {
//			veggieRepo.findById(veggieId).ifPresent(veggie -> {
//				UserPantry pantry = new UserPantry();
//				pantry.setUser(user);
//				pantry.setVeggie(veggie);
//				pantryRepo.save(pantry);
//			});
//		});
//		return new ResponseEntity<>( pantryRepo.findByUser(userRepo.findById(userId).get()) , HttpStatus.OK);
//	}
//	
//	@GetMapping("/pantry")
//	public ResponseEntity<List<UserPantry>> getUserPantry( @RequestParam Long userId) {
//		return new ResponseEntity<>( pantryRepo.findByUser(userRepo.findById(userId).get()) , HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/pantry")
//	public ResponseEntity<String> deletetUserPantryItem( @RequestParam Long pantryItemId) {
//		pantryRepo.deleteById(pantryItemId);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
//}
