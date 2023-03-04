package com.paimon.QLBanVePaimon.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Staffs;

@Repository
public interface StaffsRepository extends MongoRepository<Staffs,String>{

    Page<Staffs> findAll(Pageable pageable);

}
