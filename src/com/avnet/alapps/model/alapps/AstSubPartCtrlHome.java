package com.avnet.alapps.model.alapps;

// Generated Nov 5, 2015 12:40:16 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class AstSubPartCtrl.
 * @see com.avnet.alapps.model.alapps.AstSubPartCtrl
 * @author Hibernate Tools
 */
public class AstSubPartCtrlHome {

	private static final Log log = LogFactory.getLog(AstSubPartCtrlHome.class);

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

	public void persist(AstSubPartCtrl transientInstance) {
		log.debug("persisting AstSubPartCtrl instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstSubPartCtrl instance) {
		log.debug("attaching dirty AstSubPartCtrl instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstSubPartCtrl instance) {
		log.debug("attaching clean AstSubPartCtrl instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstSubPartCtrl persistentInstance) {
		log.debug("deleting AstSubPartCtrl instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstSubPartCtrl merge(AstSubPartCtrl detachedInstance) {
		log.debug("merging AstSubPartCtrl instance");
		try {
			AstSubPartCtrl result = (AstSubPartCtrl) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstSubPartCtrl findById(java.math.BigDecimal id) {
		log.debug("getting AstSubPartCtrl instance with id: " + id);
		try {
			AstSubPartCtrl instance = (AstSubPartCtrl) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.alapps.AstSubPartCtrl", id);
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

	public List findByExample(AstSubPartCtrl instance) {
		log.debug("finding AstSubPartCtrl instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.alapps.AstSubPartCtrl")
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
