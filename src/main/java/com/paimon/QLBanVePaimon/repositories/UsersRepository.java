package com.paimon.QLBanVePaimon.repositories;

import org.springframework.stereotype.Repository;

import com.paimon.QLBanVePaimon.models.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<Users,String>{
    
    Page<Users> findAll(Pageable pageable);

}
