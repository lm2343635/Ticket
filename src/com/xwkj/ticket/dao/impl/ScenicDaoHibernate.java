package com.xwkj.ticket.dao.impl;
import java.util.List;

import com.xwkj.common.hibernate3.support.PageHibernateDaoSupport;
import com.xwkj.ticket.dao.ScenicDao;
import com.xwkj.ticket.domain.Scenic;

public class ScenicDaoHibernate extends PageHibernateDaoSupport implements ScenicDao {

	@Override
	public Scenic get(String sid) {
		return getHibernateTemplate().get(Scenic.class, sid);
	}

	@Override
	public String save(Scenic scenic) {
		return (String)getHibernateTemplate().save(scenic);
	}

	@Override
	public void update(Scenic scenic) {
		getHibernateTemplate().update(scenic);
	}

	@Override
	public void delete(Scenic scenic) {
		getHibernateTemplate().delete(scenic);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Scenic> findAll() {
		return getHibernateTemplate().find("from Scenic order by createDate desc");
	}

}
