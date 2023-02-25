package com.paimon.QLBanVePaimon.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.models.Manager;
import com.paimon.QLBanVePaimon.repositories.ManagerRepository;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    
    
    public ListData<Manager> getAll(Pageable pageable) {
        
        var data = managerRepository.findAll(pageable);
        return new ListData<>(data);
        
    }
}
