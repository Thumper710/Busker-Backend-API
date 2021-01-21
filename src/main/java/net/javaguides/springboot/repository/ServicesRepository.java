package net.javaguides.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Services;
import net.javaguides.springboot.model.Venue;



@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
	
	List<Services> findByServiceType(Services service);
	
	List<Services> findByRate(String rate);

	Optional<Services> findByServiceType(String serviceType);
	Optional<Services> findByEmail(String email);
	@Query("FROM Services WHERE serviceType LIKE ?1% OR description LIKE ?1%")
	List<Services> searchForServices(String searchQuery);


}
