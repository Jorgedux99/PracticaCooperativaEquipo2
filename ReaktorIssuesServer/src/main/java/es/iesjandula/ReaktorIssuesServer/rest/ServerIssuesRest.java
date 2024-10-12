package es.iesjandula.ReaktorIssuesServer.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.ReaktorIssuesServer.models.IncidenciaTIC;
import es.iesjandula.ReaktorIssuesServer.repository.IssuesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
@Slf4j
public class ServerIssuesRest {
	
	private static final Logger log = LoggerFactory.getLogger(ServerIssuesRest.class);
	
	@Autowired
	private IssuesRepository issuesRepository;	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/new", consumes = "multipart/form-data")
	public ResponseEntity<?> nuevaIncidencia(@RequestParam(required = true) String aula,
											@RequestParam(required = true) String profesor,
											@RequestParam(required = true) String fecha,
											@RequestParam(required = true) String descripcion,
											@RequestParam(required = true) String estado)

	{
		try 
		{
			IncidenciaTIC incidencia = new IncidenciaTIC(aula,profesor,fecha,descripcion,estado);
			
			IncidenciaTIC incidenciaGuardada = this.issuesRepository.save(incidencia);
			
			return ResponseEntity.ok().body(incidenciaGuardada);
			
		}catch(Exception exception) 
		{
			
			
		}
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/show", consumes = "multipart/form-data")
	public ResponseEntity<?> mostrarIncidencias()
	{
		try 
		{
			
			
		}catch(Exception exception) 
		{
			
			
		}
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/changeState", consumes = "multipart/form-data")
	public ResponseEntity<?> cambiarEstadoIncidencia(@RequestParam(required = true) long id,
													 @RequestParam(required = true) String nuevoEstado)
	{
		try 
		{
			Optional<IncidenciaTIC> incidenciaId = this.issuesRepository.findById(id);
			
			if (incidenciaId.isPresent()) {
				IncidenciaTIC incidencia = incidenciaId.get();
				incidencia.setEstado(nuevoEstado);
				
				IncidenciaTIC incidenciaActualizada = this.issuesRepository.save(incidencia);
				
				return ResponseEntity.ok(incidenciaActualizada);
			} else {
				return ResponseEntity.status(404).body("No se encontró la incidencia con el ID: " + id);
			}
			
			
		}catch(Exception exception) 
		{
			log.error("Error al cambiar el estado de la incidencia: ", exception.getMessage());
			return ResponseEntity.status(500).body("Error al cambiar el estado de la incidencia");
			
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cancel", consumes = "multipart/form-data")
	public ResponseEntity<?> cancelarIncidencia(@RequestParam(required = true) long id)
	{
		try 
		{
			Optional<IncidenciaTIC> incidenciaId = this.issuesRepository.findById(id);
			
			if (incidenciaId.isPresent()) {
				IncidenciaTIC incidencia = incidenciaId.get();
				incidencia.setEstado("CANCELADA");
				
				IncidenciaTIC incidenciaActualizada = this.issuesRepository.save(incidencia);
				
				return ResponseEntity.ok(incidenciaActualizada);
			} else {
				return ResponseEntity.status(404).body("No se encontró la incidencia con el ID: " + id);
			}
			
			
		}catch(Exception exception) 
		{
			log.error("Error al cancelar la incidencia: ", exception.getMessage());
			return ResponseEntity.status(500).body("Error al cancelar la incidencia");
			
		}
	}
	
}
	


