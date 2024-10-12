package es.iesjandula.ReaktorIssuesServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iesjandula.ReaktorIssuesServer.models.IncidenciaTIC;

public interface IssuesRepository extends JpaRepository<IncidenciaTIC, Long>{

}
