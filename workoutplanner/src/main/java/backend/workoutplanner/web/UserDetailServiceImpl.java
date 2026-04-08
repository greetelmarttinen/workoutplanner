package backend.workoutplanner.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.workoutplanner.domain.User;
import backend.workoutplanner.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository uRepository;

    private UserDetailServiceImpl(UserRepository userRepository) {
        this.uRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = uRepository.findByUsername(username);
        // haetaan user reposta käyttäjän tiedot
        // ja muutetaan springin ymmärtämään muotoon
        UserDetails user = new org.springframework.security.core.userdetails.User(username,
                currentUser.getPasswordHash(), AuthorityUtils.createAuthorityList(currentUser.getRole()));

        return user;
    }

}
