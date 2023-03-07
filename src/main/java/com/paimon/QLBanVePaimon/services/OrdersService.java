package com.paimon.QLBanVePaimon.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Orders;
import com.paimon.QLBanVePaimon.models.Users;
import com.paimon.QLBanVePaimon.repositories.OrdersRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;

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
        return ordersRepository.insert(orders);
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

    public Bases edit(String id ,Bases bases){

        Bases updateBases = basesRepository.findById(id).get();
        updateBases.setName(bases.getName());
        updateBases.setAddress(bases.getAddress());
        return basesRepository.save(updateBases);

    }

    public Bases delete(String id){
        Bases deleteBases = basesRepository.findById(id).get();
        basesRepository.deleteById(id);
        return deleteBases;
    } */
}
