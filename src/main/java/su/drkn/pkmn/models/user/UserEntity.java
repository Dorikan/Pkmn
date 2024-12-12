package su.drkn.pkmn.models.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<AuthorityEntity> authorities = new LinkedHashSet<>();

}
