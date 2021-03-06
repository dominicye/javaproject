package com.avnet.alapps.model.alapps;

// Generated Jan 13, 2016 4:22:01 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class AstPartAsmExcluded.
 * @see com.avnet.alapps.model.alapps.AstPartAsmExcluded
 * @author Hibernate Tools
 */
public class AstPartAsmExcludedHome {

	private static final Log log = LogFactory
			.getLog(AstPartAsmExcludedHome.class);

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

	public void persist(AstPartAsmExcluded transientInstance) {
		log.debug("persisting AstPartAsmExcluded instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstPartAsmExcluded instance) {
		log.debug("attaching dirty AstPartAsmExcluded instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstPartAsmExcluded instance) {
		log.debug("attaching clean AstPartAsmExcluded instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstPartAsmExcluded persistentInstance) {
		log.debug("deleting AstPartAsmExcluded instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstPartAsmExcluded merge(AstPartAsmExcluded detachedInstance) {
		log.debug("merging AstPartAsmExcluded instance");
		try {
			AstPartAsmExcluded result = (AstPartAsmExcluded) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstPartAsmExcluded findById(java.math.BigDecimal id) {
		log.debug("getting AstPartAsmExcluded instance with id: " + id);
		try {
			AstPartAsmExcluded instance = (AstPartAsmExcluded) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.alapps.AstPartAsmExcluded",
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

	public List findByExample(AstPartAsmExcluded instance) {
		log.debug("finding AstPartAsmExcluded instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.alapps.AstPartAsmExcluded")
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
