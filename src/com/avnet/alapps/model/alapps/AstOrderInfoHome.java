package com.avnet.alapps.model.alapps;

// Generated Aug 17, 2016 2:43:00 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class AstOrderInfo.
 * @see com.avnet.alapps.model.alapps.AstOrderInfo
 * @author Hibernate Tools
 */
public class AstOrderInfoHome {

	private static final Log log = LogFactory.getLog(AstOrderInfoHome.class);

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

	public void persist(AstOrderInfo transientInstance) {
		log.debug("persisting AstOrderInfo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstOrderInfo instance) {
		log.debug("attaching dirty AstOrderInfo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstOrderInfo instance) {
		log.debug("attaching clean AstOrderInfo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstOrderInfo persistentInstance) {
		log.debug("deleting AstOrderInfo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstOrderInfo merge(AstOrderInfo detachedInstance) {
		log.debug("merging AstOrderInfo instance");
		try {
			AstOrderInfo result = (AstOrderInfo) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstOrderInfo findById(com.avnet.alapps.model.alapps.AstOrderInfoId id) {
		log.debug("getting AstOrderInfo instance with id: " + id);
		try {
			AstOrderInfo instance = (AstOrderInfo) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.alapps.AstOrderInfo", id);
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

	public List findByExample(AstOrderInfo instance) {
		log.debug("finding AstOrderInfo instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.alapps.AstOrderInfo")
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
