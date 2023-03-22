package com.paimon.QLBanVePaimon.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Staffs;
import com.paimon.QLBanVePaimon.repositories.StaffsRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class StaffsService {
    
    @Autowired
    private StaffsRepository staffsRepository;

    public ListData<Staffs> getAll(Pageable pageable) {

        var data = staffsRepository.findAll(pageable);
        return new ListData<>(data);
    }

    public Optional<Staffs> getId(String id) {
        return staffsRepository.findById(id);
    }

    public Staffs add(Staffs staffs) {
        var id = new ObjectId();
        staffs.setId(id.toString());
        return staffsRepository.insert(staffs);
    }

    public Staffs patch(String id, PatchRequest<Staffs> patchStaffs) {
        var dataChanging = staffsRepository.findById(id).get();

        for (String propString : patchStaffs.getPropChanging()) {
            var valueFromPatchData = Helper.get(patchStaffs.getData(), propString);
            Helper.set(dataChanging, propString, valueFromPatchData);
        }
        staffsRepository.save(dataChanging);
        return dataChanging;

    }

    public Staffs edit(String id ,Staffs staffs){

        Staffs updateStaffs = staffsRepository.findById(id).get();
        updateStaffs.setName(staffs.getName());
        updateStaffs.setAddress(staffs.getAddress());
        updateStaffs.setPhone(staffs.getPhone());
        updateStaffs.setPosition(staffs.getPosition());
        return staffsRepository.save(updateStaffs);

    }

    public Staffs delete(String id){
        Staffs deleteStaffs = staffsRepository.findById(id).get();
        staffsRepository.deleteById(id);
        return deleteStaffs;
    }

}
