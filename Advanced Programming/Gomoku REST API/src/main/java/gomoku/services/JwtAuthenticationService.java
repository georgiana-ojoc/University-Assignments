package gomoku.services;

import gomoku.dtos.players.PlayerCredentialsDTO;
import gomoku.exceptions.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtAuthenticationService implements UserDetailsService {
    @Autowired
    private PlayerService playerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            PlayerCredentialsDTO playerCredentials = playerService.getByUsername(username);
            return new User(playerCredentials.getUsername(), playerCredentials.getPassword(), new ArrayList<>());
        } catch (PlayerNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
