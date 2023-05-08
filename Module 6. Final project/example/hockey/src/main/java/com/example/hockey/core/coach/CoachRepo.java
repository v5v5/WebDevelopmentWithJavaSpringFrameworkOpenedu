package com.example.hockey.core.coach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepo extends JpaRepository<Coach, Long> {
}
