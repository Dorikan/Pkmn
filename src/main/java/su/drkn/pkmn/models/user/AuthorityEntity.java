package su.drkn.pkmn.models.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class AuthorityEntity implements GrantedAuthority {
    @Id
    @Column(name = "authority", nullable = false, length = 50)
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserEntity username;

}