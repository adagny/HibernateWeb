package com.fpmislata.persistencia.hibernate.util;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class GenericIntegratorImpl implements Integrator{

	@Override
	public void disintegrate(SessionFactoryImplementor arg0, SessionFactoryServiceRegistry arg1) {
	}

	@Override
	public void integrate(Metadata arg0, SessionFactoryImplementor arg1, SessionFactoryServiceRegistry sfsr) {
		  final EventListenerRegistry eventListenerRegistry = sfsr.getService(EventListenerRegistry.class);
		  
	        prependListeners(eventListenerRegistry);
		
	}
	
    private void prependListeners(EventListenerRegistry eventListenerRegistry) {
        eventListenerRegistry.prependListeners(EventType.PRE_INSERT, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.PRE_LOAD, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.PRE_UPDATE, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.PRE_DELETE, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.POST_INSERT, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.POST_LOAD, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.POST_UPDATE, new GenericEventListenerImpl());
        eventListenerRegistry.prependListeners(EventType.POST_DELETE, new GenericEventListenerImpl());
    }

}
