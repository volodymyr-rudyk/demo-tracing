package org.example.demotracing.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfiguration {

//    @Bean
    MeterRegistry meterRegistry() {
        LoggingMeterRegistry meterRegistry = new LoggingMeterRegistry();

        return meterRegistry;

    }

}
