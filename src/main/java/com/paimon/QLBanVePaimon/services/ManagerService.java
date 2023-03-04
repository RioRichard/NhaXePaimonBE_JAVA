package com.paimon.QLBanVePaimon.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Manager;
import com.paimon.QLBanVePaimon.repositories.ManagerRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

import lombok.var;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public ListData<Manager> getAll(Pageable pageable) {

        var data = managerRepository.findAll(pageable);
        return new ListData<>(data);

    }

    public Optional<Manager> getId(String id) {
        return managerRepository.findById(id);
    }

    public Manager add(Manager manager) {
        var id = new ObjectId();
        manager.setId(id.toString());
        var hasedPass = Helper.hash256(manager.getId() + manager.getPass());
        manager.setPass(hasedPass);
        System.out.println(hasedPass);
        return managerRepository.insert(manager);
    }

    public Manager edit(String id, Manager manager) {

        Manager updateManager = managerRepository.findById(id).get();
        updateManager.setUsername(manager.getUsername());

        updateManager.setPass(Helper.hash256(id + manager.getPass()));
        updateManager.setEmail(manager.getEmail());
        updateManager.setPhone(manager.getPhone());
        updateManager.setRole(manager.getRole());
        return managerRepository.save(updateManager);

    }

    public void delete(String id) {

        managerRepository.deleteById(id);
        return;
    }

    public Manager patch(String id, PatchRequest<Manager> patchManager) {
        var dataChanging = managerRepository.findById(id).get();

        for (String propString : patchManager.getPropChanging()) {
            var valueFromPatchData = Helper.get(patchManager.getData(), propString);
            if (propString == "password") {
                var managerId = Helper.get(patchManager.getData(), "id");
                valueFromPatchData = Helper.hash256(managerId + valueFromPatchData.toString());
            }
            Helper.set(dataChanging, propString, valueFromPatchData);
        }
        managerRepository.save(dataChanging);
        return dataChanging;

    }

}
