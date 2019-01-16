package com.fpmislata.persistencia.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

@WebListener
@WebFilter(urlPatterns = {"*.html"})
public class HibernateContextListenerAndFilter implements ServletContextListener, Filter {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.closeSessionFactory();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.buildSessionFactory();
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			HibernateUtil.openSessionAndBindToThread();
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
