package com.dovile.convertscurrency.repositories;

import com.dovile.convertscurrency.entities.ClientAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientActionRepository extends JpaRepository<ClientAction, Integer> {
}
