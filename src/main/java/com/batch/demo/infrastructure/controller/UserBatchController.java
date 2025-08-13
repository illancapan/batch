package com.batch.demo.infrastructure.controller;


import com.batch.demo.application.service.UserBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/batch")
public class UserBatchController {

    private final UserBatchService userBatchService;

    public ResponseEntity<String> runBatchJob() {
        try {
            userBatchService.runBatch();
            return ResponseEntity.ok("Batch job started successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error running batch job: " + e.getMessage());
        }
    }

}
