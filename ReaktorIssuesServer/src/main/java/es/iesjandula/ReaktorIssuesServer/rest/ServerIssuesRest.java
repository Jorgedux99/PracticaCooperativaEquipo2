package es.iesjandula.ReaktorIssuesServer.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin(origins = "http://localhost:64309")
public class ServerIssuesRest {
	
	private static final Logger log = LoggerFactory.getLogger(ServerIssuesRest.class);
	
	@Autowired
	private IssuesRepository issuesRepository;	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/new", consumes = "application/json")
	public ResponseEntity<?> nuevaIncidencia(@RequestBody IncidenciaTIC incidencia) {
	    try {
	        IncidenciaTIC incidenciaGuardada = this.issuesRepository.save(incidencia);
	        return ResponseEntity.ok().body(incidenciaGuardada);
	    } catch (Exception exception) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la incidencia.");
	    }
	}

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/show")
	public ResponseEntity<?> mostrarIncidencias()
	{
		try {
	        
	        List<IncidenciaTIC> incidencias = this.issuesRepository.findAll();
	        
	        
	        if (incidencias.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay incidencias disponibles.");
	        }
	        
	        
	        return ResponseEntity.ok().body(incidencias);
	    } catch (Exception exception) {
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Ocurrió un error al obtener las incidencias: " + exception.getMessage());
	    }
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
	


