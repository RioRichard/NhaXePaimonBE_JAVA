package com.paimon.QLBanVePaimon.services;

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
        for (Orders orders : data) {
            var route = routesRepository.findByListOrdersContaining(new ObjectId(orders.getId()));
            if (route!=null) {
                orders.setRoute(route.get(0));
            }
        }
        return new ListData<>(data);
    }

    public Orders getId(String id) {

        var orders = ordersRepository.findById(id).get();
        var route = routesRepository.findByListOrdersContaining(new ObjectId(orders.getId()));
            if (route!=null) {
                orders.setRoute(route.get(0));
            }
        return orders;
    }

    public Orders add(Orders orders) {
        var id = new ObjectId();
        orders.setId(id.toString());

        var route = routesRepository.findById(orders.getRouteId()).get();
        var listOrders = route.getListOrders();
        listOrders.add(id);
        route.setListOrders(listOrders);
        routesRepository.save(route);
        return ordersRepository.insert(orders);

        // return mongoTemplate.insert(orders, "orders");
    }

    public Orders edit(String id, Orders orders) {

        var editingOrders = ordersRepository.findById(id).get();

        editingOrders.setStatus(orders.getStatus());
        editingOrders.setPaymentInfo(orders.getPaymentInfo());
        return ordersRepository.save(editingOrders);

    }

    // public Orders delete(String id) {
    //     Orders deleteOrders = ordersRepository.findById(id).get();
    //     ordersRepository.deleteById(id);
    //     return deleteOrders;
    // }

    /*
     * public Bases patch(String id, PatchRequest<Bases> patchBases) {
     * var dataChanging = basesRepository.findById(id).get();
     * 
     * for (String propString : patchBases.getPropChanging()) {
     * var valueFromPatchData = Helper.get(patchBases.getData(), propString);
     * Helper.set(dataChanging, propString, valueFromPatchData);
     * }
     * basesRepository.save(dataChanging);
     * return dataChanging;
     * 
     * }
     * 
     * 
     * 
     */
}
