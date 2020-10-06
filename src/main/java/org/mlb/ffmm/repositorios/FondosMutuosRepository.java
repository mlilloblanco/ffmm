package org.mlb.ffmm.repositorios;

import org.mlb.ffmm.modelos.FondoMutuo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondosMutuosRepository extends JpaRepository<FondoMutuo, Integer> {

}
