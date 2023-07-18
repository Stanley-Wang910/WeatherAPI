package Project3;

import java.util.HashMap;
import java.util.Scanner;

public class myWeatherApp {

	static Scanner console = new Scanner(System.in);

	private static HashMap<Integer, String> weatherInfo = new HashMap<>();
	static String mapType;
	static int zoom;

	public static void CityWeatherInfo() throws Exception {
		boolean validCityName = false;

		while (!validCityName) {
			System.out.println("Input a city name:");
			String city = console.nextLine();
			System.out.println();

			validCityName = Weather211.CityWeather(city);
			int photo = 0;
			int zoom = -1;
			String mapType = null;
			if (validCityName) {
				
				
				

				System.out.println("Current Weather [" + city + "]\n");

				boolean photoRun = true;
				while (photoRun) {
					System.out.println("Please select an image option\n1. Road Map\n2. Sattelite View");

					photo = console.nextInt();
					
					switch (photo) {

					case 1:
						mapType = "roadmap";
						System.out.println("Road Map Selected");
						photoRun = false;
						break;

					case 2:
						mapType = "satellite";
						System.out.println("Satellite View Selected");
						photoRun = false;
						break;

					default:
						System.out.println("invalid selction");
						break;
					}

				}
				boolean zoomRun = true;
				while (zoomRun) {
					System.out.println("\nPlease select a zoom level 0 (far) -21 (close)");
					zoom = console.nextInt();

					if (zoom >= 0 && zoom <= 21) {
						zoomRun = false;
					} else {
						System.out.println("Please select an available zoom level");
						zoomRun = true;
					}

				}
				
				weatherInfo.putAll(Weather211.getCityWeather());
				// print text version
				for (int i = 0; i < weatherInfo.size(); i++) {
					System.out.println(weatherInfo.get(i));
				}
				new Map211(weatherInfo, mapType, zoom);
				
			} else {
				System.out.println("Invalid city name. Type again.\n");
			}
		}
	
	
	}

	

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to Weather 211 - Spring 2022");
		System.out.println();

		CityWeatherInfo();
		
	}

}
