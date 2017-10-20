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
 * Home object for domain model class DataCaptureDetail.
 * @see com.avnet.alapps.model.dbconnect.DataCaptureDetail
 * @author Hibernate Tools
 */
public class DataCaptureDetailHome {

	private static final Log log = LogFactory
			.getLog(DataCaptureDetailHome.class);

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

	public void persist(DataCaptureDetail transientInstance) {
		log.debug("persisting DataCaptureDetail instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DataCaptureDetail instance) {
		log.debug("attaching dirty DataCaptureDetail instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DataCaptureDetail instance) {
		log.debug("attaching clean DataCaptureDetail instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DataCaptureDetail persistentInstance) {
		log.debug("deleting DataCaptureDetail instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DataCaptureDetail merge(DataCaptureDetail detachedInstance) {
		log.debug("merging DataCaptureDetail instance");
		try {
			DataCaptureDetail result = (DataCaptureDetail) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DataCaptureDetail findById(java.math.BigDecimal id) {
		log.debug("getting DataCaptureDetail instance with id: " + id);
		try {
			DataCaptureDetail instance = (DataCaptureDetail) sessionFactory
					.getCurrentSession()
					.get("com.avnet.alapps.model.dbconnect.DataCaptureDetail",
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

	public List findByExample(DataCaptureDetail instance) {
		log.debug("finding DataCaptureDetail instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.dbconnect.DataCaptureDetail")
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
