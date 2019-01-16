package ejemplo.persistencia.dao.impl;

import javax.validation.ConstraintViolation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fpmislata.excepciones.BussinessException;

import ejemplo.dominio.Usuario;
import ejemplo.persistencia.dao.ProfesorDAO;
import ejemplo.persistencia.dao.UsuarioDAO;



public class DUsuario {
	

	SessionFactory sessionFactory;

	Configuration configuration = new Configuration();

	{
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	public void pruebaException() {
		Session session = sessionFactory.openSession();

		Usuario usuario = new Usuario("lramire", "Luis", "Ramirez", "Cano", "1234567", "1234567");

		try {
			session.beginTransaction();

			session.save(usuario);

			session.getTransaction().commit();
		} catch (javax.validation.ConstraintViolationException cve) {
			session.getTransaction().rollback();
			System.out.println("No se ha podido insertar el profesor debido a los siguientes errores:");
			for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
				System.out.println("En el campo '" + constraintViolation.getPropertyPath() + "':"
						+ constraintViolation.getMessage());
			}
		} catch (org.hibernate.exception.ConstraintViolationException cve) {
			session.getTransaction().rollback();
			System.out.println("No se ha podido insertar el profesor debido al siguiente error:");
			System.out.println(
					"El valor ya existe." + cve.getLocalizedMessage() + " en la propiedad " + cve.getConstraintName());
		}

		session.close();
	}

	public void exportar() {
		new org.hibernate.tool.hbm2ddl.SchemaExport(configuration).setOutputFile("script.sql").setDelimiter(";")
				.create(true, false);
		// SchemaExport export = new SchemaExport(configuration);
		// export.setDelimiter(";");
		// export.setOutputFile("create_schema.sql");
		// export.setFormat(true);
		// export.execute(true, false, false, false);

	}


	public static void main(String... args) throws BussinessException {
		System.out.println("INICIO");
		ApplicationContext context =new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		DUsuario du = new DUsuario();
		// du.exportar();
//		du.pruebaException();
		DUsuario du = new DUsuario();
//		ProfesorDAO profesorDAO = new ProfesorDAOImplHibernate();
//		du.profesorDAO.findAll();
//		profesorDAO.findAll();
	}

}
