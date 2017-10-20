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
 * Home object for domain model class DataCaptureCharacteristic.
 * @see com.avnet.alapps.model.dbconnect.DataCaptureCharacteristic
 * @author Hibernate Tools
 */
public class DataCaptureCharacteristicHome {

	private static final Log log = LogFactory
			.getLog(DataCaptureCharacteristicHome.class);

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

	public void persist(DataCaptureCharacteristic transientInstance) {
		log.debug("persisting DataCaptureCharacteristic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DataCaptureCharacteristic instance) {
		log.debug("attaching dirty DataCaptureCharacteristic instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DataCaptureCharacteristic instance) {
		log.debug("attaching clean DataCaptureCharacteristic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DataCaptureCharacteristic persistentInstance) {
		log.debug("deleting DataCaptureCharacteristic instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DataCaptureCharacteristic merge(
			DataCaptureCharacteristic detachedInstance) {
		log.debug("merging DataCaptureCharacteristic instance");
		try {
			DataCaptureCharacteristic result = (DataCaptureCharacteristic) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DataCaptureCharacteristic findById(java.math.BigDecimal id) {
		log.debug("getting DataCaptureCharacteristic instance with id: " + id);
		try {
			DataCaptureCharacteristic instance = (DataCaptureCharacteristic) sessionFactory
					.getCurrentSession()
					.get("com.avnet.alapps.model.dbconnect.DataCaptureCharacteristic",
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

	public List findByExample(DataCaptureCharacteristic instance) {
		log.debug("finding DataCaptureCharacteristic instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.dbconnect.DataCaptureCharacteristic")
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
