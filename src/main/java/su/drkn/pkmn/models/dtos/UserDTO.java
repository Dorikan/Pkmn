package su.drkn.pkmn.models.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import su.drkn.pkmn.models.user.AuthorityEntity;
import su.drkn.pkmn.models.user.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDTO implements UserDetails {

    private String username;
    private String password;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDTO(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.enabled = userEntity.getEnabled();
        this.authorities = userEntity.getAuthorities().stream()
                .map(authority -> (GrantedAuthority) authority)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
