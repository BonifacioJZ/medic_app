package com.bonifacio.medic_app.persitence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "persons")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Size(max = 100)
    @NotNull
    @Column
    private String name;
    @Size(max = 150)
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Size(max = 13, min = 10)
    private String phone;
    @Email
    @Column
    private String email;
    @Size(max = 200)
    @NotNull
    @Column
    private String colony;
    @Size(max = 200)
    @NotNull
    @Column
    private String city;
    @NotNull
    @Column
    private LocalDate birthday;
    @NotNull
    @Column
    private String address;
    @Size(max = 18, min = 18)
    @NotNull
    @Column(unique = true)
    private String curp;
    @Transient
    private  int age;

    @CreationTimestamp
    private Instant createAt;
    @UpdateTimestamp
    private Instant updateAt;

    public int getAge(){
        LocalDate now = LocalDate.now();
        Period period = Period.between(this.birthday,now);
        return period.getYears();
    }

}
