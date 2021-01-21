package net.javaguides.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long>{

	Optional<Venue> findByVenueName(String venueName);
	Optional<Venue> findByEmail(String email);
	
	@Query("FROM Venue WHERE venueName LIKE ?1% OR description LIKE ?1%")
	List<Venue> searchForVenues(String searchQuery);

	
}
