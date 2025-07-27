package com.amit.microservices.limitsservice.bean;

public class LimitConfiguration {
	private int maximum;
	private int minimum;
	private String name;

//no-argument constructor
	protected LimitConfiguration() {
	}

//generating getters
	public int getMaximum() {
		return maximum;
	}

	public int getMinimum() {
		return minimum;
	}
	
	public String getName() {
		return name;
	}

//Generating constructor using fields
	public LimitConfiguration(int maximum, int minimum, String name) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
		this.name = name;
	}
}