package com.rislah.tpilet.repository;

import com.rislah.tpilet.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	boolean existsCompanyByName(String name);

    @Query("SELECT c FROM Company c")
    @EntityGraph(value = "graph.Company.buses", type = EntityGraph.EntityGraphType.FETCH)
	List<Company> findAllWithBuses();
}
