package com.dovile.convertscurrency.repositories;

import com.dovile.convertscurrency.entities.ConfigDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author barkauskaite.dovile@gmail.com
 */
@Repository
public interface ConfigDateRepository extends JpaRepository<ConfigDate, Integer> {
}
