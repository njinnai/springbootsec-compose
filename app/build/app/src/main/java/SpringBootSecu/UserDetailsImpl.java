package SpringBootSecu;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsImpl extends User {
    //ユーザ情報。
    private final Login account;

    public UserDetailsImpl(Login account,
            Collection<GrantedAuthority> authorities) {

        super(account.getName(), account.getPassword(),
                true, true, true, true, authorities);

        this.account = account;
    }

    public Login getAccount() {
        return account;
    }

}
