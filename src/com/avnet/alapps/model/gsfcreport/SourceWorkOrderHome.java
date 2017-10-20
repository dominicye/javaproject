package com.avnet.alapps.model.gsfcreport;

// Generated Aug 31, 2015 4:15:34 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class SourceWorkOrder.
 * @see com.avnet.alapps.model.gsfcreport.SourceWorkOrder
 * @author Hibernate Tools
 */
public class SourceWorkOrderHome {

	private static final Log log = LogFactory.getLog(SourceWorkOrderHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_GSFCREPORT);
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(SourceWorkOrder transientInstance) {
		log.debug("persisting SourceWorkOrder instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SourceWorkOrder instance) {
		log.debug("attaching dirty SourceWorkOrder instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SourceWorkOrder instance) {
		log.debug("attaching clean SourceWorkOrder instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SourceWorkOrder persistentInstance) {
		log.debug("deleting SourceWorkOrder instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SourceWorkOrder merge(SourceWorkOrder detachedInstance) {
		log.debug("merging SourceWorkOrder instance");
		try {
			SourceWorkOrder result = (SourceWorkOrder) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SourceWorkOrder findById(java.math.BigDecimal id) {
		log.debug("getting SourceWorkOrder instance with id: " + id);
		try {
			SourceWorkOrder instance = (SourceWorkOrder) sessionFactory
					.getCurrentSession()
					.get("com.avnet.alapps.model.gsfcreport.SourceWorkOrder",
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

	public List findByExample(SourceWorkOrder instance) {
		log.debug("finding SourceWorkOrder instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.gsfcreport.SourceWorkOrder")
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
