package com.paimon.QLBanVePaimon.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.paimon.QLBanVePaimon.models.Orders;

@Repository
public interface OrdersRepository extends MongoRepository<Orders,String> {
    
    Page<Orders> findAll(Pageable pageable);
    List<Orders> findByIdIn(List<String> ids);
    List<Orders> findByUser(ObjectId userId);
    

    
    // List<Orders> findByUsers(Users users);

}
