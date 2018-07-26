package com.ryan.neuroevolution4j.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ryan.neuroevolution4j.Constants;
import com.ryan.neuroevolution4j.Neuroevolution4jApplication;
import com.ryan.neuroevolution4j.models.Layer;
import com.ryan.neuroevolution4j.models.Network;
import com.ryan.neuroevolution4j.models.Neuron;

public class NetworkService {
	
	public static List<Network> getFirstGeneration() {
		List<Network> networks = new ArrayList<Network>();
		
		for (var i = 0; i < Constants.population; i++) {
			Network network = new Network();
			//Input Layer
			network.setLayers(0, new Layer(Constants.inputLayers, Constants.inputLayerInputs));
			
			//Hidden Layer
			network.setLayers(1, new Layer(Constants.hiddenLayers, Constants.inputLayers));
			
			//Output Layer
			network.setLayers(2, new Layer(Constants.outputLayerNeurons, Constants.hiddenLayers));
			networks.add(network);
		}
		
		return networks;
	}

	/**
	 * Neuroevolution4jApplication.bestNetworks is sorted by highest scoring network
	 * 
	 * Look at each network in the bestNetwork list and compare to each of the new networks.
	 * If a new network is higher at this index than breed with best and insert it.
	 * Lastly bump the lowest score off the list.
	 */
	public static List<Network> submitFeedback(List<Network> networks){
		
		//Add new networks
		Neuroevolution4jApplication.bestNetworks.addAll(networks);
		
		//Sort by score
		Collections.sort(Neuroevolution4jApplication.bestNetworks, new Comparator<Network>(){
		     public int compare(Network n1, Network n2){
		         if(n1.getScore() == n2.getScore())
		             return 0;
		         return n1.getScore() > n2.getScore() ? -1 : 1;
		     }
		});
		
		//Natural selection
		Neuroevolution4jApplication.bestNetworks = Neuroevolution4jApplication.bestNetworks.subList(0, Constants.population);
		
		//Breeding
		List<Network> nextGeneration = new ArrayList<Network>();
		int unchangedNetworkCount = (int) Math.round(Neuroevolution4jApplication.bestNetworks.size() * Constants.elitism);
		for(int i=0; i<Neuroevolution4jApplication.bestNetworks.size(); i++) {
			if(i > unchangedNetworkCount) {
				nextGeneration.add(breed(Neuroevolution4jApplication.bestNetworks.get(i)));
			}else {
				nextGeneration.add(Neuroevolution4jApplication.bestNetworks.get(i)); //Best networks are protected from mutation
			}
		}
		
		return nextGeneration;
	}
	
	/**
	 * Breed new top network with the best of the best.
	 * The amount of DNA to clone from the best network is based on the elitism constant.
	 */
	private static Network breed(Network network) {
		double score = network.getScore();
		Network best = Neuroevolution4jApplication.bestNetworks.get(0);
		Network child = network;
		child.setScore(score); //Keep score intact
		
		//Breed to create new network with new neuron weights
		int layerIndex = 0;
		for (Layer layer : child.getLayers()) {
			int neuronIndex = 0;
			for(Neuron neuron : layer.getNeurons()) {
				int weightIndex = 0;
				for(Double weight : neuron.getWeights()) {
					if (Math.random() > 1 - Constants.elitism) {  // Genetic crossover
						neuron.setWeight(weightIndex, best.getLayers().get(layerIndex).getNeurons().get(neuronIndex).getWeights().get(weightIndex));
					}
					
					//Randomly mutate child slightly
					if(Math.random() <= Constants.mutationRate) {
						neuron.setWeight(weightIndex, weight + mutate());
					}
					weightIndex++;
				}
				neuronIndex++;
			}
			layerIndex++;
		}
		
		return child;
	}
	
	/**
	 * This adds randomness.
	 * 
	 * This is critical or the network will never try new things.
	 */
	private static double mutate() {
		return Math.random() * Constants.mutationRange * 2 - Constants.mutationRange;
	}
}
