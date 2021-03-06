package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Flight;
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
	public List<Flight> getAllFlights(String source, String destination, int limit, int offset) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Flight> q = entityManager.createQuery(
				"SELECT x FROM flight x WHERE source = :sourceParam AND destination = :destinationParam", Flight.class);
		q.setParameter("sourceParam", source);
		q.setParameter("destinationParam", destination);
		q.setFirstResult(offset);
		q.setMaxResults(limit);
		List<Flight> flightList = q.getResultList();
		return flightList;
	}
	
	@UnitOfWork
	public Integer getCountOfFlights(String source, String destination) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Flight> q = entityManager.createQuery("SELECT x FROM flight x WHERE source = :sourceParam AND destination = :destinationParam", Flight.class);
		q.setParameter("sourceParam", source);
		q.setParameter("destinationParam", destination);
		Integer count = q.getResultList().size();
		return count;
	}
}