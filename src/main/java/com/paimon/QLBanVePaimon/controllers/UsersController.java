package com.paimon.QLBanVePaimon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paimon.QLBanVePaimon.AppConstant;
import com.paimon.QLBanVePaimon.models.Users;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.services.UsersService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("users")
public class UsersController {
    
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {

        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var data = usersService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "users", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "users", null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = usersService.getId(id);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "users", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "users", null);

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Users users) {
        try {
            var res = usersService.add(users);
            return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.OK, "users", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "users", null);

        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patch(@PathVariable String id, @RequestBody PatchRequest<Users> patchUsers) {
        try {
            var res = usersService.patch(id, patchUsers);
            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "users", res);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "users", null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id, @RequestBody Users users){
        var update = usersService.edit(id, users);
        return ResponseHandler.generateMessage("Cập Nhật Thành Công", HttpStatus.OK, "users", update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id){
        var delete = usersService.delete(id);
        return ResponseHandler.generateMessage("Xóa Thành Công", HttpStatus.OK, "users", delete);
    }

}
