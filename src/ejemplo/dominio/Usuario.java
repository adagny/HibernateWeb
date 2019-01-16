package ejemplo.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.validator.constraints.ScriptAssert;

import com.fpmislata.anotaciones.Captions.Caption;


////@ScriptAssert(lang="javascript",script="(_this.password!=null)?  _this.password.equals(_this.confirmPassword) : false",message="Los 2 passwords deben ser iguales")
//@ScriptAssert.List({
//	  @ScriptAssert(lang="javascript",script="(_this.password!=null)?  _this.password.equals(_this.confirmPassword) : false",message="Los 2 passwords deben ser iguales"),
//	  @ScriptAssert(lang="javascript",script="(_this.password=='123456')",message="La contraseña no puede ser tan tonta")
//	})
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable, PreInsertEventListener {

	@Id
	@Column(name = "IdUsuario")
	@GeneratedValue(generator = "generador_propietario_hibernate_increment")
	@org.hibernate.annotations.GenericGenerator(name = "generador_propietario_hibernate_increment", strategy = "increment")
	private int idUsuario;

	@Column(name = "login", unique = true)
	private String login;

	@NotNull
	@Size(min = 3, max = 50)
	@Caption("Nombre")
	private String nombre;

	@NotNull
	@Size(min = 3, max = 50)
	@Caption("Apellido 1°")
	private String ape1;

	@Size(min = 3, max = 50)
	@Caption("Apellido 2°")
	private String ape2;

	@NotNull
	@Size(min = 7, max = 50)
	private String password;

	@NotNull
	@Size(min = 7, max = 50)
	@Column(insertable = false, updatable = false)
	private String confirmPassword;

	@AssertTrue(message = "El login y el password no pueden coincidir")
	private boolean isPasswordValido() {
		if ((login != null) && (login.equalsIgnoreCase(password))) {
			return false;
		} else {
			return true;
		}
	}
	
	@Column
	private Date fechaCreacion;

	public Usuario(String login, String nombre, String ape1, String ape2, String password, String confirmPassword) {
		super();
		this.login = login;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe1() {
		return ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public boolean onPreInsert(PreInsertEvent pie) {
		int propertyNameIndex = getPropertyNameIndex(pie.getPersister().getPropertyNames(), "fechaCreacion");
		         Date fechaCreacion=new Date();
		         pie.getState()[propertyNameIndex] = fechaCreacion;
		         ((Usuario)(pie.getEntity())).setFechaCreacion(fechaCreacion);
		         System.out.println(fechaCreacion);
		         
		return false;
	}

	private int getPropertyNameIndex(String[] propertyNames, String propertyName) {
		for (int i = 0; i < propertyNames.length; i++) {
			if (propertyNames[i].equals(propertyName)) {
				return i;
			}
		}
		return -1;
	}

}