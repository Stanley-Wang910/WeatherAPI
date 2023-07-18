package Project3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather211 
{

	static HashMap<Integer, String> weatherSpecs = new HashMap<>();
	
	public static boolean CityWeather (String cityName) {
		
	boolean validCityName=false;
	
	try 
	{
		//Create URL instance
		String urlFirst = "https://api.openweathermap.org/data/2.5/weather?q=";
		String urlSec = "&appid=2ec4eb4d7d0fd284d3119647aa02bd81";
		//temp
		
		String theURL = urlFirst + cityName + urlSec;
		URL url = new URL(theURL);
		
		//Reads info from URL
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		//Read JSON file. All data for the city is stored in myObject"
		JSONObject myObject = (JSONObject)jsonParser.parse(br);
		
		//City
		
		
		weatherSpecs.put(0, cityName);
		
		
		
		// Weather
		
		
		JSONArray weatherArray = (JSONArray)myObject.get("weather");
		JSONObject w = (JSONObject) weatherArray.get(0);
		//get weather info from w
			String weatherNOW = (String) w.get("description") ;
		//add weather info to the data structure
			weatherSpecs.put(1,  weatherNOW +  " in " + cityName); //Weather
				
		
		// Temperature and Humidity
				
		
			JSONObject main = (JSONObject)myObject.get("main");
				
			double cityTemp = (double)main.get("temp");
			cityTemp = ((cityTemp - 273.15)*9)/5 + 32;///convert to Fahrenheit;	
			String tempNow= String.format("%.1f", cityTemp)+"\u00B0";
			
			
			double feelsLike = (double)main.get("feels_like");
			feelsLike = ((feelsLike - 273.15)*9)/5 + 32;
			String feelsLikeNow = String.format("%.1f", feelsLike)+"\u00B0";
			
			
			double maxTemp = (double)main.get("temp_max");
			maxTemp = ((maxTemp- 273.15)*9)/5 + 32;
			String tempMax = String.format("%.1f", maxTemp)+"\u00B0";
			
			double minTemp = (double)main.get("temp_min");
			minTemp = ((minTemp- 273.15)*9)/5 + 32;
			String tempMin = String.format("%.1f", minTemp)+"\u00B0";
				
			
			long cityHumidity = (long)main.get("humidity");
			
			weatherSpecs.put(2, "The current Temperature is " + tempNow); //CurrentTemp
			weatherSpecs.put(3, "However, it only feels about "+ feelsLikeNow); //FeelsLike
			weatherSpecs.put(4, "The minimum temperature is "+tempMin); //MinimumTemp
			weatherSpecs.put(5, "The maximum temperature is "+ tempMax); //MaximumTemp
			weatherSpecs.put(6, "The humidity is currently "+ Long.toString(cityHumidity)+"%"); //Humidity
			
			//Wind
			

			JSONObject wind = (JSONObject)myObject.get("wind");
			double windSpeed = (double)wind.get("speed");
			String windFeel = " ";
			if(windSpeed>=0 && windSpeed<2) {
				windFeel = "there is a Light Breeze";
			}
			else if (windSpeed>2 && windSpeed<4) {
				windFeel = "there is a Gentle to Moderate Breeze";
			}
			else if (windSpeed>4 && windSpeed<6) {
				windFeel = "there is a Strong Breeze";
			}
			else if (windSpeed>6 && windSpeed<8) {
				windFeel = "there is a Light to Medium Gale";
			}
			else if (windSpeed>8 && windSpeed<10) {
				windFeel = "there are Heavy Winds";
			}
			
			weatherSpecs.put(7, windFeel); //https://www.weather.gov/pqr/wind //WindForce

			validCityName = true;
		
	}
	catch (Exception e) {
		
	}
	return validCityName;
	}
	public static HashMap<Integer, String> getCityWeather() {
        
        return weatherSpecs;
	
}
}

