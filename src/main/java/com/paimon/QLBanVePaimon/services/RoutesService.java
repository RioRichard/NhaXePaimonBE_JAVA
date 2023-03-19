package com.paimon.QLBanVePaimon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


// import com.paimon.QLBanVePaimon.models.Bases;
import com.paimon.QLBanVePaimon.models.Routes;
import com.paimon.QLBanVePaimon.repositories.RoutesRepository;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class RoutesService {
    
    @Autowired
    private RoutesRepository routesRepository;

    public ListData<Routes> getAll(Pageable pageable) {

        var data = routesRepository.findAll(pageable);
        return new ListData<Routes>(data);
    }

    public Routes getById(String id) {
        return routesRepository.findById(id).get();
    }
}
