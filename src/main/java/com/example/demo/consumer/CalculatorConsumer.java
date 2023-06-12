package com.example.demo.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.AuthenticationException;
import org.apache.kafka.common.errors.InvalidOffsetException;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CalculatorConsumer {
    private final KafkaConsumer<String, Integer> consumer;

    @Value("${kafka.topic}")
    private String TOPIC;

    @EventListener(ApplicationReadyEvent.class)
    @Async
    public void postConstruct() {
        System.out.println("Consumer is created.");
        //Subscribe to your topic
        // Poll records "forever" and print out the ConsumerRecord<Key, Value> on every TopicPartition (you can get via records.partitions())
        // Commit the offset to the NEXT message that your application will read!
        // Handle exceptions sent thrown by the poll() method
        // CLose the consumer once your polling is done
    }
}
