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
import com.paimon.QLBanVePaimon.models.Staffs;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.services.StaffsService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("staffs")
public class StaffsController {
    
    @Autowired
    private StaffsService staffsService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {

        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var data = staffsService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "staffs", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "staffs", null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = staffsService.getId(id);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "staffs", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "staffs", null);

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Staffs staffs) {
        try {
            var res = staffsService.add(staffs);
            return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.OK, "staffs", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "staffs", null);

        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patch(@PathVariable String id, @RequestBody PatchRequest<Staffs> patchStaffs) {
        try {
            var res = staffsService.patch(id, patchStaffs);
            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "staffs", res);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "staffs", null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id, @RequestBody Staffs staffs){
        var update = staffsService.edit(id, staffs);
        return ResponseHandler.generateMessage("Cập Nhật Thành Công", HttpStatus.OK, "staffs", update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id){
        var delete = staffsService.delete(id);
        return ResponseHandler.generateMessage("Xóa Thành Công", HttpStatus.OK, "staffs", delete);
    }

}
