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
import com.paimon.QLBanVePaimon.models.Promote;
import com.paimon.QLBanVePaimon.requestModel.PatchRequest;
import com.paimon.QLBanVePaimon.services.PromoteService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("promotes")
public class PromoteController {

    @Autowired
    private PromoteService promoteService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {

        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var data = promoteService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "promote", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "promote", null);

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = promoteService.getId(id);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "promote", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "promote", null);

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Promote promote) {
        try {
            var res = promoteService.add(promote);
            return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.OK, "promote", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "promote", null);

        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patch(@PathVariable String id, @RequestBody PatchRequest<Promote> patchPromote) {
        try {
            var res = promoteService.patch(id, patchPromote);
            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "promote", res);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "promote", null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id, @RequestBody Promote manager) {
        var update = promoteService.edit(id, manager);
        return ResponseHandler.generateMessage("Cập Nhật Thành Công", HttpStatus.OK, "promote", update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id) {
        promoteService.delete(id);
        return ResponseHandler.generateMessage("Xóa Thành Công", HttpStatus.OK, "promote", null);
    }

}
