package net.javaguides.springboot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);
	Optional<User> findByLastName(String lastName);
	Optional<User> findByFirstName(String firstName);
	Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
	Optional<User> findById(Long id);

	

//	Optional<User> findUser(long id);
	@Query("FROM User WHERE firstName LIKE ?1% OR lastName LIKE ?1%")
	List<User> searchForUsers(String searchQuery);
	
	
}


