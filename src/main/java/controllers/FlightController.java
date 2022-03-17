package controllers;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.FlightDao;
import models.Flight;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;

@Singleton
public class FlightController {
	
	@Inject
	FlightDao flightDao;
	
	public Result getAllFlights(Context context) {
		List<Flight> flightList = flightDao.getAllFlights();
		return Results.json().render(flightList);
	}
	
	public Result saveFlight(@Param("name") String name,
							 @Param("source") String source,
							 @Param("destination") String destination,
							 Context context) {
		Flight savedFlight = flightDao.saveFlight(name, source, destination);
		return Results.json().render(savedFlight);
	}
	
	public Result deleteFlights(@Param("name") String name,
								Context context) {
		List<Flight> deletedFlights = flightDao.deleteFlight(name);
		return Results.json().render(deletedFlights);
	}
	
}