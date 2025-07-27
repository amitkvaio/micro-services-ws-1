package com.cloud.configclient.bean;

public class Limits {
	private int minimum;
	private int maximum;
	private String name;

	public Limits() {
	}
	
	public Limits(int minimum, int maximum, String name) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
		this.name = name;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Limits [minimum=" + minimum + ", maximum=" + maximum + ", name=" + name + "]";
	}
}
