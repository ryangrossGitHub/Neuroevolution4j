package com.ryan.neuroevolution4j.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.neuroevolution4j.Neuroevolution4jApplication;
import com.ryan.neuroevolution4j.models.Network;
import com.ryan.neuroevolution4j.services.NetworkService;

@RestController
@RequestMapping("/Neuroevolution4j")
public class MainController {

	/**
	 * 
	 * Client uploads their most recent generation of networks with scores
	 * and in return gets the next generation of networks bred from the best
	 * networks of all clients
	 * 
	 * @param network
	 * @return
	 */
	@PostMapping("/")
	public List<Network> score(@RequestBody(required=false) List<Network> networks) {
		
		if(networks != null) { //Client is getting it's first generation
			return NetworkService.submitFeedback(networks);
		}
		
		return Neuroevolution4jApplication.bestNetworks;
	}
}
