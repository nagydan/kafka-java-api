package com.example.demo.controller;

import com.example.demo.producer.NumberProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KafkaController {

    private final NumberProducer numberProducer;

    @GetMapping("/produce")
    public ResponseEntity procudeNumbers(@RequestParam Integer number) {
        System.out.println("Producer endpoint is called with number: " + number);
        numberProducer.sendNumbers(number);
        return ResponseEntity.ok().build();
    }
}
