package com.paimon.QLBanVePaimon.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Seat;

@Repository
public interface SeatRepository extends MongoRepository<Seat,String> {
    
}
