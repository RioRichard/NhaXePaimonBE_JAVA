package com.paimon.QLBanVePaimon.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Bases;
import com.paimon.QLBanVePaimon.repositories.BasesRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class BasesService {
    
    @Autowired
    private BasesRepository basesRepository;

    public ListData<Bases> getAll(Pageable pageable) {

        var data = basesRepository.findAll(pageable);
        return new ListData<>(data);
    }

    public Optional<Bases> getId(String id) {
        return basesRepository.findById(id);
    }

    public Bases add(Bases bases) {
        var id = new ObjectId();
        bases.setId(id.toString());
        return basesRepository.insert(bases);
    }

    public Bases patch(String id, PatchRequest<Bases> patchBases) {
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
    }

}
