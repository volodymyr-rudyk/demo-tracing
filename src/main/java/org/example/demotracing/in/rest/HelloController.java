package org.example.demotracing.in.rest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping()
public class HelloController {

    private final MeterRegistry meterRegistry;
    private final ObservationRegistry observationRegistry;

    public HelloController(MeterRegistry meterRegistry, ObservationRegistry observationRegistry) {
        this.meterRegistry = meterRegistry;
        this.observationRegistry = observationRegistry;
    }

    @GetMapping("/hello")
    String hello() {

        Timer ttt = Timer.builder("ttt")
                .register(meterRegistry);

        return ttt.record(() -> Observation.createNotStarted("request.hello", observationRegistry)
                .contextualName("--123sdd")
                .observe(() -> {
                    if (System.currentTimeMillis() % 2 == 0) {
                        return "hello";
                    }
                    throw new IllegalStateException("Try again later");
                }));

    }

    //    @Observed
    @GetMapping("/go")
    String go() {
        if (System.currentTimeMillis() % 2 == 0) {
            return "go";
        }
        throw new IllegalStateException("GO GO Try again later");
    }



}
