package codingcompetition2019;

import org.w3c.dom.xpath.XPathResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * User written submissions for the coding competition.
 */
public class CodingCompCSVUtil {

	/**
	 * @param fileName the name of the fiel in the directory
	 * @param countryName name of country
	 * @return gives bakc 2d array of data in csv file
	 * @throws IOException when there is no file !!!
	 */
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {

		Scanner scanner = new Scanner(new File(fileName));

		List<List<String>> bigList = new ArrayList<>();

		while (scanner.hasNext()) {

			List<String> littleList;
			String whatever = scanner.nextLine();
			littleList = Arrays.asList(whatever.split(","));

			if (littleList.get(0).equals(countryName)) {
				bigList.add(littleList);
			}

		}

		return bigList;

	}

	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {

		Scanner scanner = new Scanner(new File(fileName));

		List<List<String>> bigList = new ArrayList<>();

		while (scanner.hasNext()) {

			List<String> littleList;
			String whatever = scanner.nextLine();
			littleList = Arrays.asList(whatever.split(","));
			bigList.add(littleList);

		}

		return bigList;
	}

	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {

		Scanner scanner = new Scanner(new File(fileName));

		List<List<String>> bigList = new ArrayList<>();

		scanner.nextLine();

		while (scanner.hasNext()) {

			List<String> littleList;
			String whatever = scanner.nextLine();
			littleList = Arrays.asList(whatever.split(","));
			bigList.add(littleList);

		}

		return bigList;
	}

	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {

		List<String> mostImpactful = records.get(0);

		for (List l : records) {
			if ( Integer.parseInt((String)(l.get(3))) > Integer.parseInt(mostImpactful.get(3)) ) {
				mostImpactful = l;
			}
		}

		return new DisasterDescription(mostImpactful.get(2), "Earthquake", Integer.parseInt(mostImpactful.get(3)));

	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {

		List mostImpactful = records.get(0);

		boolean initElementFound = false;

		int i = 0;
		while (!initElementFound) {
			if ( ((String)(records.get(i).get(0))).equals(category) ) {
				mostImpactful = records.get(i);
				initElementFound = true;
			}
			i++;
		}

		for (List l : records) {
			if ( ((String)(l.get(0))).equals(category) ) {
				if ( Integer.parseInt((String)(l.get(3))) > Integer.parseInt((String)(mostImpactful.get(3))) ) {
					mostImpactful = l;
				}
			}
		}

		return new DisasterDescription((String)(mostImpactful.get(2)), category, Integer.parseInt((String)(mostImpactful.get(3))));
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {

		List mostImpactful = records.get(0);

		boolean initElementFound = false;

		int i = 0;
		while (!initElementFound) {
			if ( ((String)(records.get(i).get(2))).equals(year) ) {
				if ( !((String)(records.get(i).get(0))).equals("All natural disasters")) {
					mostImpactful = records.get(i);
					initElementFound = true;
				}
			}
			i++;
		}

		for (List l : records) {
			if ( ((String)(l.get(2))).equals(year) ) {
				if ( !((String)(l.get(0))).equals("All natural disasters")) {
					if ( Integer.parseInt((String)(l.get(3))) > Integer.parseInt((String)(mostImpactful.get(3))) ) {
						mostImpactful = l;
					}
				}
			}
		}

		return new DisasterDescription((String)(mostImpactful.get(2)), (String)(mostImpactful.get(0)), Integer.parseInt((String)(mostImpactful.get(3))));
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {

		int total = 0;

		for (int i = 0; i < records.size(); i++) {

			if ( ((String)(records.get(i).get(0))).equals(category) ) {
				total += Integer.parseInt(((String)(records.get(i).get(3))));
			}

		}

		return new DisasterDescription("All", category, total);

	}

	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {

		int amountFound = 0;

		// No Maximum
		if (max == -1) {

			for (int i = 0; i < records.size(); i++) {
				if (Integer.parseInt((String)(records.get(i).get(3))) >= min) {
					amountFound++;
				}
			}

		// Max is given
		} else {
			for (int i = 0; i < records.size(); i++) {
				if (Integer.parseInt((String)(records.get(i).get(3))) >= min
						&& Integer.parseInt((String)(records.get(i).get(3))) <= max) {
					amountFound++;
				}
			}
		}

		return amountFound;

	}

	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {

		int total1 = 0;

		for (int i = 0; i < records1.size(); i++) {
			total1 += Integer.parseInt(((String)(records1.get(i).get(3))));
		}

		int total2 = 0;

		for (int i = 0; i < records2.size(); i++) {
			total2 += Integer.parseInt(((String)(records2.get(i).get(3))));
		}

		return (total1 > total2);

	}

	// GUI HANDLING
	public static ArrayList<String> getCountryList(String filename) {

		try {
			Scanner scanner = new Scanner(new File(filename));

			ArrayList<String> l = new ArrayList<>();

			l.add("World");

			scanner.nextLine();

			int entries = 0;
			while (scanner.hasNext()) {

				String countryName = scanner.nextLine().split(",")[0];

				if (!countryName.equals(l.get(entries)) && !countryName.equals("World")) {
					l.add(countryName);
					entries++;
				}

			}

			return l;

		} catch (FileNotFoundException e) {
			System.out.println(e);
			return null;

		}

	}

	public static ArrayList<String> getDisasterList(String filename) {

	try {
		Scanner scanner = new Scanner(new File(filename));

		ArrayList<String> l = new ArrayList<>();

		scanner.nextLine();

		int entries = 0;
		while (scanner.hasNext()) {

			String countryName = scanner.nextLine().split(",")[0];

			if (entries == 0) {
				l.add(countryName);
				entries++;
			}

			if (!countryName.equals(l.get(entries-1))) {
				l.add(countryName);
				entries++;
			}

		}

		return l;

	} catch (FileNotFoundException e) {
		System.out.println(e);
		return null;

	}

}

	public static String getReqInfo(String choice1, String choice2) {
		return "peepoo";
	}
}
