package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.FlightDao;
import filters.Authorized;
import models.Flight;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.params.Param;
import ninja.params.PathParam;

@Singleton
public class FlightController {
	
	@Inject
	FlightDao flightDao;
	
	public Result getFlight(@PathParam("id") Long id,
							Context context) {
		Flight flight = flightDao.getFlightById(id);
		return Results.json().render(flight);
	}

	public Result getAllFlights(@Param("source") String source,
								@Param("destination") String destination,
								Context context) {
		List<Flight> flightList = flightDao.getAllFlights(source, destination);
		return Results.json().render(flightList);
	}
	
	@FilterWith({SecureFilter.class})
	public Result saveFlight(Flight flight,
							 Context context) {
		try {
			Flight savedFlight = flightDao.saveFlight(flight);
			return Results.status(201).json().render(savedFlight);
		} catch(RollbackException e) {
			Map<String, String> res = new HashMap();
			res.put("message", e.getMessage());
			return Results.status(422).json().render(e.getMessage());
		}
	}
	
	@FilterWith(SecureFilter.class)
	public Result updateFlight(Flight flight,
							   Context context) {
		try {
			flightDao.updateFlight(flight);
			return Results.json().status(200).render(flight);
		} catch (Exception e) {
			Map<String, String> res = new HashMap();
			res.put("message", e.getMessage());
			return Results.status(409).json().render(res);
		}
	}
	
	@FilterWith(SecureFilter.class)
	public Result deleteFlight(@Param("id") Long id,
								Context context) {
		Flight deletedFlight = flightDao.deleteFlight(id);
		return Results.json().render(deletedFlight);
	}
	
}