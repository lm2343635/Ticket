package com.xwkj.ticket.dao;

import com.xwkj.ticket.domain.Ticket;

public interface TicketDao {
	Ticket get(String tid);
	String save(Ticket ticket);
	void update(Ticket ticket);
	void delete(Ticket ticket);
}
