package com.example.compilertest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Compiler {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compilerIdx;

    @ManyToOne
    @JoinColumn
    private Project project;

    private String isFile;
    private String name;

    @ManyToOne
    @JoinColumn
    private Compiler parent;

}
