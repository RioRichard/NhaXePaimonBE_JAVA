package com.paimon.QLBanVePaimon.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Promote;
import com.paimon.QLBanVePaimon.repositories.PromoteRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class PromoteService {

    @Autowired
    private PromoteRepository promoteRepository;

    public ListData<Promote> getAll(Pageable pageable) {

        var data = promoteRepository.findAll(pageable);
        return new ListData<Promote>(data);

    }

    public Object add(Promote promote) {
        var id = new ObjectId();
        promote.setId(id.toString());
        return promoteRepository.insert(promote);
    }

    public Object patch(String id, PatchRequest<Promote> patchPromote) {
        var dataChanging = promoteRepository.findById(id).get();

        for (String propString : patchPromote.getPropChanging()) {
            var valueFromPatchData = Helper.get(patchPromote.getData(), propString);
            Helper.set(dataChanging, propString, valueFromPatchData);
        }
        promoteRepository.save(dataChanging);
        return dataChanging;
    }

    public Object edit(String id, Promote promote) {
        Promote updateManager = promoteRepository.findById(id).get();
        updateManager.setAmount(promote.getAmount());
        updateManager.setFrom(promote.getFrom());
        updateManager.setTo(promote.getTo());
        updateManager.setType(promote.getType());
        
        return promoteRepository.save(updateManager);
    }

    public void delete(String id) {
        promoteRepository.deleteById(id);
        return;
    }

    public Object getId(String id) {
        return promoteRepository.findById(id);

    }

}
