package com.vikash.kafkaconsumer.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootStrapServers;
    private Integer noOfPartitions;
    private Short replicationFactor;
    private String topicName;
}
