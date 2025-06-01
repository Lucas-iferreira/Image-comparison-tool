package io.github.lucasiferreira.facecomparison.controller;

import io.github.lucasiferreira.facecomparison.service.ComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/facecomparison", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComparisonController {

    @Autowired
    private ComparisonService service;

    @GetMapping("/status")
    public Status getStatus() {
        return new Status("OK AND RUNNING!");
    }

    @PostMapping(path = "/compare", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OutPut compare(@RequestBody  Input input) throws Exception {
        try {
            float result = service.compareTwoPhotos(input.photo1, input.photo2);
            return new OutPut(result);

        } catch (Exception e) {
            e.printStackTrace();
            return new OutPut(-1);
        }
    }

    record Status(String status) {
    }

    record Input(String photo1, String photo2) {
    }

    record OutPut(float similarity) {
    }


}
