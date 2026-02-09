package com.example.parkingmanagerapi.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Entity
@Data
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String surname;
    private String password;
    private String mail;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;
}
