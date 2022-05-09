package geography.model;

import java.util.ArrayList;

public class WorldMap {

	// ArrayList für Country Objekten
	public ArrayList<Country> countryCollection = new ArrayList<Country>();

	// Methoden zum Addieren und löschen von Country Objekten
	public void addCountry(Country country) {
		this.countryCollection.add(country);
	}

}
