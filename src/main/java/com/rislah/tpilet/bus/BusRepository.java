package com.rislah.tpilet.bus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    boolean existsBusByNumber(int number);
    boolean existsBusById(int id);
}
