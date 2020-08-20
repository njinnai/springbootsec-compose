package SpringBootSecu;
import java.util.Collection;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Service
public class LoginService implements UserDetailsService {
    
    @Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	LoginRepository repository;

	//@Autowired
	//public LoginService(LoginRepository loginRepository) {
	//	this.repository = loginRepository;
	//}

    @Override
	@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //if ("admin".equals(username)) {
        //    List<GrantedAuthority> authorities = new ArrayList<>();
        //    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //    return new UserDetailsImpl(username, this.passwordEncoder.encode("admin"), authorities);
        //}
        
        //if ("user".equals(username)) {
        //    List<GrantedAuthority> authorities = new ArrayList<>();
        //    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //    return new UserDetailsImpl(username, this.passwordEncoder.encode("user"), authorities);
        //}
       
	Optional<Login> login = repository.findByName(username);
        if (login.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
	if (login == null) {
		throw new UsernameNotFoundException(username);
	}
	return new UserDetailsImpl(login.get(), getAuthorities(login.get()));

            //List<GrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            //return new User(username, this.passwordEncoder.encode(login.get()), authorities);

        // ユーザーが見つからなかった
        //throw new UsernameNotFoundException(username);
    }

    private Collection<GrantedAuthority> getAuthorities(Login account) {
        //認証が通った時にユーザに与える権限の範囲を設定する。
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

}
