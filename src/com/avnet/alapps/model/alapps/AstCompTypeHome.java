package com.avnet.alapps.model.alapps;

// Generated Apr 20, 2016 1:55:17 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class AstCompType.
 * @see com.avnet.alapps.model.alapps.AstCompType
 * @author Hibernate Tools
 */
public class AstCompTypeHome {

	private static final Log log = LogFactory.getLog(AstCompTypeHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_ALAPPS);
		} catch (Exception e) {
			log.error("Could not locate ALAPPSSessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate ALAPPSSessionFactory in JNDI");
		}
	}

	public void persist(AstCompType transientInstance) {
		log.debug("persisting AstCompType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstCompType instance) {
		log.debug("attaching dirty AstCompType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstCompType instance) {
		log.debug("attaching clean AstCompType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstCompType persistentInstance) {
		log.debug("deleting AstCompType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstCompType merge(AstCompType detachedInstance) {
		log.debug("merging AstCompType instance");
		try {
			AstCompType result = (AstCompType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstCompType findById(java.math.BigDecimal id) {
		log.debug("getting AstCompType instance with id: " + id);
		try {
			AstCompType instance = (AstCompType) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.alapps.AstCompType", id);
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

	public List findByExample(AstCompType instance) {
		log.debug("finding AstCompType instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria("com.avnet.alapps.model.alapps.AstCompType")
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
