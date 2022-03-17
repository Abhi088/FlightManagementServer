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
	
	public Result getFlight(@Param("id") Long id,
							Context context) {
		Flight flight = flightDao.getFlightById(id);
		return Results.json().render(flight);
	}
	
	public Result getAllFlights(Context context) {
		List<Flight> flightList = flightDao.getAllFlights();
		return Results.json().render(flightList);
	}
	
	public Result getFlightFromSourceToDestination(@Param("source") String source,
												   @Param("destination") String destination) {
		List<Flight> flights = flightDao.getFlightBySourceAndDestination(source, destination);
		return Results.json().render(flights);
	}
	
	public Result saveFlight(Flight flight,
							 Context context) {
		Flight savedFlight = flightDao.saveFlight(flight);
		return Results.json().render(savedFlight);
	}
	
	public Result updateFlight(Flight flight,
							   Context context) {
		flightDao.updateFlight(flight);
		return Results.json().render(flight);
	}
	
	public Result deleteFlight(@Param("id") Long id,
								Context context) {
		Flight deletedFlight = flightDao.deleteFlight(id);
		return Results.json().render(deletedFlight);
	}
	
}