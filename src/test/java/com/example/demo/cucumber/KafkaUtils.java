package com.example.demo.cucumber;

import io.cucumber.docstring.DocString;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class KafkaUtils {
   private final KafkaTemplate<String , String> kafkaTemplate;

   @When("I produce a message in topic {string}")
    public void produceInTopic(String topic, DocString message) {
       kafkaTemplate.send(topic, message.getContent());
   }

}
