package com.springbootpeserta.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "bootcamp")
public class Bootcamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idbootcamp;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpeserta", referencedColumnName = "id")
    private Peserta id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idbatch", referencedColumnName = "idbatch")
    private Batch idbatch;
    
    private String joindate;
}