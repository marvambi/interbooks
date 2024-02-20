package com.marvambi.interbooks.repository;

import com.marvambi.interbooks.model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  List<Employee> findByPublished(boolean published);

  List<Employee> findByTitleContaining(String title);
}