package com.dovile.convertscurrency.repositories;

import com.dovile.convertscurrency.entities.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Integer> {

    @Query(name = "CurrencyData.findByType")
    CurrencyData findByType(@Param("type") String type);
}