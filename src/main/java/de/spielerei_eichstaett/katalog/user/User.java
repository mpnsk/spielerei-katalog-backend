package de.spielerei_eichstaett.katalog.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@With
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String username;
    String email;

    @Column(length = 68)
    String password;

    boolean enabled = false;
    boolean accountNonExpired = true;
    boolean accountNonLocked = true;
    boolean credentialsNonExpired = true;

    @ElementCollection(fetch = FetchType.EAGER)
//    @ToString.Exclude
    Set<Role> roles = new HashSet<>();

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private Collection<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Enum::name).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public enum Role {
        ADMIN
    }
}