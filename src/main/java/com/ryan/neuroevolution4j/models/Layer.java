package com.ryan.neuroevolution4j.models;

import java.util.ArrayList;
import java.util.List;

public class Layer {
	private List<Neuron> neurons;
	
	public Layer() {
		this.neurons = new ArrayList<Neuron>();
	}
	
	public Layer(int numberOfNeurons, int numberOfInputs) {
		this();
		
		for (int i = 0; i < numberOfNeurons; i++) {
			Neuron neuron = new Neuron(numberOfInputs);
			this.neurons.add(neuron);
		}
	}
	
	public List<Neuron> getNeurons(){
		return this.neurons;
	}
	
	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}
}
