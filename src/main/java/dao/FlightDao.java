package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Flight;
import models.FlightResponse;
import ninja.jpa.UnitOfWork;

public class FlightDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;

	@Transactional
	public Flight saveFlight(Flight flight) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(flight);
		return flight;
	}

	@Transactional
	public Flight updateFlight(Flight flight) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.merge(flight);
		return flight;
	}

	@Transactional
	public boolean deleteFlight(Long id) {
		EntityManager entityManager = entityManagerProvider.get();
		Query q1 = entityManager.createQuery("DELETE FROM flight x WHERE id = :idParam").setParameter("idParam", id);
		q1.executeUpdate();
		return true;
	}

	@UnitOfWork
	public Flight getFlightById(Long id) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Flight> q = entityManager.createQuery("SELECT x from flight x WHERE id = :idParam", Flight.class)
				.setParameter("idParam", id);
		List<Flight> flights = q.getResultList();
		if (flights.size() == 0)
			return null;
		return flights.get(0);
	}

	@UnitOfWork
	public List<FlightResponse> getAllFlights(String source, String destination, int limit, int offset) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<FlightResponse> q = entityManager.createQuery(
				"SELECT NEW models.FlightResponse(fl.id, fl.flight_id, fl.type, fl.airline, fl.source_terminal, fl.destination_terminal, fl.departure as flight_departure, fl.duration as flight_duration, fl.start_days, fl.total_seats, fl.adult_price, fl.infant_price, fl.check_in_baggage, fl.cabin_baggage, a1.code AS source_code, a1.name AS source_airport, a1.city AS source_city, a2.code AS destination_code, a2.name AS destination_airport, a2.city AS destination_city) from flight fl join airport a1 on fl.source=a1.code join airport a2 on fl.destination = a2.code where a1.city=:sourceParam and a2.city=:destinationParam",
				FlightResponse.class);
		q.setParameter("sourceParam", source);
		q.setParameter("destinationParam", destination);
		q.setFirstResult(offset);
		q.setMaxResults(limit);
		List<FlightResponse> flightList = q.getResultList();
		return flightList;
	}

	@UnitOfWork
	public Integer getCountOfFlights(String source, String destination) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Flight> q = entityManager.createQuery(
				"SELECT x FROM flight x WHERE source = :sourceParam AND destination = :destinationParam", Flight.class);
		q.setParameter("sourceParam", source);
		q.setParameter("destinationParam", destination);
		Integer count = q.getResultList().size();
		return count;
	}
}