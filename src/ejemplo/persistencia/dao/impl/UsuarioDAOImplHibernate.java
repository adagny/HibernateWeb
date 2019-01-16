package ejemplo.persistencia.dao.impl;

import com.fpmislata.persistencia.dao.impl.GenericDAOImplHibernate;

import ejemplo.dominio.Usuario;
import ejemplo.persistencia.dao.UsuarioDAO;

public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario,Integer> implements UsuarioDAO{

}
