package com.ryan.opensearch_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ryan
 */
@ConfigurationProperties(prefix = "ryan.custom")
@Component
@Data
public class OpensearchProperties {

    private String jasyptPassword;

    private String accessKey;

    private String secret;

    private String host;

    private String appName;
}
