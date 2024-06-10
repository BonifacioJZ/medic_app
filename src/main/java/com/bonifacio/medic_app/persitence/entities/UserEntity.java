package com.bonifacio.medic_app.persitence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserEntity extends PersonEntity implements UserDetails {
    @NotBlank
    @NotEmpty
    @Size(min = 8,max = 32)
    @Column(unique = true)
    private String username;
    @Column
    @NotEmpty
    @NotBlank
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,targetEntity = RoleEntity.class,cascade = {CascadeType.PERSIST})
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns =@JoinColumn(name = "role_id") )
    private Set<RoleEntity> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<ExpedientEntity> expedientEntities;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        this.getRoles().forEach(role ->{
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
        });
        this.getRoles().stream().flatMap(role-> role.getPermissions().stream())
                .forEach(permission->{
                    authorityList.add(new SimpleGrantedAuthority(permission.getPermission()));
                });
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNoExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNoLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNoExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
