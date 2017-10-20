package com.avnet.alapps.model.gsfc;

// Generated Apr 14, 2015 5:50:13 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.avnet.alapps.common.AlAppsConstants;

/**
 * Home object for domain model class UserRoleOperation.
 * @see com.avnet.alapps.model.gsfc.UserRoleOperation
 * @author Hibernate Tools
 */
public class UserRoleOperationHome {

	private static final Log log = LogFactory
			.getLog(UserRoleOperationHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_GSFC);
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(UserRoleOperation transientInstance) {
		log.debug("persisting UserRoleOperation instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserRoleOperation instance) {
		log.debug("attaching dirty UserRoleOperation instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserRoleOperation instance) {
		log.debug("attaching clean UserRoleOperation instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UserRoleOperation persistentInstance) {
		log.debug("deleting UserRoleOperation instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserRoleOperation merge(UserRoleOperation detachedInstance) {
		log.debug("merging UserRoleOperation instance");
		try {
			UserRoleOperation result = (UserRoleOperation) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserRoleOperation findById(java.math.BigDecimal id) {
		log.debug("getting UserRoleOperation instance with id: " + id);
		try {
			UserRoleOperation instance = (UserRoleOperation) sessionFactory
					.getCurrentSession()
					.get("com.avnet.alapps.model.gsfc.UserRoleOperation", id);
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

	public List findByExample(UserRoleOperation instance) {
		log.debug("finding UserRoleOperation instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.avnet.alapps.model.gsfc.UserRoleOperation")
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
