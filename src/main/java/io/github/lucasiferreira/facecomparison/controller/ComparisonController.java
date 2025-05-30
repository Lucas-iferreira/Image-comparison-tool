package io.github.lucasiferreira.facecomparison.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/facecomparison", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComparisonController {

    @GetMapping("/status")
    public Status getStatus(){
        return new Status("OK AND RUNNING!");
    }

    record Status(String status){}
    record ComparisonInput(String photo1, String photo2){}
    record ComparisonOutPut(float similarity){}
}
