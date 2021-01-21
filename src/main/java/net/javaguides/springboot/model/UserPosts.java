//package net.javaguides.springboot.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//
//@Entity
//public class UserPosts {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@ManyToOne
//	@JoinColumn(name="item_id")
//	private Item item;
//	
//	@ManyToOne
//	@JoinColumn(name="venue_id")
//	private Venue venue;
//	
//	@ManyToOne
//	@JoinColumn(name="service_id")
//	private Services service;
//
//	
//	@OneToMany
//	@JoinColumn(name="user_post_id")
//	private User user;
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Item getItem() {
//		return item;
//	}
//
//	public void setItem(Item item) {
//		this.item = item;
//	}
//
//	public Venue getVenue() {
//		return venue;
//	}
//
//	public void setVenue(Venue venue) {
//		this.venue = venue;
//	}
//
//	public Services getService() {
//		return service;
//	}
//
//	public void setService(Services service) {
//		this.service = service;
//	}
//	
//	
//
//}
//


