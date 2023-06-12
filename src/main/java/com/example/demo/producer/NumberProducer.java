package com.example.demo.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class NumberProducer {

    private final KafkaProducer<String, Integer> producer;

    @Value("${kafka.topic}")
    private String TOPIC;

    @PostConstruct
    public void postConstruct() {
        System.out.println("Producer is created.");
    }


    public void sendNumbers(int number) {
        for (int i = 0; i < number; i++) {
            String partition;
            if (isPrime(i)) {
                partition = "prime";
            } else {
                partition = "non-prime";
            }
            ProducerRecord record = new ProducerRecord<>(TOPIC, Integer.valueOf(i));
            producer.send(record, (RecordMetadata metadata, Exception e) -> {
               if (e != null) {
                   System.out.println("Error during publishing message: " + e.getMessage());
               } else {
                   System.out.println("Published message key = " + record.key() +
                           ", value = " + record.value() +
                           ", topic = " + metadata.topic() +
                           ", partition = " + metadata.partition() +
                           ", offset = " + metadata.offset());
               }
            });
        }
//      Not needed to call these
//      producer.flush();
//      producer.close();
    }

    private boolean isPrime(int n) {
        if (n<= 1) {
            return false;
        }
        for (int i = 2; i< n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
