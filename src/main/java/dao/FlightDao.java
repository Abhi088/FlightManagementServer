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
	public Flight saveFlight(String name, String source, String destination) {
		Flight flight = new Flight(name, source, destination);
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(flight);
		return flight;
	}
	
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
	public Flight deleteFlight(Long id) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Flight> q = entityManager.createQuery("SELECT x FROM flight x WHERE id = :idParam ", Flight.class).setParameter("idParam", id);
		List<Flight> flightList = q.getResultList();
		Query q1 = entityManager.createQuery("DELETE FROM flight x WHERE id = :idParam").setParameter("idParam", id);
		q1.executeUpdate();
		return flightList.get(0);
	}
	
	
	
	@UnitOfWork
	public List<Flight> getAllFlights() {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Flight> q = entityManager.createQuery("SELECT x FROM flight x", Flight.class);
		List<Flight> flightList = q.getResultList();
		return flightList;
	}
}