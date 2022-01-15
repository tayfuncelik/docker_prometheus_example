package com.fluxsample.demo.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Api(value = "Control documentation")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("/getAll")
    @ApiOperation(value = "Get All")
    public List<String> getAll() throws InterruptedException {
        Thread.sleep(4000);
        return new ArrayList<>(Arrays.asList("DATA_1", "DATA_2", "DATA_3"));
    }


    @GetMapping("/getAllForReactive")
    @ApiOperation(value = "Get getAllForReactive")
    public Flux<String> getAllForReactive() {
        return getNonBlocking();
    }

    public Flux<String> getNonBlocking() {
        log.info("Starting NON-BLOCKING Controller!");
        Flux<String> fluxResponse = WebClient.create()
                .get()
                .uri("http://localhost:8080/api/getAll")
                .retrieve()
                .bodyToFlux(String.class);

        fluxResponse.subscribe(data -> log.info(data.toString()));
        log.info("Exiting NON-BLOCKING Controller!");
        return fluxResponse;
    }
}
