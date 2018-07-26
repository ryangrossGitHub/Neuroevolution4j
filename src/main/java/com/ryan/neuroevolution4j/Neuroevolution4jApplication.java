package com.ryan.neuroevolution4j;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.ryan.neuroevolution4j.models.Network;
import com.ryan.neuroevolution4j.services.NetworkService;

@SpringBootApplication
public class Neuroevolution4jApplication {
	
	public static List<Network> bestNetworks;

	public static void main(String[] args) {
		SpringApplication.run(Neuroevolution4jApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void boot() {
		bestNetworks = NetworkService.getFirstGeneration();
	}
}
