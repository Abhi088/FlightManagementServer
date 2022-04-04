package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.RollbackException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.FlightDao;
import etc.Utilities;
import filters.IsAdminFilter;
import filters.LoggedInFilter;
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
	
	Utilities utility = new Utilities();

	public Result getFlight(@PathParam("id") Long id, Context context) {
		Flight flight = flightDao.getFlightById(id);
		return Results.json().render(flight);
	}

	public Result getAllFlights(@Param("source") String source, @Param("destination") String destination,
			@Param("limit") int limit, @Param("offset") int offset, Context context) {
		Integer numberOfFlights = flightDao.getCountOfFlights(source, destination);
		List<Flight> flightList = flightDao.getAllFlights(source, destination, limit, offset);
		Map<String, Object> res = new HashMap<>();
		res.put("flights", flightList);
		res.put("count", numberOfFlights);
		return Results.json().render(res);
	}

	@FilterWith({ SecureFilter.class, IsAdminFilter.class })
	public Result saveFlight(Flight flight, Context context) {
		try {
			Flight savedFlight = flightDao.saveFlight(flight);
			return Results.status(201).json().render(savedFlight);
		} catch (RollbackException e) {
			return utility.createMessageResponse(422, e.getMessage());
		}
	}

	@FilterWith({ LoggedInFilter.class, IsAdminFilter.class })
	public Result updateFlight(Flight flight, Context context) {
		try {
			flightDao.updateFlight(flight);
			return Results.json().status(200).render(flight);
		} catch (Exception e) {
			return utility.createMessageResponse(409, e.getMessage());
		}
	}

	@FilterWith({ SecureFilter.class, IsAdminFilter.class })
	public Result deleteFlight(@Param("id") Long id, Context context) {
		flightDao.deleteFlight(id);
		return utility.createMessageResponse(200, "Flight successfully deleted");
	}

}