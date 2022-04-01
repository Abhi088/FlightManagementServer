package controllers;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.AirportDao;
import models.Airport;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;

@Singleton
public class AirportController {
	@Inject
	AirportDao airportDao;
	
	public Result saveAirport(Airport airport, Context context) {
		try {
			Airport savedAirport = airportDao.saveAirport(airport);
			return Results.status(201).json().render(savedAirport);
		} catch (Exception e) {
			Map<String, String> res = new HashMap<>();
			res.put("message", e.getMessage());
			return Results.status(422).json().render(res);
		}
	}
	
//	public Result addTerminal(@Param("id") Long id, @Param("terminal") Integer terminal) {
//		try {
//			Airport newAirport = airportDao.addTerminal(id, terminal);
//			return Results.status(201).json().render(newAirport);
//		} catch(Exception e) {
//			Map<String, String> res = new HashMap<>();
//			res.put("message", e.getMessage());
//			return Results.status(422).json().render(res);
//		}
//	}
}
