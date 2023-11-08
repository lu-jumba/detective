package com.example.detective.controller;

import com.example.detective.service.SupportOrderService;

import com.example.detective.entities.SupportOrder;
import com.example.detective.handler.Response;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 /*
 * @author Lugwiri
 */
@RestController
@RequestMapping("/api/support")

public class SupportOrderController {
    @Autowired
    private SupportOrderService supportOrderService;
    
    @GetMapping("/reportUuid/listSupportOrders")
    public ResponseEntity <Response <List<SupportOrder>>> listSupportOrders(
        @PathVariable("reportUuid") 
        @RequestParam String reportUuid){
             Response <List<SupportOrder>> response = supportOrderService.listSupportOrders(reportUuid);
             return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping ("/reportUuid/completeSupportOrder")
    public ResponseEntity <Response <Integer>> completeSupportOrder(@RequestBody String reportUuid){
        Response <Integer> response = supportOrderService.completeSupportOrder(reportUuid);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity<>(response, HttpStatus.OK);
            case 101 -> new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        };
    }
    
}
