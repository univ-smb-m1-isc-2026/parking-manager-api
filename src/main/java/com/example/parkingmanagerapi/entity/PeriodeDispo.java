package com.example.parkingmanagerapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PeriodeDispo")
public class PeriodeDispo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriodeDispo;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Place_id", nullable = false)
    private Place place;
}
