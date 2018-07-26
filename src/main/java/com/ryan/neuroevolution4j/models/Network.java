package com.ryan.neuroevolution4j.models;

import java.util.ArrayList;
import java.util.List;

import com.ryan.neuroevolution4j.Constants;

//Perceptron network
public class Network {
	private List<Layer> layers;
	private double score;
	private double[] inputs;
	
	public Network() {
		layers = new ArrayList<Layer>();
		setInputs(new double[Constants.inputLayers]);
	}
	
	public List<Layer> getLayers(){
		return this.layers;
	}
	
	public void setLayers(int index, Layer layer) {
		if(this.layers.size() <= index) {
			this.layers.add(layer);
		}
		this.layers.set(index, layer);
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double[] getInputs() {
		return inputs;
	}

	public void setInputs(double[] inputs) {
		this.inputs = inputs;
	}
}
