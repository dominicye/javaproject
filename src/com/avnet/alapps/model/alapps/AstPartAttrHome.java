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
 * Home object for domain model class AstPartAttr.
 * @see com.avnet.alapps.model.alapps.AstPartAttr
 * @author Hibernate Tools
 */
public class AstPartAttrHome {

	private static final Log log = LogFactory.getLog(AstPartAttrHome.class);

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

	public void persist(AstPartAttr transientInstance) {
		log.debug("persisting AstPartAttr instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstPartAttr instance) {
		log.debug("attaching dirty AstPartAttr instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstPartAttr instance) {
		log.debug("attaching clean AstPartAttr instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstPartAttr persistentInstance) {
		log.debug("deleting AstPartAttr instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstPartAttr merge(AstPartAttr detachedInstance) {
		log.debug("merging AstPartAttr instance");
		try {
			AstPartAttr result = (AstPartAttr) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstPartAttr findById(java.math.BigDecimal id) {
		log.debug("getting AstPartAttr instance with id: " + id);
		try {
			AstPartAttr instance = (AstPartAttr) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.alapps.AstPartAttr", id);
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

	public List findByExample(AstPartAttr instance) {
		log.debug("finding AstPartAttr instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria("com.avnet.alapps.model.alapps.AstPartAttr")
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
