package org.mlb.ffmm.repositorios;

import java.util.List;

import org.mlb.ffmm.modelos.Cuota;
import org.mlb.ffmm.modelos.CuotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, CuotaId> {
	@Query(value="select * \r\n" + 
			"from cuotas c \r\n" + 
			"where c.rut = ?1 and c.serie=?2", nativeQuery=true)
	List<Cuota> findCuotaByRutAndSerie(String rut, String serie);
}
