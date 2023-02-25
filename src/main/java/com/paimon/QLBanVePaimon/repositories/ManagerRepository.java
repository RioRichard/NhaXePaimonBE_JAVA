package com.paimon.QLBanVePaimon.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Manager;



@Repository
public interface ManagerRepository extends MongoRepository<Manager,ObjectId>{

    Page<Manager> findAll(Pageable pageable);
        

    
}
