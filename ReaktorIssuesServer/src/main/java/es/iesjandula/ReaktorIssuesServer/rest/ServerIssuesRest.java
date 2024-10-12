package es.iesjandula.ReaktorIssuesServer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.ReaktorIssuesServer.repository.IssuesRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/issues")
@Slf4j
public class ServerIssuesRest {
	
	@Autowired
	private IssuesRepository issuesRepository;	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/new", consumes = "multipart/form-data")
	public ResponseEntity<?> nuevaIncidencia(@RequestParam(required = true) long id,
											@RequestParam(required = true) String aula,
											@RequestParam(required = true) String profesor,
											@RequestParam(required = true) String fecha,
											@RequestParam(required = true) String descripcion,
											@RequestParam(required = true) String estado)
	{
		try 
		{
			
			
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
	public ResponseEntity<?> cambiarEstadoIncidencia(@RequestParam(required = true) long id)
	{
		try 
		{
			
			
		}catch(Exception exception) 
		{
			
			
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cancel", consumes = "multipart/form-data")
	public ResponseEntity<?> nuevaIncidencia(@RequestParam(required = true) long id)
	{
		try 
		{
			
			
		}catch(Exception exception) 
		{
			
			
		}
		return null;
	}
	
}
	


