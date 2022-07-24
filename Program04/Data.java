import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class Data {

	private ArrayList<Event> list;

	public static void main(String[] args) throws IOException {

		System.out.println("STARTING");
		// This puts all the data from the file into a list
		Data data = new Data("covid-data.csv");
		PrintWriter output = new PrintWriter("output.txt");

		// You can use the display method to write your results
		// to a file. This makes it easier to view larger results.
//		Set<String> locations = data.allLocations();
//		display(output, locations);
//		display(output, data.allLocations());
//		display(output, data.allContinents());
//		display(output, data.getLocationsInThisContinent("North America"));
//		display(output, data.deathsByContinent());
//		display(output, data.casesByLocation());
//		display(output, data.numCasesByCountry());  //private method
//		display(output, data.casesByLocation(0));
//		display(output, data.codeCounts());
//		display(output, data.casesByDate());
//		display(output, data.mostCasesByDate());

//		System.out.println(data.casesByMonth());
//		display(output, data.locationsByContinent());

//		
//		Set<String> locations = data.allLocations();
//		System.out.println(locations);
//		System.out.println(locations.size());

		output.close();
		System.out.println("DONE");
	}

	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allLocations() {
		Set<String> result = new TreeSet<String>();

		for (Event event : list) {
			result.add(event.location);
		}
		return result;
	}

	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allContinents() {
		Set<String> result = new TreeSet<String>();

		for (Event event : list) {
			result.add(event.continent);
		}
		return result;
	}

	/*
	 * Returns all the locations in a particular continent. The empty set is
	 * returned if the continent does not have any locations.
	 */
	public Set<String> getLocationsInThisContinent(String continent) {
		Set<String> result = new TreeSet<String>();

		for (Event event : list) {
			if (event.continent.equals(continent)) {
				result.add(event.location);
			}
		}
		return result;
	}

	/*
	 * Returns a map keyed to a continent, where the value is the total number of
	 * deaths in that continent.
	 */
	public Map<String, Integer> deathsByContinent() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event event : list) {
			if (!result.containsKey(event.continent))
				result.put(event.continent, event.deaths);
			else {
				int deaths = result.get(event.continent) + event.deaths;
				result.put(event.continent, deaths);
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to a location, where the value is the total number of
	 * cases for that location. The map should only contain locations that had at
	 * least 1 case.
	 */
	public Map<String, Integer> casesByLocation() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event event : list) {
			if (!result.containsKey(event.location))
				result.put(event.location, event.cases);
			else {
				int cases = result.get(event.location) + event.cases;
				result.put(event.location, cases);
			}
		}

		return result;
	}

	/*
	 * For a specified month (0 is January, 1 is February, and so on), returns a map
	 * keyed to a location, where the value is the total number of cases for that
	 * location. The map should only contain locations that had at least one case.
	 * If there are no cases for a given month, returns an empty map. NOTE: Date has
	 * a getMonth() method you can use. You may get a warning that getMonth() is a
	 * deprecated method, but that's ok.
	 */
	public Map<String, Integer> casesByLocation(int month) {
		Map<String, Integer> result = new TreeMap<>();

		for (Event event : list) {
			if (!(result.containsKey(event.location) && event.date.getMonth() == month && event.cases > 0))
				result.put(event.location, event.cases);
			else if (event.date.getMonth() == month) {
				int cases = result.get(event.location) + event.cases;
				result.put(event.location, cases);
			}
		}

		return result;
	}

	// solved in video
	/*
	 * Returns a map keyed to the continent, where the value for each continent is a
	 * set of all locations in that continent.
	 */
	public Map<String, Set<String>> locationsByContinent() {
		Map<String, Set<String>> result = new TreeMap<>();
		Set<String> locationSet;
		for (Event event : list) {
			if (!result.containsKey(event.continent))
				locationSet = new TreeSet<String>();
			else
				locationSet = result.get(event.continent);
			locationSet.add(event.location);
			result.put(event.continent, locationSet);

		}
		return result;
	}

	/*
	 * Returns a map keyed to a location's 3-letter code, where the value is the
	 * total number of times that code appears in the data.
	 */
	public Map<String, Integer> codeCounts() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event event : list) {
			if (!result.containsKey(event.abbreviation))
				result.put(event.abbreviation, 1);
			else {
				int numOfTimesShownSoFar = result.get(event.abbreviation) + 1;
				result.put(event.abbreviation, numOfTimesShownSoFar);
			}
		}
		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value is the total number of cases
	 * for that date.
	 */
	public Map<Date, Integer> casesByDate() {
		Map<Date, Integer> result = new TreeMap<>();

		for (Event event : list) {
			if (!result.containsKey(event.date))
				result.put(event.date, event.cases);
			else {
				int numCasesForThatDate = result.get(event.date) + event.cases;
				result.put(event.date, numCasesForThatDate);
			}
		}
		return result;
	}

	// ----HELPER METHOD I MADE---- //
	private Map<Integer, String> numCasesByCountry() {
		Map<Integer, String> switchedMap = new TreeMap<>();
		Map<String, Integer> normalMap = casesByLocation();

		for (Map.Entry<String, Integer> entry : normalMap.entrySet()) {
			switchedMap.put(entry.getValue(), entry.getKey());
		}

		return switchedMap;
	}

	/*
	 * Returns a list of the locations in the database, sorted by the total number
	 * of cases reported for that location. The location with the fewest cases
	 * should be first, and the location with the most cases should be last.
	 */
	public List<String> locationsSortedByCaseCount() {
		Map<Integer, String> sortedMapByCase = numCasesByCountry();
		List<String> result = new ArrayList<String>();

		for (Map.Entry<Integer, String> entry : sortedMapByCase.entrySet()) {
			result.add(entry.getValue());
		}

		return result;
	}

	// ----HELPER METHOD I MADE---- //
	private Set<String> mostCasesOfLocationByDate(Date date) {
		Set<String> output = new HashSet<String>();
		int mostCases = -1;

		for (Event event : list) {
			if (event.date.equals(date) && event.cases > mostCases) {
				output.clear();
				output.add(event.location);
				mostCases = event.cases;
			} else if (event.date.equals(date) && event.cases == mostCases) {
				output.add(event.location);
			}
		}
		return output;
	}

	/* 
	 * Returns a map keyed to the date, where the value for that date is the
	 * location (or locations) that had the highest number of cases on that date.
	 * It's possible that the set will contain only one location. But if there are
	 * ties, the set will contain more than one location.
	 */
	public Map<Date, Set<String>> mostCasesByDate() {
		Map<Date, Set<String>> result = new TreeMap<>();
		for (Event event : list) {
			if (!result.containsKey(event.date))
				result.put(event.date, mostCasesOfLocationByDate(event.date));
		}
		return result;
	}

	/*
	 * Returns a map keyed to a month (0=January, 1=February, and so on), where the
	 * value is the number of reported cases for that month. Only include months
	 * that are listed in the data. NOTE: Date has a getMonth() method you can use.
	 * You may get a warning that getMonth() is a deprecated method, but that's ok.
	 */
	public Map<Integer, Integer> casesByMonth() {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		for (Event event : list) {
			if (!result.containsKey(event.date.getMonth()))
				result.put(event.date.getMonth(), event.cases);
			else {
				int c = result.get(event.date.getMonth());
				c += event.cases;
				result.put(event.date.getMonth(), c);
			}
		}

		return result;
	}

	/// \/\/\/\/\/\/\/\/\/\/\/\/\/\/********** DON'T MODIFY ANY OF THE CODE BELOW
	/// ************\/\/\/\/\/\/\/\/\/\/\/\/\/\/\

	// Reads file data into an ArrayList of Event objects.
	// Don't modify this code
	public Data(String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		list = new ArrayList<Event>();
		in.nextLine();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		while (in.hasNextLine()) {
			String input = in.nextLine();
			String[] line = input.split(",");
			String abbreviation = line[0];
			String continent = line[1];
			String location = line[2];
			Date date = simpleDateFormat.parse(line[3], new ParsePosition(0));
			int cases = Integer.parseInt(line[4]);
			int deaths = Integer.parseInt(line[5]);
			Event d = new Event(abbreviation, continent, location, date, cases, deaths);
			list.add(d);
		}

		in.close();
	}

	/*
	 * Writes a collection (list, set) to a specified file. DON'T CHANGE THIS.
	 */
	public static <T> void display(PrintWriter output, Collection<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		output.println(line + "]");
	}

	/*
	 * Writes a map to a specified file DON'T CHANGE THIS.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void display(PrintWriter output, Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}

		for (K key : items.keySet()) {
			output.print(key + "---------->");
			Object o = items.get(key);
			if (o instanceof Collection) {
				output.println();
				display(output, (Collection<Object>) items.get(key));
			} else {
				output.println(items.get(key));
			}
		}
	}

	/*
	 * Inner class for organizing event information. DON'T CHANGE THIS.
	 */
	private class Event {
		private String abbreviation;
		private String continent;
		private String location;
		private Date date;
		private int cases;
		private int deaths;

		private Event(String abbreviation, String continent, String location, Date date, int cases, int deaths) {
			this.abbreviation = abbreviation;
			this.continent = continent;
			this.location = location;
			this.date = date;
			this.cases = cases;
			this.deaths = deaths;
		}

		@Override
		public String toString() {
			return "[" + abbreviation + "," + continent + "," + location + "," + date + "," + cases + "," + deaths
					+ "]";
		}

	}

}
