package com.paimon.QLBanVePaimon.services;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Buses;
import com.paimon.QLBanVePaimon.models.Seat;
import com.paimon.QLBanVePaimon.repositories.BusesRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BusesService {
    @Autowired
    private BusesRepository busesRepository;

    public ListData<Buses> getAll(Pageable pageable) {

        var data = busesRepository.findAll(pageable);
        return new ListData<>(data);
    }

    public Optional<Buses> getId(String id) {
        return busesRepository.findById(id);
    }

    public Buses add(Buses buses) {
        var id = new ObjectId();
        buses.setId(id.toString());
        for (Seat seat : buses.getSeats()) {
            var seatId = new ObjectId();
            seat.setId(seatId.toString());
        }
        return busesRepository.insert(buses);
    }

    public Buses patch(String id, PatchRequest<Buses> patchBases) {
        var dataChanging = busesRepository.findById(id).get();

        for (String propString : patchBases.getPropChanging()) {
            var valueFromPatchData = Helper.get(patchBases.getData(), propString);
            Helper.set(dataChanging, propString, valueFromPatchData);
        }
        busesRepository.save(dataChanging);
        return dataChanging;

    }

    public Buses edit(String id ,Buses buses){

        Buses updateBuses = busesRepository.findById(id).get();
        updateBuses.setBus_number(buses.getBus_number());
        updateBuses.setType(buses.getType());
        updateBuses.setSeats(buses.getSeats());
        return busesRepository.save(updateBuses);

    }

    public Buses delete(String id){
        Buses deleteBuses = busesRepository.findById(id).get();
        busesRepository.deleteById(id);
        return deleteBuses;
    }
}
