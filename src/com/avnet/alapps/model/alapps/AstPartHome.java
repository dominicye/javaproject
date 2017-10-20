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
 * Home object for domain model class AstPart.
 * @see com.avnet.alapps.model.alapps.AstPart
 * @author Hibernate Tools
 */
public class AstPartHome {

	private static final Log log = LogFactory.getLog(AstPartHome.class);

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

	public void persist(AstPart transientInstance) {
		log.debug("persisting AstPart instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(AstPart instance) {
		log.debug("attaching dirty AstPart instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AstPart instance) {
		log.debug("attaching clean AstPart instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(AstPart persistentInstance) {
		log.debug("deleting AstPart instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AstPart merge(AstPart detachedInstance) {
		log.debug("merging AstPart instance");
		try {
			AstPart result = (AstPart) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AstPart findById(java.math.BigDecimal id) {
		log.debug("getting AstPart instance with id: " + id);
		try {
			AstPart instance = (AstPart) sessionFactory.getCurrentSession()
					.get("com.avnet.alapps.model.alapps.AstPart", id);
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

	public List findByExample(AstPart instance) {
		log.debug("finding AstPart instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.avnet.alapps.model.alapps.AstPart")
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
