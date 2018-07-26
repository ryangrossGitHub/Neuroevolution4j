package com.ryan.neuroevolution4j;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    public static final int inputLayers = 3;
    public static final int hiddenLayers = 15;
  	public static final int inputLayerInputs = 0; //Input layer always has 0 inputs because it is the first layer
  	public static final int outputLayerNeurons = 1; //Output layer always has 1 neuron which is the final yes or no decision from the network
    public static final int population = 30; // Population by generation.
    public static final double elitism = 0.2; //Percent of child DNA cloned from best network
    public static final double mutationRate = 0.5; // How often random mutation is applied to the weights of synapses.
	public static final double mutationRange = 0.1; // How much random mutation is applied to the weights of synapses.
}
