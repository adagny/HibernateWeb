package ejemplo.persistencia.dao.impl;



import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fpmislata.persistencia.hibernate.HibernateUtil;

import ejemplo.dominio.CorreoElectronico;
import ejemplo.dominio.Nombre;
import ejemplo.dominio.Profesor;
import ejemplo.dominio.TipoFuncionario;



public class DProfesor {

	SessionFactory sessionFactory;

//	Configuration configuration = new Configuration();
//	{
//		configuration.configure();
//		sessionFactory = configuration.buildSessionFactory();
//	}

	// public void guardar(){
	//
	//
	// Profesor profesor = new Profesor(/*101,*/"Juan","Peres","Garcia");
	// Session session = this.sessionFactory.openSession();
	// session.beginTransaction();
	//
	// session.save(profesor);
	//
	// session.getTransaction().commit();
	// session.close();
	//
	// }

	// public void guardarDosTablas(){
	//
	//
	// Profesor profesor = new Profesor(/*400,*/"Rosa","La","Golosa");
	// List<CorreoElectronico> correosElectronicos = new
	// ArrayList<CorreoElectronico>();
	// correosElectronicos.add(new
	// CorreoElectronico(/*3,*/"perraxXx@gmail.com",profesor));
	//// correosElectronicos.add(new
	// CorreoElectronico(/*2,*/"puta@gmail.com",profesor));
	//// correosElectronicos.add(new
	// CorreoElectronico(/*1,*/"zorra@gmail.com",profesor));
	//
	// profesor.setCorreoElectronico(correosElectronicos);
	//
	// Session session=sessionFactory.openSession();
	// session.beginTransaction();
	// session.save(profesor);
	// session.getTransaction().commit();
	// session.close();
	// }

	// public void pruebaForanea(){
	// Profesor profesor=new Profesor("Eduardo", "Grau", "Arica");
	//
	// Session session=sessionFactory.openSession();
	// session.beginTransaction();
	//
	// session.save(profesor);
	//
	// session.getTransaction().commit();
	// session.close();
	// }

	// public void pruebaComponente(){
	// Profesor profesor=new Profesor(new Nombre("Gabriel", "Sáez",
	// "Izquierdo"));
	//
	// Session session=sessionFactory.openSession();
	// session.beginTransaction();
	//
	// session.save(profesor);
	//
	// session.getTransaction().commit();
	// session.close();
	// }

	public void pruebaEnum() {
		Profesor profesor = new Profesor(new Nombre("Elias", "Rubio", "Sánchez"), TipoFuncionario.Interino);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(profesor);

		session.getTransaction().commit();
		session.close();
	}

