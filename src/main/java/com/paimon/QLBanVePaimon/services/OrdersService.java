package com.paimon.QLBanVePaimon.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.models.Orders;
import com.paimon.QLBanVePaimon.repositories.OrdersRepository;
import com.paimon.QLBanVePaimon.repositories.RoutesRepository;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private RoutesRepository routesRepository;

    public ListData<Orders> getAll(Pageable pageable) {

        var data = ordersRepository.findAll(pageable);
        return new ListData<>(data);
    }

    public Optional<Orders> getId(String id) {
        
        return ordersRepository.findById(id);
    }

    

    public Orders add(Orders orders) {
        var id = new ObjectId();
        orders.setId(id.toString());
        // var route = routesRepository.findById(orders.getRouteId()).get();
        // var listOrders = route.getOrders();
        // listOrders.add(id.toString());
        // route.setOrders(listOrders);
        // routesRepository.save(route);
        ordersRepository.insert(orders);

        return orders;
    }

    public Orders edit(String id ,Orders orders){

        orders.setId(id);
        return ordersRepository.save(orders);

    }
    public Orders delete(String id){
        Orders deleteOrders = ordersRepository.findById(id).get();
        ordersRepository.deleteById(id);
        return deleteOrders;
    }

    /* public Bases patch(String id, PatchRequest<Bases> patchBases) {
        var dataChanging = basesRepository.findById(id).get();

        for (String propString : patchBases.getPropChanging()) {
            var valueFromPatchData = Helper.get(patchBases.getData(), propString);
            Helper.set(dataChanging, propString, valueFromPatchData);
        }
        basesRepository.save(dataChanging);
        return dataChanging;

    }

    

    */
}
