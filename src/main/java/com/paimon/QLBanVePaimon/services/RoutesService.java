package com.paimon.QLBanVePaimon.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.models.Orders;
// import com.paimon.QLBanVePaimon.models.Bases;
import com.paimon.QLBanVePaimon.models.Routes;
import com.paimon.QLBanVePaimon.models.Seat;
import com.paimon.QLBanVePaimon.repositories.BasesRepository;
import com.paimon.QLBanVePaimon.repositories.BusesRepository;
import com.paimon.QLBanVePaimon.repositories.OrdersRepository;
import com.paimon.QLBanVePaimon.repositories.RoutesRepository;
import com.paimon.QLBanVePaimon.sideModels.ListData;

import lombok.var;

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

    public ListData<Routes> getAll(String fromId, String toId, Pageable pageable) {

        // Page<Routes> data = null;
        if (!fromId.isBlank() && !fromId.isEmpty() && !toId.isEmpty() && !toId.isBlank()) {
            return new ListData<Routes>(
                    routesRepository.findAllByFromAndTo(new ObjectId(fromId), new ObjectId(toId), pageable));

        }
        if ((fromId.isBlank() && !fromId.isEmpty()) && (toId.isEmpty() || toId.isBlank())) {
            return new ListData<Routes>(
                    routesRepository.findAllByFrom(new ObjectId(fromId), pageable));

        }
        if ((fromId.isBlank() || fromId.isEmpty()) && (!toId.isEmpty() && !toId.isBlank())) {
            return new ListData<Routes>(
                    routesRepository.findAllByTo(new ObjectId(toId), pageable));

        }
        return new ListData<Routes>(
                    routesRepository.findAll(pageable));
        // throw new Exception("Không có thông tin").


    }

    
    

    public Routes getById(String id) {
        var data = routesRepository.findById(id).get();
        List<Orders> orders = ordersRepository.findByIdIn(data.getOrders());
        for (Orders order : orders) {
            for (Seat item : order.getSeat()) {
                for (Seat seatInBus : data.getBus().getSeats()) {
                    if (item.getId().equals(seatInBus.getId()) ) {
                        
                        seatInBus.setStatus("true");
                    }
                }
            }
        }
        return data;
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

    public Routes edit(String id, Routes route) throws Exception {
        var routes = routesRepository.findById(id).get();
        var bus = busesRepository.findById(route.getBus_id());
        var from = basesRepository.findById(route.getFrom_id());
        var to = basesRepository.findById(route.getTo_id());
        if (bus == null || from == null || to == null || from == to) {
            throw new Exception("Kiểm tra bus hoặc điểm đi hoặc điểm tới là hợp lệ");
        }
        routes.setBus(bus.get());
        routes.setFrom(from.get());
        routes.setTo(to.get());
        return routesRepository.save(routes);
    }

}
