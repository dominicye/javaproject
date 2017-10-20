package com.avnet.alapps.model.dbconnect;

// Generated Jan 4, 2016 4:31:44 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class ItsUnitDetail.
 * @see com.avnet.alapps.model.dbconnect.ItsUnitDetail
 * @author Hibernate Tools
 */
public class ItsUnitDetailHome {

	private static final Log log = LogFactory.getLog(ItsUnitDetailHome.class);

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

	public void persist(ItsUnitDetail transientInstance) {
		log.debug("persisting ItsUnitDetail instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ItsUnitDetail instance) {
		log.debug("attaching dirty ItsUnitDetail instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItsUnitDetail instance) {
		log.debug("attaching clean ItsUnitDetail instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ItsUnitDetail persistentInstance) {
		log.debug("deleting ItsUnitDetail instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ItsUnitDetail merge(ItsUnitDetail detachedInstance) {
		log.debug("merging ItsUnitDetail instance");
		try {
			ItsUnitDetail result = (ItsUnitDetail) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItsUnitDetail findById(long id) {
		log.debug("getting ItsUnitDetail instance with id: " + id);
		try {
			ItsUnitDetail instance = (ItsUnitDetail) sessionFactory
					.getCurrentSession().get(
							"com.avnet.alapps.model.dbconnect.ItsUnitDetail",
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

	public List findByExample(ItsUnitDetail instance) {
		log.debug("finding ItsUnitDetail instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.dbconnect.ItsUnitDetail")
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
