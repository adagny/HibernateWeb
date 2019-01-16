package ejemplo.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.IndexColumn;


@Entity
@Table(name="profesor")
public class Profesor implements Serializable {
	

//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="Id")
	@GeneratedValue( generator = "generador_propietario_hibernate_increment" )
	@org.hibernate.annotations.GenericGenerator(
	    name = "generador_propietario_hibernate_increment",
	    strategy = "increment"
	)
	private int id;
	
	
//	@Column(name="nombre")
//	@NotNull
//	@Size(min = 3, max = 50)
//	private String nombre;
//	
//	@Column(name="ape1")
//	@NotNull
//	@Size(min = 3, max = 50)
//	private String ape1;
//	
//	@Column(name="ape2")
//	@Size(min = 3, max = 50)
//	private String ape2;
	
	@Embedded
	@Valid
	private Nombre nombre;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoFuncionario tipoFuncionario;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="IdProfesor")
	@IndexColumn(name="idx")
	private List<CorreoElectronico> correoElectronico;
	
	
	
	public Profesor(){
		
	}
	
	public Profesor(String nombre, String ape1, String ape2){
////		this.id = id;
//		this.nombre = nombre;
//		this.ape1 = ape1;
//		this.ape2 = ape2;
	}
	
	public Profesor(Nombre nombre,TipoFuncionario tipoFuncionario){
		this.nombre = nombre;
		this.tipoFuncionario = tipoFuncionario;
	}
	
	
	
	
	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public Nombre getNombre() {
		return nombre;
	}

	public void setNombre(Nombre nombre) {
		this.nombre = nombre;
	}

	
	
	public List<CorreoElectronico> getCorreoElectronico() {
		return correoElectronico;
	}

//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getApe1() {
//		return ape1;
//	}
//
//	public void setApe1(String ape1) {
//		this.ape1 = ape1;
//	}
//
//	public String getApe2() {
//		return ape2;
//	}
//
//	public void setApe2(String ape2) {
//		this.ape2 = ape2;
//	}

	public void setCorreoElectronico(List<CorreoElectronico> correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	
}
