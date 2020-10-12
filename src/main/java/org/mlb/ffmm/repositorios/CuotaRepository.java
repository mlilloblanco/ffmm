package org.mlb.ffmm.repositorios;

import org.mlb.ffmm.modelos.Cuota;
import org.mlb.ffmm.modelos.CuotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, CuotaId> {

}
