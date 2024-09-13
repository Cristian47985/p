package com.example.cqrs.sync.controllers;

import com.example.cqrs.sync.SycnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncController {
    @Autowired
    private SycnService dataSyncService;

    @PostMapping
    public String syncData() {
        dataSyncService.sycn();
        return "Data synchronized successfully!";
    }
}
