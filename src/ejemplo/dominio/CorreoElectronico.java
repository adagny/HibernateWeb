package ejemplo.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="CorreosElectronicos")
public class CorreoElectronico implements Serializable{
	
	@Id
	@Column(name="IdCorreo")
	@GeneratedValue(generator="generador_clave_foranea")
	@org.hibernate.annotations.GenericGenerator(
			name="generador_clave_foranea",
			strategy="foreign",
			parameters=@Parameter(name="property", value="profesor")
			)
	private int idCorreo;
	
	@Column(name="DireccionCorreo")
	private String direccionCorreo;
	
	@ManyToOne
	@JoinColumn(name="IdProfesor")
	private Profesor profesor;
	
	public CorreoElectronico(){
		
	}
	
	public CorreoElectronico(/*int idCorreo,*/ String direccionCorreo, Profesor profesor){
//		this.idCorreo = idCorreo;
		this.direccionCorreo = direccionCorreo;
		this.profesor = profesor;
		
	}

	public int getIdCorreo() {
		return idCorreo;
	}

	public void setIdCorreo(int idCorreo) {
		this.idCorreo = idCorreo;
	}

	public String getDireccionCorreo() {
		return direccionCorreo;
	}

	public void setDireccionCorreo(String direccionCorreo) {
		this.direccionCorreo = direccionCorreo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
}
