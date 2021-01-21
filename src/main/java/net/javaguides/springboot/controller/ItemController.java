package net.javaguides.springboot.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Item;
import net.javaguides.springboot.model.Services;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.ItemRepository;
import net.javaguides.springboot.repository.ServicesRepository;
import net.javaguides.springboot.webUtils.WebUtils;

@CrossOrigin									 	//=Puts spring on same port as react
@RestController										//-Creates rest controller	
@RequestMapping("/api/v1/")							//-Sets request mapping
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;			//-Autowire allows us to write userRepository once and use throughout
	
	@Autowired
	private WebUtils webUtils;
	
	@GetMapping("items")							//This code creates a simple rest endpoint that 
	public List<Item> getItems(){					 //returns a list of all users in the @Autowired userRepository
		return this.itemRepository.findAll();		 //object, created from UserRepository class
	}
	
	@PostMapping("searchItem")
	public ResponseEntity<List<Item>> searchItem(@RequestParam String searchQuery){
		return new ResponseEntity<List<Item>>(itemRepository.searchForItems(searchQuery), HttpStatus.OK);

	}
	
//	@PostMapping("item")
//	public ResponseEntity<Item> setItem(@RequestBody Item item){
//		List<Item> existingItem = itemRepository.findAllByItemName(item.getItemName());
//		if(existingItem.isEmpty()) {
//			return new ResponseEntity<Item>(this.itemRepository.save(item), HttpStatus.OK);
//		}
//		return new ResponseEntity<Item>(existingItem.get(0), HttpStatus.OK); 
//	}
	
	@RequestMapping(value="item", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Item> setItem(Item item){
		Optional<Item> existingItem = (Optional<Item>) itemRepository.findById(item.getId());
		if(existingItem.isEmpty()) {
			System.out.println(":::> ITEM ::> "+item.toString() );
			Item savedItem = this.itemRepository.save(item);
			System.out.println(savedItem.getFile().getOriginalFilename()+ " hello test *****>>>>>");
			try {
				webUtils.addItemPhoto(item.getFile(), savedItem.getId(), "item");
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			savedItem.setFile(null);

			return new ResponseEntity<Item>( savedItem, HttpStatus.OK);
		}
		return new ResponseEntity<Item>(existingItem.get(), HttpStatus.OK);
	
		
	}
	
	@DeleteMapping("item")
	public ResponseEntity<String> deleteItem( @RequestParam Long id) {
		itemRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}