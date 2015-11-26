package com.xwkj.ticket.dao.impl;

import com.xwkj.common.hibernate3.support.PageHibernateDaoSupport;
import com.xwkj.ticket.dao.TicketDao;
import com.xwkj.ticket.domain.Ticket;

public class TicketDaoHibernate extends PageHibernateDaoSupport implements TicketDao {

	@Override
	public Ticket get(String tid) {
		return getHibernateTemplate().get(Ticket.class, tid);
	}

	@Override
	public String save(Ticket ticket) {
		return (String)getHibernateTemplate().save(ticket);
	}

	@Override
	public void update(Ticket ticket) {
		getHibernateTemplate().update(ticket);
	}

	@Override
	public void delete(Ticket ticket) {
		getHibernateTemplate().delete(ticket);
	}

}
