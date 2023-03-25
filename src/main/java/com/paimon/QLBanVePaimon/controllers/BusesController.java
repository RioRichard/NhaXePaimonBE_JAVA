package com.paimon.QLBanVePaimon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paimon.QLBanVePaimon.AppConstant;
import com.paimon.QLBanVePaimon.models.Buses;
import com.paimon.QLBanVePaimon.services.BusesService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("buses")
@CrossOrigin("*")

public class BusesController {

    @Autowired
    private BusesService busesService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {

        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var data = busesService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "buses", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST, "buses", null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = busesService.getId(id);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "buses", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST, "buses", null);

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Buses buses) {
        try {
            var res = busesService.add(buses);
            return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.CREATED, "buses", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST, "buses", null);

        }

    }

    // @PatchMapping("{id}")
    // public ResponseEntity<Object> patch(@PathVariable String id, @RequestBody PatchRequest<Buses> patchBuses) {
    //     try {
    //         var res = busesService.patch(id, patchBuses);
    //         return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "buses", res);
    //     } catch (Exception e) {
    //         return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST, "buses", null);
    //     }

    // }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id, @RequestBody Buses buses) {

        try {
            var update = busesService.edit(id, buses);

            return ResponseHandler.generateMessage("Lưu thành công", HttpStatus.OK, "buses", update);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST, "buses", null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id) {

        try {
            var delete = busesService.delete(id);
            return ResponseHandler.generateMessage("Xóa Thành Công", HttpStatus.OK, "buses", delete);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.BAD_REQUEST, "buses", null);

        }
    }

}
