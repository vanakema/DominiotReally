package main;

public abstract class Card {
	String name;
	String description;
	int cost;
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract int getCost();
	
}
