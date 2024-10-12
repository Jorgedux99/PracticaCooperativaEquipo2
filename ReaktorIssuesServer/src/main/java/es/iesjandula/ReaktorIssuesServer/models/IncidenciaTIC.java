package es.iesjandula.ReaktorIssuesServer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IncidenciaTIC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String aula;
	private String profesor;
	private String fecha;
	private String descripcion;
	private String estado;
}
