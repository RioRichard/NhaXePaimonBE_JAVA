package com.paimon.QLBanVePaimon.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.paimon.QLBanVePaimon.models.Routes;

@Repository
public interface RoutesRepository extends MongoRepository<Routes,String>{
    

    Page<Routes> findAll(Pageable pageable);

    Routes findByOrders(String id);
    // List<Routes> findByOrdersContaining(String orders);
    List<Routes> findByListOrdersContaining(ObjectId objectId);

}
