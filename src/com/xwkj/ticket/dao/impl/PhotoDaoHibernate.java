package com.xwkj.ticket.dao.impl;

import java.util.List;

import com.xwkj.common.hibernate3.support.PageHibernateDaoSupport;
import com.xwkj.ticket.dao.PhotoDao;
import com.xwkj.ticket.domain.Photo;
import com.xwkj.ticket.domain.Scenic;

public class PhotoDaoHibernate extends PageHibernateDaoSupport implements PhotoDao {

	@Override
	public Photo get(String pid) {
		return getHibernateTemplate().get(Photo.class, pid);
	}

	@Override
	public String save(Photo photo) {
		return (String)getHibernateTemplate().save(photo);
	}

	@Override
	public void update(Photo photo) {
		getHibernateTemplate().update(photo);
	}

	@Override
	public void delete(Photo photo) {
		getHibernateTemplate().delete(photo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> findByScenic(Scenic scenic) {
		return getHibernateTemplate().find("from Photo where scenic=? order by upload", scenic);
	}

}
