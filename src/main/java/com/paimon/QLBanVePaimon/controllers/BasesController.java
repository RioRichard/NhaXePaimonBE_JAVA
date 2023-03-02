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
import com.paimon.QLBanVePaimon.models.Bases;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.services.BasesService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("bases")
public class BasesController {
    
    @Autowired
    private BasesService basesService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {

        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var data = basesService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "bases", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "bases", null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = basesService.getId(id);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "bases", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "bases", null);

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Bases bases) {
        try {
            var res = basesService.add(bases);
            return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.OK, "bases", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "bases", null);

        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patch(@PathVariable String id, @RequestBody PatchRequest<Bases> patchBases) {
        try {
            var res = basesService.patch(id, patchBases);
            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "bases", res);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "bases", null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id, @RequestBody Bases bases){
        var update = basesService.edit(id, bases);
        return ResponseHandler.generateMessage("Cập Nhật Thành Công", HttpStatus.OK, "managers", update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id){
        var delete = basesService.delete(id);
        return ResponseHandler.generateMessage("Xóa Thành Công", HttpStatus.OK, "managers", delete);
    }



}
