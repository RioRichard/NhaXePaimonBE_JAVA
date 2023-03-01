package com.paimon.QLBanVePaimon.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Bases;

@Repository
public interface BasesRepository extends MongoRepository<Bases,String>{
    
    Page<Bases> findAll(Pageable pageable);

    
}
