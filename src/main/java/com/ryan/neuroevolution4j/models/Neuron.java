package com.ryan.neuroevolution4j.models;

import java.util.ArrayList;
import java.util.List;

import com.ryan.neuroevolution4j.services.NeuronService;

public class Neuron {
	private Double value;
	private List<Double> weights;
	
	public Neuron() {
		this.value = 0.0;
		this.weights = new ArrayList<Double>();
	}
	
	public Neuron(int numberOfWeights) {
		this();
		for (int i = 0; i < numberOfWeights; i++) {
			weights.add(NeuronService.getRandomWeight());
		}
	}
	
	public Double getValue() {
		return this.value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public List<Double> getWeights(){
		return this.weights;
	}
	
	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}
	
	public void setWeight(int index, Double weight) {
		this.weights.set(index, weight);
	}
}
