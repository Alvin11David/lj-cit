package org.example.capstoneapi.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "capstone-api")
@Getter
@Setter
public class CapstoneApiProperties {
    private String greeting;
    private int pagesize;
}
