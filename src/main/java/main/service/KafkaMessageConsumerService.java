package main.service;

import main.config.KafkaProducerConfiguration;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageConsumerService {

private final RedisTemplate<String, String> redisTemplate;

	List<String> messages=new ArrayList<>();

	public KafkaMessageConsumerService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
	}

	@KafkaListener(topics = "messages")
	public void onMessage(@Payload String msg) {
		messages.add(msg);
		String key = "key:" + System.currentTimeMillis();
		redisTemplate.opsForValue().set(key, msg);
	}

	public List<String> getAll(){
		List<String> res= new ArrayList<>(messages);
		return res;
	}
}

