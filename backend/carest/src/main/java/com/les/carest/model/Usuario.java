//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.les.carest.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(
    name = "usuario"
)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(
        strategy = GenerationType.UUID
    )
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    @OneToMany
    private List<Permissao> role;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public String getPassword() {
        return this.senha;
    }

    public String getUsername() {
        return this.email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
