package com.avnet.alapps.model.dbconnect;

// Generated Aug 22, 2016 12:57:06 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class ProductionOrderHeader.
 * @see com.avnet.alapps.model.dbconnect.ProductionOrderHeader
 * @author Hibernate Tools
 */
public class ProductionOrderHeaderHome {

	private static final Log log = LogFactory
			.getLog(ProductionOrderHeaderHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_DBCONNECT);
		} catch (Exception e) {
			log.error("Could not locate DBCONNECTSessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate DBCONNECTSessionFactory in JNDI");
		}
	}

	public void persist(ProductionOrderHeader transientInstance) {
		log.debug("persisting ProductionOrderHeader instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductionOrderHeader instance) {
		log.debug("attaching dirty ProductionOrderHeader instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProductionOrderHeader instance) {
		log.debug("attaching clean ProductionOrderHeader instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProductionOrderHeader persistentInstance) {
		log.debug("deleting ProductionOrderHeader instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProductionOrderHeader merge(ProductionOrderHeader detachedInstance) {
		log.debug("merging ProductionOrderHeader instance");
		try {
			ProductionOrderHeader result = (ProductionOrderHeader) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductionOrderHeader findById(
			com.avnet.alapps.model.dbconnect.ProductionOrderHeaderId id) {
		log.debug("getting ProductionOrderHeader instance with id: " + id);
		try {
			ProductionOrderHeader instance = (ProductionOrderHeader) sessionFactory
					.getCurrentSession()
					.get("com.avnet.alapps.model.dbconnect.ProductionOrderHeader",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProductionOrderHeader instance) {
		log.debug("finding ProductionOrderHeader instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.dbconnect.ProductionOrderHeader")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
