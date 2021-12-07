package com.example.compilertest.repository;

import com.example.compilertest.entity.Compiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompilerRepository extends JpaRepository<Compiler, Long> {
}
