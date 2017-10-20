package com.avnet.alapps.model.dbconnect;

// Generated Feb 9, 2016 3:01:05 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class Orderheader.
 * @see com.avnet.alapps.model.dbconnect.Orderheader
 * @author Hibernate Tools
 */
public class OrderheaderHome {

	private static final Log log = LogFactory.getLog(OrderheaderHome.class);

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

	public void persist(Orderheader transientInstance) {
		log.debug("persisting Orderheader instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Orderheader instance) {
		log.debug("attaching dirty Orderheader instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orderheader instance) {
		log.debug("attaching clean Orderheader instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Orderheader persistentInstance) {
		log.debug("deleting Orderheader instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orderheader merge(Orderheader detachedInstance) {
		log.debug("merging Orderheader instance");
		try {
			Orderheader result = (Orderheader) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Orderheader findById(java.lang.String id) {
		log.debug("getting Orderheader instance with id: " + id);
		try {
			Orderheader instance = (Orderheader) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.dbconnect.Orderheader", id);
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

	public List findByExample(Orderheader instance) {
		log.debug("finding Orderheader instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.dbconnect.Orderheader")
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
