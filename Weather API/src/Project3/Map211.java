package Project3;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Map211 {

	// Data Structures (minimum requirement)

	static String html;
	static String weather;
	static String mapFileName = "myMap.html";
	static ArrayList<String> weatherInfo = new ArrayList<>();

	Map211(HashMap<Integer, String> weatherInfo2, String mapType, int zoom) throws IOException {
		String city = weatherInfo2.get(0);
	

		weather = " " + city.toUpperCase() + " | " + weatherInfo2.get(1) + " | " + weatherInfo2.get(2) + ", "
				+ weatherInfo2.get(3) + " | " + weatherInfo2.get(4) + " | " + weatherInfo2.get(5) + " | "
				+ weatherInfo2.get(6) + " and " + weatherInfo2.get(7);

		// write a HTML file
		writeHTML(weather, city, mapType, zoom);

		// run html file from java code
		String url = mapFileName; // Found in project folder
		File htmlFile = new File(url);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	public static void writeHTML(String weatherNow, String city, String mapType, int zoom) {
		html = "<!DOCTYPE html>" + "<html>" + "<body>" + "<h2>" + weatherNow + "</h2>" + "<iframe" + "  width=1200"
				+ "  height=900" + "  style=border:0" + "  loading=lazy" + "  allowfullscreen"
				+ "  referrerpolicy=\"no-referrer-when-downgrade\""
				+ "src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyBU9803NZQ1g6Pq7a5DDGGPBoH87XFX8NU&q=" + city + "&zoom=" + zoom
				+ "&maptype=" + mapType + "\"" + "</iframe>" + "</body>" + "</html>";

		File f = new File(mapFileName);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
