package main;

public abstract class Card {
	String name;
	String description;
	int cost;
	int value;
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract int getCost();
	
}
