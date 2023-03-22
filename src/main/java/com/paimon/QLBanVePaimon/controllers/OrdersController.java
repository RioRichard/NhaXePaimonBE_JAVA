package com.paimon.QLBanVePaimon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.paimon.QLBanVePaimon.models.Orders;
import com.paimon.QLBanVePaimon.services.OrdersService;
import com.paimon.QLBanVePaimon.sideModels.ResponseHandler;

@RestController
@RequestMapping("orders")

public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = AppConstant.MIN_PAGE, required = false) int page,
            @RequestParam(value = "size", defaultValue = AppConstant.MAX_SIZE_QUERY, required = false) int size) {

        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            var data = ordersService.getAll(pageable);
            return ResponseHandler.generateMessage(null, HttpStatus.OK, "orders", data);
        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "orders", null);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable String id) {
        try {
            var data = ordersService.getId(id);
            return ResponseHandler.generateMessage("Lấy thành công", HttpStatus.OK, "orders", data);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "orders", null);

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody Orders orders) {
        try {
            var res = ordersService.add(orders);
            return ResponseHandler.generateMessage("Tạo thành công", HttpStatus.OK, "orders", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "orders", null);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable("id") String id, @RequestBody Orders orders) {
        try {
            var res = ordersService.edit(id, orders);
            return ResponseHandler.generateMessage("Sửa thành công", HttpStatus.OK, "orders", res);

        } catch (Exception e) {
            return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "orders", null);

        }

    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Object> delete(@PathVariable("id") String id) {
    //     try {
    //         var res = ordersService.delete(id);
    //         return ResponseHandler.generateMessage("Xóa thành công", HttpStatus.OK, "orders", res);

    //     } catch (Exception e) {
    //         return ResponseHandler.generateMessage(e.getMessage(), HttpStatus.MULTI_STATUS, "orders", null);

    //     }

    // }

}
