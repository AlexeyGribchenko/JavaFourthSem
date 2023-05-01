package ru.mirea.task23.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mirea.task23.entities.enums.Role;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Entity
@Table(name="another_usr_table")
@Getter
@Setter
public class AnotherUser implements UserDetails {
    @Id
    @SequenceGenerator(name = "another_user_table_seq", allocationSize = 1)
    @GeneratedValue(generator = "another_user_table_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "usr_role", joinColumns = @JoinColumn(name = "usr_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public AnotherUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AnotherUser() {
    }

    public void setRoles(Role role) {
        this.roles = Collections.singleton(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
