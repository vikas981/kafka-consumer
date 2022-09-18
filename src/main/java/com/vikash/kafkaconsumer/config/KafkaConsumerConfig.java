package com.vikash.kafkaconsumer.config;

import com.vikash.kafkaconsumer.config.data.KafkaConfigData;
import com.vikash.kafkaconsumer.config.data.KafkaConsumerConfigData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConsumerConfigData kafkaConsumerConfigData;

    @Autowired
    private KafkaConfigData kafkaConfigData;


    @Bean
    public Map<String,Object> consumerConfig(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaConfigData.getBootStrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,kafkaConsumerConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,kafkaConsumerConfigData.getValueDeserializer());
       // props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,kafkaConsumerConfigData.getHeartbeatIntervalMS());
        return props;
    }

    @Bean
    public ConsumerFactory<String,Object> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Object> concurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
