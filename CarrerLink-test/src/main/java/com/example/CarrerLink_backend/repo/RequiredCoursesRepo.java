package com.example.CarrerLink_backend.repo;

import com.example.CarrerLink_backend.entity.RequiredCources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RequiredCoursesRepo extends JpaRepository<RequiredCources,Integer> {
}
