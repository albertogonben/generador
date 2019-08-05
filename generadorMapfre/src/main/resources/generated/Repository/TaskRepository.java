package com.mapfre.gaia.amap3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapfre.gaia.amap3.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
