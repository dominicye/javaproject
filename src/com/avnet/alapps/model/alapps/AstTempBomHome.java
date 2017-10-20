package com.avnet.alapps.model.alapps;

// Generated Dec 18, 2015 1:28:30 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class AstTempBom.
 * @see com.avnet.alapps.model.alapps.AstTempBom
 * @author Hibernate Tools
 */
public class AstTempBomHome {

	private static final Log log = LogFactory.getLog(AstTempBomHome.class);

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

	public void persist(AstTempBom transientInstance) {
		log.debug("persisting AstTempBom instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstTempBom instance) {
		log.debug("attaching dirty AstTempBom instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstTempBom instance) {
		log.debug("attaching clean AstTempBom instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstTempBom persistentInstance) {
		log.debug("deleting AstTempBom instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstTempBom merge(AstTempBom detachedInstance) {
		log.debug("merging AstTempBom instance");
		try {
			AstTempBom result = (AstTempBom) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstTempBom findById(java.math.BigDecimal id) {
		log.debug("getting AstTempBom instance with id: " + id);
		try {
			AstTempBom instance = (AstTempBom) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.alapps.AstTempBom", id);
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

	public List findByExample(AstTempBom instance) {
		log.debug("finding AstTempBom instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.avnet.alapps.model.alapps.AstTempBom")
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
