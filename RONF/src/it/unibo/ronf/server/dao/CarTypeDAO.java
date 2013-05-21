package it.unibo.ronf.server.dao;
import it.unibo.ronf.shared.entities.CarType;

import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository("cartTypeDAO")
public class CarTypeDAO extends JpaDAO<CarType> {
	
	public CarType findByType(String type) {
		
		TypedQuery<CarType> query = em
				.createQuery(
						"SELECT ct FROM CarType ct WHERE ct.type = :type",
						entityClass);
		
		query.setParameter("type", type);
		
		List<CarType> typeList = query.getResultList();
		
		return typeList.get(0);
		
		
	}

}
