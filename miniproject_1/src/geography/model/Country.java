package geography.model;

import java.util.ArrayList;

public class Country extends GovernedRegion {

	// Variablen für Konstruktor
	private ArrayList<Region> regionCollection = new ArrayList<Region>();
	private String government;

	// Konstruktoren
	public Country(String name, int population, int area, String government) {
		super(name, population, area);
		this.government = government;
	}

	public Country(String name, int population, int area, String government, ArrayList<Region> regionCollection) {
		super(name, population, area);
		this.government = government;
		this.regionCollection = regionCollection;
	}

	// Methoden zum einfügen
	public void addRegion(Region region) {
		this.regionCollection.add(region);
	}

	// getter und setter
	public String getGovernment() {
		return government;
	}

	public void setGovernment(String government) {
		this.government = government;
	}

	public ArrayList<Region> getRegionCollection() {
		return regionCollection;
	}

	public void setRegionCollection(ArrayList<Region> regionCollection) {
		this.regionCollection = regionCollection;
	}
}
