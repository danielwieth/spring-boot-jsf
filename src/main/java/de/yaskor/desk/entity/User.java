package de.yaskor.desk.entity;

import de.yaskor.desk.enums.Language;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author saka
 */
@Data
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    public String id;
    private String username;
    private String password;
    private boolean admin = false;
    private boolean enabled = true;
    private Language language = Language.EN;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    public List<GrantedAuthority> getAuthorities() {
        return Arrays.asList((GrantedAuthority) () -> admin ? "ADMIN" : "USER");
    }
}