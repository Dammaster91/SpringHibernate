package com.emp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emp.dao.EmployeeDao;
import com.emp.entity.EmployeeEntity;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int save(EmployeeEntity p) {

		Session session = null;
		Integer id = 0;
		try {

			session = sessionFactory.openSession();
			id = (Integer) session.save(p);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (id != 0) {
			return id;
		} else {
			return id;

		}

		}

	@Override
	public int update(EmployeeEntity p) {
		Session session = null;
		try {

			session = sessionFactory.openSession();
			Object o = session.load(EmployeeEntity.class, new Integer(p.getId()));
			EmployeeEntity cl = (EmployeeEntity) o;

			Transaction tx = session.beginTransaction();
			session.update(cl);

			cl.setName(p.getName());
			cl.setSalary(p.getSalary());
			cl.setDesignation(p.getDesignation());
			tx.commit();
			System.out.println("Object Updated successfully.....!!");

			return 1;
			/*
			 * session = sessionFactory.openSession(); session.update(cartLine);
			 * return true;
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public int delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		EmployeeEntity employee = (EmployeeEntity) session.get(EmployeeEntity.class, new Integer(id));
        if(null != employee){
            session.delete(employee);
            tx.commit();
            
        }
		return id;
		
	}

	@Override
	public EmployeeEntity getEmpById(int id) {

		Session session = sessionFactory.openSession();
		System.out.println(session);

		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("id"), "id");
		pList.add(Projections.property("name"), "name");
		pList.add(Projections.property("salary"), "salary");
		pList.add(Projections.property("designation"), "designation");

		Criteria criteria = session.createCriteria(EmployeeEntity.class).setProjection(pList);

		EmployeeEntity empList = (EmployeeEntity) criteria
				.setResultTransformer(new AliasToBeanResultTransformer(EmployeeEntity.class))
				.add(Restrictions.eq("id", id)).uniqueResult();

		return empList;
	}

	@Override
	public List<EmployeeEntity> getEmployees() {

		Session session = sessionFactory.openSession();
		System.out.println(session);

		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("id"), "id");
		pList.add(Projections.property("name"), "name");
		pList.add(Projections.property("salary"), "salary");
		pList.add(Projections.property("designation"), "designation");

		Criteria criteria = session.createCriteria(EmployeeEntity.class).setProjection(pList);

		@SuppressWarnings("unchecked")
		List<EmployeeEntity> empList = criteria
				.setResultTransformer(new AliasToBeanResultTransformer(EmployeeEntity.class))
				// .add(Restrictions.eq("id", id))
				.list();

		return empList;
	}

}