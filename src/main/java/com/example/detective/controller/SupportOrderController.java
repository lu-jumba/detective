package com.example.detective.controller;

import com.example.detective.service.SupportOrderService;
import com.example.detective.handler.Response;
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
    public ResponseEntity listSupportOrders(@PathVariable("reportUuid") String reportUuid){
        return new ResponseEntity(supportOrderService.listSupportOrders(reportUuid), HttpStatus.OK);
    }

    @PutMapping ("/reportUuid/completeSupportOrder")
    public ResponseEntity completeSupportOrder(@RequestBody String reportUuid){
        Response response = supportOrderService.completeSupportOrder(reportUuid);

        return switch (response.getCode()) {
            case 0 -> new ResponseEntity(response, HttpStatus.OK);
            case 101 -> new ResponseEntity(response, HttpStatus.CONFLICT);
            case 102 -> new ResponseEntity(response, HttpStatus.NOT_FOUND);
            default -> new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        };
    }
    
}
