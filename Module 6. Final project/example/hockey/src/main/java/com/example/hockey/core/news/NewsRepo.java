package com.example.hockey.core.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, Long> {
}
