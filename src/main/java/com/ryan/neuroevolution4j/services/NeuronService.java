package com.ryan.neuroevolution4j.services;

public class NeuronService {

	public static Double getRandomWeight() {
		int min = -1;
		int max = 1;
		return min + Math.random() * (max - min);
	}
}
