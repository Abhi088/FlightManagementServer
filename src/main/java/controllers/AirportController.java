package controllers;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.AirportDao;
import etc.Utilities;
import filters.IsAdminFilter;
import filters.LoggedInFilter;
import models.Airport;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;

@Singleton
public class AirportController {
	@Inject
	AirportDao airportDao;
	
	Utilities utility = new Utilities();
	
	@FilterWith({LoggedInFilter.class, IsAdminFilter.class})
	public Result saveAirport(Airport airport, Context context) {
		try {
			Airport savedAirport = airportDao.saveAirport(airport);
			return Results.status(201).json().render(savedAirport);
		} catch (Exception e) {
			return utility.createMessageResponse(422, e.getMessage());
		}
	}
	
	@FilterWith({LoggedInFilter.class, IsAdminFilter.class})
	public Result addTerminal(@Param("id") Long id, @Param("terminal") Integer terminal) {
		try {
			Airport newAirport = airportDao.addTerminal(id, terminal);
			return Results.status(201).json().render(newAirport);
		} catch(Exception e) {
			return utility.createMessageResponse(422, e.getMessage());
		}
	}
	
//	public Result deleteTerminal(@Param("id") Long id, @Param("terminal") Integer terminal) {
//		try {
//			airportDao.deleteTerminal(id, terminal);
//			return utility.createMessageResponse(409, "Terminal deleted successfully");
//		} catch(Exception e) {
//			return utility.createMessageResponse(409, e.getMessage());
//		}
//	}
}
