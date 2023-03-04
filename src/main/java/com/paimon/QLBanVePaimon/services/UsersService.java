package com.paimon.QLBanVePaimon.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paimon.QLBanVePaimon.Helper;
import com.paimon.QLBanVePaimon.models.Users;
import com.paimon.QLBanVePaimon.repositories.UsersRepository;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.sideModels.ListData;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository usersRepository;

    public ListData<Users> getAll(Pageable pageable) {

        var data = usersRepository.findAll(pageable);
        return new ListData<>(data);
    }

    public Optional<Users> getId(String id) {
        return usersRepository.findById(id);
    }

    public Users add(Users users) {
        var id = new ObjectId();
        users.setId(id.toString());
        return usersRepository.insert(users);
    }

    public Users patch(String id, PatchRequest<Users> patchUsers) {
        var dataChanging = usersRepository.findById(id).get();

        for (String propString : patchUsers.getPropChanging()) {
            var valueFromPatchData = Helper.get(patchUsers.getData(), propString);
            Helper.set(dataChanging, propString, valueFromPatchData);
        }
        usersRepository.save(dataChanging);
        return dataChanging;

    }

    public Users edit(String id ,Users users){

        Users updateUsers = usersRepository.findById(id).get();
        updateUsers.setUsername(users.getUsername());
        updateUsers.setPassword(users.getPassword());
        updateUsers.setName(users.getName());
        updateUsers.setEmail(users.getEmail());
        updateUsers.setPhone(users.getPhone());
        return usersRepository.save(updateUsers);

    }

    public Users delete(String id){
        Users deleteUsers = usersRepository.findById(id).get();
        usersRepository.deleteById(id);
        return deleteUsers;
    }
}
