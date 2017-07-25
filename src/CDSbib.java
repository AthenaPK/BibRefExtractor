import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;

public class CDSbib {

	private static final Logger logger = Logger
			.getLogger(CDSbib.class.getName());

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		ArrayList<String> urlArrayList = new ArrayList<>();
		String bibtex = "/export/hx";
		String userInput = sc.nextLine();
		while (!userInput.isEmpty()) {

			urlArrayList.add(userInput);
			userInput = sc.nextLine();
		} ;
		sc.close();
		for (String currentElement : urlArrayList) {
			String finalUrl = currentElement.substring(0,
					currentElement.length() - 6) + bibtex;
			String bibreftext = Jsoup.connect(finalUrl).get().select("pre")
					.text();
			writeToFile("Bibtex-database.txt", bibreftext);
		}

	}

	/**
	 * Opens and writes into a file with the given filename. The file will be
	 * created if it does not exist
	 * 
	 * @param filename
	 *            The file to write in
	 */
	public static void writeToFile(String filename, String bibref) {

		// Try with resources autocloses the writer when done, regardless of try
		// result - Java 1.7 +
		// No need to close the temporary FileWriter, java does it for us
		// http://stackoverflow.com/questions/16584777/is-it-necessary-to-close-a-filewriter-provided-it-is-written-through-a-buffered

		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(filename, true))) {

			// Write your stuff here
			writer.write(bibref);
			writer.newLine();

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}

	}
}