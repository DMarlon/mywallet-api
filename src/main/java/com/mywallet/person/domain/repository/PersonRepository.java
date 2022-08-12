package com.mywallet.person.domain.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mywallet.person.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("FROM Person p WHERE LOWER(CONCAT(p.name, ' ', p.surname)) LIKE CONCAT('%', :term, '%') ORDER BY p.name")
	List<Person> findAutoComplete(@Param("term") String term, Pageable pageable);

	default Pageable getPageableAutoComplete() {
		return PageRequest.of(0, 10);
	}

}
