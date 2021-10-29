package com.example.webflux_study.pubsubpattern;

import com.example.webflux_study.rxjava.RxSeeEmitter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class TemperatureController {

    private final TemperatureSensor temperatureSensor;

    @GetMapping("/temperature-stream")
    public SseEmitter events() {
        final RxSeeEmitter emitter = new RxSeeEmitter();

        temperatureSensor.temperatureStream()
                .subscribe(emitter.getSubscriber());

        return emitter;
    }

}
