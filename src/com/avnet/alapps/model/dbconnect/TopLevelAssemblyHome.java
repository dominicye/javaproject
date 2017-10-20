package com.avnet.alapps.model.dbconnect;

// Generated Apr 11, 2016 1:59:58 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class TopLevelAssembly.
 * @see com.avnet.alapps.model.dbconnect.TopLevelAssembly
 * @author Hibernate Tools
 */
public class TopLevelAssemblyHome {

	private static final Log log = LogFactory
			.getLog(TopLevelAssemblyHome.class);

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

	public void persist(TopLevelAssembly transientInstance) {
		log.debug("persisting TopLevelAssembly instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TopLevelAssembly instance) {
		log.debug("attaching dirty TopLevelAssembly instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TopLevelAssembly instance) {
		log.debug("attaching clean TopLevelAssembly instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TopLevelAssembly persistentInstance) {
		log.debug("deleting TopLevelAssembly instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TopLevelAssembly merge(TopLevelAssembly detachedInstance) {
		log.debug("merging TopLevelAssembly instance");
		try {
			TopLevelAssembly result = (TopLevelAssembly) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TopLevelAssembly findById(java.math.BigDecimal id) {
		log.debug("getting TopLevelAssembly instance with id: " + id);
		try {
			TopLevelAssembly instance = (TopLevelAssembly) sessionFactory
					.getCurrentSession()
					.get("com.avnet.alapps.model.dbconnect.TopLevelAssembly",
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

	public List findByExample(TopLevelAssembly instance) {
		log.debug("finding TopLevelAssembly instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.dbconnect.TopLevelAssembly")
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
