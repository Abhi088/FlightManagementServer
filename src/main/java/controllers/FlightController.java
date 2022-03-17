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
	
	public Result saveFlight(Flight flight,
							 Context context) {
		Flight savedFlight = flightDao.saveFlight(flight);
		return Results.json().render(savedFlight);
	}
	
	public Result deleteFlight(@Param("id") Long id,
								Context context) {
		Flight deletedFlight = flightDao.deleteFlight(id);
		return Results.json().render(deletedFlight);
	}
	
}