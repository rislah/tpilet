package com.rislah.tpilet.repository;

import com.rislah.tpilet.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    boolean existsBusByNumber(int number);
    boolean existsBusById(int id);
}
