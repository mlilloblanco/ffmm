package org.mlb.ffmm.repositorios;

import java.util.List;

import org.mlb.ffmm.modelos.Serie;
import org.mlb.ffmm.modelos.SerieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, SerieId> {

	@Query(value="select s.*\r\n" + 
			"from series s \r\n" + 
			"inner join fondosmutuos fm \r\n" + 
			"on s.rut = fm.rut \r\n" + 
			"where fm.vigencia = ?1", nativeQuery=true)
	List<Serie> findByVigencia(int vigencia);
}
