package com.paimon.QLBanVePaimon.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.paimon.QLBanVePaimon.models.Routes;

public interface RoutesRepository extends MongoRepository<Routes,String>{
    

    Page<Routes> findAll(Pageable pageable);

}
