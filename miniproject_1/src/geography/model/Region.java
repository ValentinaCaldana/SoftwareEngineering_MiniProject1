package geography.model;

import java.util.ArrayList;

public class Region extends GovernedRegion {

	// Variablen für Konstruktoren
	private ArrayList<City> cityCollection = new ArrayList<City>();
	private Country country;

	// Konstruktoren
	public Region(String name, int population, int area, Country country) {
		super(name, population, area);
		this.country = country;
	}

	public Region(String name, int population, int area, Country country, ArrayList<City> cityCollection) {
		super(name, population, area);
		this.country = country;
		this.cityCollection = cityCollection;
	}

	// Methoden für addieren von City Objekten
	public void addCity(City city) {
		this.cityCollection.add(city);
	}

	// getter und setter
	public ArrayList<City> getCityCollection() {
		return cityCollection;
	}

	public void setCityCollection(ArrayList<City> cityCollection) {
		this.cityCollection = cityCollection;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
