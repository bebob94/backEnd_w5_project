package com.backEnd_w5_project.auth.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.backEnd_w5_project.auth.entity.Dispositivo;


public interface DispositivoRepository extends PagingAndSortingRepository<Dispositivo, Long>,CrudRepository<Dispositivo, Long> {

}