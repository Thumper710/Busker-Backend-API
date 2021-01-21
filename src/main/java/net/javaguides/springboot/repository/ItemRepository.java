package net.javaguides.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	Optional<Item> findById(long id);
	List<Item> findAllByItemType(String itemType);
	List<Item> findAllByItemName(String itemName);
	List<Item> findAllByPrice(String price);
	
	@Query("FROM Item WHERE itemName LIKE ?1% OR itemType LIKE ?1%")
	List<Item> searchForItems(String searchQuery);


}
