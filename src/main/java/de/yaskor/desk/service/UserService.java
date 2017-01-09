package de.yaskor.desk.service;

import de.yaskor.desk.entity.User;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author samil kale
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

    @PostConstruct
    private void initalize() {
    }

    public User getActiveUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = new User("admin", "admin");
        if (user == null) {
            log.warn("username '" + username + "' not found");
            throw new UsernameNotFoundException("");
        }
        return user;
    }
}
