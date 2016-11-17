package com.example;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(ProducerChannels.class)
@SpringBootApplication
public class ProducerApplication {
	
	private final MessageChannel greeterChannel;
	
	public ProducerApplication(ProducerChannels channels) {
		this.greeterChannel = channels.greeter();
	}
	
	@PostMapping("/greet/{name}")
	public void publish(@PathVariable String name) {
		String greeting = "Hello " + name + "!";
		Message<String> msg = MessageBuilder.withPayload(greeting).build();
		this.greeterChannel.send(msg);
		Logger.getLogger(this.getClass().getName()).info("sending message: " + name);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}

interface ProducerChannels {
	@Output
	MessageChannel greeter();
}