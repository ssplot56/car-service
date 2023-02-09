package com.splot.carservice.repository;

import com.splot.carservice.model.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {
}
