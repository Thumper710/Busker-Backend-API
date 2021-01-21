//package net.javaguides.springboot.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import net.javaguides.springboot.model.Item;
//import net.javaguides.springboot.model.Services;
//import net.javaguides.springboot.model.User;
//import net.javaguides.springboot.model.UserPosts;
//import net.javaguides.springboot.model.Venue;
//
//
//
//
//@Repository
//public interface UserPostsRepository extends JpaRepository <UserPosts, Long> {
//
//
//Optional<UserPosts> findAllPostsByUser(Services service, User user, Item item, Venue venue);
//List<UserPosts> findByServiceAndUser(Services service, User user);
//List<UserPosts> findByItemAndUser(Item item, User user);
//List<UserPosts> findByUser(User user);
//
//
//}
