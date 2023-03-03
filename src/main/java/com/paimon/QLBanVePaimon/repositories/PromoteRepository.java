package com.paimon.QLBanVePaimon.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Promote;

@Repository
public interface PromoteRepository extends MongoRepository<Promote,String>{
    
    Page<Promote> findAll(Pageable pageable);

}
