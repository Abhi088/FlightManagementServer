package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Airport;
import ninja.jpa.UnitOfWork;

public class AirportDao {
	@Inject
	Provider<EntityManager> entityManagerProvider;
	
	@Transactional
	public Airport saveAirport(Airport airport) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(airport);
		return airport;
	}
	
	@UnitOfWork
	public List<Airport> getAirports(String city) {
		EntityManager entityManager = entityManagerProvider.get();
		city = city + "%";
		TypedQuery<Airport> q = entityManager.createQuery("SELECT x from airport x WHERE city LIKE :cityParam", Airport.class).setParameter("cityParam", city);
		List<Airport> airportList = q.getResultList();
		return airportList;
	}
	
	@Transactional
	public Airport addTerminal(Long id, Integer terminal) throws Exception {
		if(terminal == null) throw new Exception("Integer value expected, got null");
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<Airport> q = entityManager.createQuery("SELECT x from airport x WHERE id = :idParam", Airport.class).setParameter("idParam", id);
		Airport airport = q.getSingleResult();
		airport.addTerminal(terminal);
		return airport;
	}
	
//	@Transactional
//	public void deleteTerminal(Long id, Integer terminal) {
//		EntityManager entityManager = entityManagerProvider.get();
//		TypedQuery<Airport> q = entityManager.createQuery("SELECT x from airport x WHERE id = :idParam", Airport.class).setParameter("idParam", id);
//		Airport airport = q.getSingleResult();
//		airport.deleteTerminal(terminal);
//	}
}
