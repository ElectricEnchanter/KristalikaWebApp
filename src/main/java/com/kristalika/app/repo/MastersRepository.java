package com.kristalika.app.repo;

import com.kristalika.app.models.Masters;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MastersRepository extends CrudRepository<Masters, Long> {

	@Query("SELECT name FROM Masters WHERE pin = ?1")
	String findNameByPin(int pin);

	@Query("SELECT id FROM Masters WHERE pin = ?1")
	Long findIdByPin(int pin);

	@Query("SELECT id FROM Masters WHERE name = ?1")
	Long findIdByName(String name);

}
