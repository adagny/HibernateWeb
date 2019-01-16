package ejemplo.persistencia.dao.impl;

import org.springframework.stereotype.Component;

import com.fpmislata.persistencia.dao.impl.GenericDAOImplHibernate;

import ejemplo.dominio.Profesor;
import ejemplo.persistencia.dao.ProfesorDAO;

public class ProfesorDAOImplHibernate extends GenericDAOImplHibernate<Profesor,Integer> implements ProfesorDAO{

}
