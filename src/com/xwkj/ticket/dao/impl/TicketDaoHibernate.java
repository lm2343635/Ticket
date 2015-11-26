package com.xwkj.ticket.dao.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public Ticket findByTno(String tno) {
		String hql="from Ticket where tno=?";
		List<Ticket> tickets=getHibernateTemplate().find(hql, tno);
		if(tickets.size()==0)
			return null;
		return tickets.get(0);
	}

}
