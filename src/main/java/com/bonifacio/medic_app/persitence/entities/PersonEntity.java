package com.bonifacio.medic_app.persitence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Size(max = 100)
    @NotEmpty
    @NotBlank
    @Column
    private String name;
    @Size(max = 150)
    @NotBlank
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty
    @NotBlank
    @Size(max = 13, min = 10)
    @Column
    private String phone;
    @Email
    @Column
    private String email;
    @Size(max = 200)
    @NotBlank
    @NotEmpty
    @Column
    private String colony;
    @Size(max = 200)
    @NotBlank
    @NotEmpty
    @Column
    private String city;
    @NotNull
    @Column
    private LocalDate birthday;
    @NotBlank
    @NotEmpty
    @Column
    private String address;
    @Size(max = 18, min = 18)
    @NotBlank
    @NotEmpty
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
