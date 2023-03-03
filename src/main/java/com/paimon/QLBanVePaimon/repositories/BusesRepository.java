package com.paimon.QLBanVePaimon.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Buses;

@Repository
public interface BusesRepository extends MongoRepository<Buses,String> {

    Page<Buses> findAll(Pageable pageable);

}
