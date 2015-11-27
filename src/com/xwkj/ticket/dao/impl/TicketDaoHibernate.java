package com.xwkj.ticket.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xwkj.common.hibernate3.support.PageHibernateDaoSupport;
import com.xwkj.ticket.dao.TicketDao;
import com.xwkj.ticket.domain.Scenic;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findPayed(Date date, String tno, String telephone, Scenic scenic) {
		String hql="from Ticket where pay=true and date=?";
		List<Object> objects=new ArrayList<>();
		objects.add(date);
		if(tno!=null&&!tno.equals("")) {
			hql+=" and tno like ?";
			objects.add("%"+tno+"%");
		}
		if(telephone!=null&&!telephone.equals("")) {
			hql+=" and telephone like ?";
			objects.add("%"+telephone+"%");
		}
		if(scenic!=null) {
			hql+=" and scenic=?";
			objects.add(scenic);
		}
		hql+=" order by createDate";
		Object [] objs=new Object[objects.size()];
		for(int i=0; i<objects.size(); i++) {
			objs[i]=objects.get(i);
		}
		return getHibernateTemplate().find(hql, objs);
	}

}
