package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageProducerService {
    List<String> messages=new ArrayList<>();

    @Autowired
	private KafkaTemplate<String, String> kafka;

    public void send(String msg) {
        kafka.send("messages", msg);
    }
}

