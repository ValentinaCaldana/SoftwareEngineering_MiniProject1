package geography.model;

public class City extends GovernedRegion {

	// Variablen für Region
	private Region region;

	// Konstruktoren
	public City(String name, int population, int area) {
		super(name, population, area);
	}

	public City(String name, int population, int area, Region region) {
		super(name, population, area);
		this.region = region;
	}

	// getter und setter
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}
