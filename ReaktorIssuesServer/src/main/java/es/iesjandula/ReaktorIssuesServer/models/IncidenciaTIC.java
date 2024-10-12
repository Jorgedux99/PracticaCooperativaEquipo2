package es.iesjandula.ReaktorIssuesServer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class IncidenciaTIC {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String aula;
	private String profesor;
	private String fecha;
	private String descripcion;
	private String estado;
	
	
	
	public IncidenciaTIC(String aula, String profesor, String fecha, String descripcion, String estado) {
		super();
		this.aula = aula;
		this.profesor = profesor;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	
	
}