	public void pruebaQuery1() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT p FROM profesor p");
		List<Profesor> profesores = query.list();
		for (Profesor profesor : profesores) {
//			System.out.println(profesor.getNombre().getNombre());
		}
		session.close();
	}

	public void pruebaQuery2() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT p.id,p.nombre FROM Profesor p");
		List<Object[]> listDatos = query.list();
		for (Object[] datos : listDatos) {
			System.out.println(datos[0] + "--" + ((Nombre) datos[1]).getNombreCompleto());
		}
		session.close();
	}

	public void pruebaQueryPaginacion() {
		Session session = sessionFactory.openSession();
		int tamanyoPagina = 10;
		int paginaAMostrar = 7;

		Query query = session.createQuery("SELECT p FROM Profesor p Order By p.id");
		query.setMaxResults(tamanyoPagina);
		query.setFirstResult(paginaAMostrar * tamanyoPagina);
		List<Profesor> profesores = query.list();

		for (Profesor profesor : profesores) {
			System.out.println(profesor.toString());
		}
		session.close();
	}

	public void pruebaQueryXML() {
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("encuentraTodosProfesores");
		List<Profesor> profesores = query.list();
		for (Profesor profesor : profesores) {
//			System.out.println(profesor.getNombre().getNombre());
		}
		session.close();

	}

	public void pruebaParametrosPosicion() {
		String nombre = "ISIDRO";
		String ape1 = "CORTINA";
		String ape2 = "GARCIA";

		Session session = this.sessionFactory.openSession();

		Query query = session.createQuery(
				"SELECT p FROM Profesor p where p.nombre.nombre=? AND p.nombre.ape1=? AND p.nombre.ape2=?");
		// query.setString(0, nombre);
		// query.setString(1, ape1);
		// query.setString(2, ape2);

		// con objetos
		query.setParameter(0, nombre);
		query.setParameter(1, ape1);
		query.setParameter(2, ape2);

		List<Profesor> profesores = query.list();
		if (profesores != null) {
			for (Profesor profesor : profesores) {
				System.out.println(profesor.toString());
			}
		}
		session.close();
	}

	public void pruebaParametrosNombre() {
		String nombre = "ISIDRO";
		String ape1 = "CORTINA";
		String ape2 = "GARCIA";

		Session session = this.sessionFactory.openSession();

		Query query = session.createQuery(
				"SELECT p FROM Profesor p where p.nombre.nombre=:nombre AND p.nombre.ape1=:ape1 AND p.nombre.ape2=:ape2");
		// query.setString("nombre", nombre);
		// query.setString("ape1", ape1);
		// query.setString("ape2", ape2);

		// con objetos
		query.setParameter("nombre", nombre);
		query.setParameter("ape1", ape1);
		query.setParameter("ape2", ape2);

		List<Profesor> profesores = query.list();
		if (profesores != null) {
			for (Profesor profesor : profesores) {
				System.out.println(profesor.toString());
			}
		}
		session.close();
	}

	public void pruebaParametrosNombreConcatenado() {
		String nombre = "ISIDRO";
		String ape1 = "CORTINA";
		String ape2 = "GARCIA";

		Session session = this.sessionFactory.openSession();

		Query query = session
				.createQuery(
						"SELECT p FROM Profesor p where p.nombre.nombre=:nombre AND p.nombre.ape1=:ape1 AND p.nombre.ape2=:ape2")
				.setParameter("nombre", nombre).setParameter("ape1", ape1).setParameter("ape2", ape2);

		// query.setString("nombre", nombre);
		// query.setString("ape1", ape1);
		// query.setString("ape2", ape2);

		// con objetos

		List<Profesor> profesores = query.list();
		if (profesores != null) {
			for (Profesor profesor : profesores) {
				System.out.println(profesor.toString());
			}
		}
		session.close();
	}
	
	public void pruebaLentaCorreos(){
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("SELECT p FROM Profesor p");
		 List<Profesor> profesores = query.list();
		 for (Profesor profesor : profesores) {
		     System.out.println(profesor.toString());
		     for (CorreoElectronico correoElectronico : profesor.getCorreoElectronico()) {
		         System.out.println("\t"+correoElectronico);
		     }
		 }
			session.close();
	}
	
	public void pruebaOptimizadaCorreos(){
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("SELECT p FROM Profesor p LEFT JOIN FETCH p.correoElectronico");
		 List<Profesor> profesores = query.list();
		 for (Profesor profesor : profesores) {
		     System.out.println(profesor.toString());
		     for (CorreoElectronico correoElectronico : profesor.getCorreoElectronico()) {
		         System.out.println("\t"+correoElectronico);
		     }
		 }
		session.close();
	}

	public void pruebaMasOptimizadaCorreos(){
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("SELECT p FROM Profesor p LEFT JOIN FETCH p.correoElectronico");
		 List<Profesor> profesores = query.list();
		
		 Set<Profesor> profesoresSinDuplicar = new LinkedHashSet<Profesor>(profesores);
		 profesores.clear();
		 profesores.addAll(profesoresSinDuplicar);
		
		 for (Profesor profesor : profesores) {
		     System.out.println(profesor.toString());
		     for (CorreoElectronico correoElectronico : profesor.getCorreoElectronico()) {
		         System.out.println("\t"+correoElectronico);
		     }
		 }
			session.close();
	}
	
	public void pruebaSqlQuery(){
		Session session = this.sessionFactory.openSession();
		Query query = session.createSQLQuery("SELECT IdCiclo,nombreCiclo,Horas FROM cicloformativo");
		 List<Object[]> listDatos = query.list();
		
		 for (Object[] datos : listDatos) {
		     System.out.println(datos[0] + "-" + datos[1] + " " + datos[2]);
		 }
		 session.close();
	}
	
	public void pruebaSqlPersonalizadas(){
		Session session = this.sessionFactory.openSession();
		System.out.println("INSERT");
		 Profesor profesor;
		
		 session.beginTransaction();
		     profesor=new Profesor(new Nombre("Celia", "Sanchez", "Jordá"), TipoFuncionario.Carrera) ;
		     session.save(profesor);
		 session.getTransaction().commit();
		 
			System.out.println("UPDATE");		
		 session.beginTransaction();
//		     profesor.setNombre(new Nombre("Juan Carlos","",""));
		     session.update(profesor);
		 session.getTransaction().commit();
		 
			System.out.println("DELETE");
		
		 session.beginTransaction();
		     session.delete(profesor);
		 session.getTransaction().commit();
		 session.close();
	}
	
	public void pruebaEstadosHibernateEvict(){
		
		Session session = sessionFactory.openSession();
		 Profesor profesor =(Profesor)session.get(Profesor.class,1001);
		 session.evict(profesor);
		 session.close();
	}

	
	public void pruebaEstadosHibernate(){
		
		Session session = sessionFactory.openSession();
		 Profesor profesor =(Profesor)session.get(Profesor.class,1002);

		 session.evict(profesor);
//		 profesor.setNombre(new Nombre("Caaaabezaaaa","De","Pija"));
		 
		 
		 
		 session.beginTransaction();

		 session.saveOrUpdate(profesor);
		 session.beginTransaction().commit();
		 
//		 session.merge(profesor);
		 
//		 session.beginTransaction();
//		 session.delete(profesor);
//		 session.beginTransaction().commit();
		 

		 session.close();
	}
	
	public void pruebaNotNullAndSize(){
		Session session = sessionFactory.openSession();
		Profesor profesor = new Profesor(null,null,null);
	
		 try {
		     session.beginTransaction();
		
		     session.save(profesor);
		
		     session.getTransaction().commit();
		 } catch (ConstraintViolationException cve) {
		     session.getTransaction().rollback();
		     System.out.println("No se ha podido insertar el profesor");
		 }
		 session.close();
	}
	
	public void pruebaMEnsajeError(){
		Session session = sessionFactory.openSession();
		Profesor profesor = new Profesor(new Nombre(null, null, "Gomez"),TipoFuncionario.Carrera);
		
		 try {
		     session.beginTransaction();
		
		     session.save(profesor);
		
		     session.getTransaction().commit();
		 } catch (ConstraintViolationException cve) {
		     session.getTransaction().rollback();
		     System.out.println("No se ha podido insertar el profesor debido a los siguientes Errores:");
		     for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
		         System.out.println("En el campo '" + constraintViolation.getPropertyPath() + "':" + constraintViolation.getMessage());
		     }
		 }
		 session.close();
	}
	
	
	public static void main(String[] args) {
		         HibernateUtil.buildSessionFactory();
		
		         try {
		             HibernateUtil.openSessionAndBindToThread();
		
		             Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		             Profesor profesor = (Profesor) session.get(Profesor.class, 1213);
		             System.out.println(profesor.getNombre().getNombreCompleto());
		         } finally {
		             HibernateUtil.closeSessionAndUnbindFromThread();
		         }
		
		         HibernateUtil.closeSessionFactory();
		     }
	
	
//	public static void main(String[] args) {
//		System.out.println("Guardamos");
//		DProfesor dp = new DProfesor();
//		// dp.pruebaForanea();
//		// dp.guardarDosTablas();
//		// dp.pruebaComponente();
//		// dp.pruebaEnum();
//		// dp.pruebaQuery2();
//		// dp.pruebaQueryPaginacion();
//		// dp.pruebaQueryXML();
////		dp.pruebaParametrosNombre();
////		dp.pruebaParametrosPosicion();
////		dp.pruebaLentaCorreos();
////		dp.pruebaOptimizadaCorreos();
////		dp.pruebaMasOptimizadaCorreos();
////		dp.pruebaSqlQuery();
////		dp.pruebaSqlPersonalizadas();
////		dp.pruebaEstadosHibernate();
////		dp.pruebaNotNullAndSize();
//		dp.pruebaMEnsajeError();
//
//	}

}
