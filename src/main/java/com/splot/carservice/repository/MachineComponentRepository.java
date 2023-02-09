package com.splot.carservice.repository;

import com.splot.carservice.model.MachineComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineComponentRepository extends JpaRepository<MachineComponent, Long> {
}
