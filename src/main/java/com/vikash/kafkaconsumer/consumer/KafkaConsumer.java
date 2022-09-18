package com.vikash.kafkaconsumer.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.kafkaconsumer.config.data.KafkaConfigData;
import com.vikash.kafkaconsumer.config.data.KafkaConsumerConfigData;
import com.vikash.kafkaconsumer.model.Student;
import com.vikash.kafkaconsumer.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private KafkaConsumerConfigData kafkaConsumerConfigData;

    @Autowired
    private KafkaConfigData kafkaConfigData;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper mapper;


    @KafkaListener(topics = "#{kafkaConfigData.getTopicName()}",groupId = "group_id")
    public void consume(String message) throws JsonProcessingException {
        Student student = mapper.readValue(message,Student.class);
        if(Objects.nonNull(student)){
            if(studentRepository.existsByPhoneNumber(student.getPhoneNumber())){
              log.info("Student Already Exists With Phone Number {}",student.getPhoneNumber());
            }else{
                studentRepository.save(student);
            }
        }
        System.out.println(message);
    }
}
