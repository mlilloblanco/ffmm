package org.mlb.ffmm.repositorios;

import org.mlb.ffmm.modelos.Serie;
import org.mlb.ffmm.modelos.SerieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, SerieId> {

}
