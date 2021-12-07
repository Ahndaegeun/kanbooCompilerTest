package com.example.compilertest.service;

import com.example.compilertest.entity.CompilerContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopmilerContentRepository extends JpaRepository<CompilerContent, Long> {
}
