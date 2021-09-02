package com.rislah.tpilet.repository;

import com.rislah.tpilet.model.Route;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    @EntityGraph(value = "graph.Route.findAll")
    List<Route> findAll();
}
