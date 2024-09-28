package org.example.demotracing.in.rest;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class VisitorController {

    private final MeterRegistry meterRegistry;
    private final AtomicInteger visitors;

    public VisitorController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.visitors = this.meterRegistry.gauge("visitors_active", new AtomicInteger(0));
    }

    @GetMapping("/visit")
    String visit() {
        return "visit " + visitors.incrementAndGet();
    }

    @GetMapping("/leave")
    String leave() {
        return "leave " + visitors.decrementAndGet();
    }
}
