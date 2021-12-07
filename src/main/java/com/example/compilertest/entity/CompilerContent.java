package com.example.compilertest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompilerContent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cotentIdx;

    @ManyToOne
    @JoinColumn(name = "compilerIdx")
    private Compiler compiler;
    private String content;
}
