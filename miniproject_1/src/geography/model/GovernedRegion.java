package geography.model;

public abstract class GovernedRegion {
	
	// Basis für Object
	private String name;
	private int area;
	private int population;

	// super Konstruktor
	public GovernedRegion(String name, int population, int area) {
		this.name = name;
		this.area = area;
		this.population = population;
	}

	// getter und setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

}
