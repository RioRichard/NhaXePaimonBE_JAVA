package com.paimon.QLBanVePaimon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


// import com.paimon.QLBanVePaimon.models.Bases;
import com.paimon.QLBanVePaimon.models.Routes;
import com.paimon.QLBanVePaimon.repositories.BasesRepository;
import com.paimon.QLBanVePaimon.repositories.BusesRepository;
import com.paimon.QLBanVePaimon.repositories.OrdersRepository;
import com.paimon.QLBanVePaimon.repositories.RoutesRepository;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class RoutesService {
    
    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private BusesRepository busesRepository;
    @Autowired
    private BasesRepository basesRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public ListData<Routes> getAll(Pageable pageable) {

        var data = routesRepository.findAll(pageable);
        
        return new ListData<Routes>(data);
    }

    public Routes getById(String id) {
        return routesRepository.findById(id).get();
    }

    public Routes add(Routes route) throws Exception {
        var bus = busesRepository.findById(route.getBus_id());
        var from = basesRepository.findById(route.getFrom_id());
        var to = basesRepository.findById(route.getTo_id());
        if (bus == null || from == null || to == null || from == to) {
            throw new Exception("Kiểm tra bus hoặc điểm đi hoặc điểm tới là hợp lệ");
        }
        route.setBus(bus.get());
        route.setFrom(from.get());
        route.setTo(to.get());
        routesRepository.insert(route);
        return null;
    }

   
}
