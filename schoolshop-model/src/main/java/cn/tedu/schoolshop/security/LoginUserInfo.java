package cn.tedu.schoolshop.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/14 4:17
 */
@Getter
@Setter

public class LoginUserInfo extends User {

    private Integer id;
    private String nickname;

    public LoginUserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public LoginUserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    @Override
    public String toString() {
        return "LoginUserInfo >>> " +
                "id=" + id +
                ", nickname=" + nickname +
                ',' + super.toString();
    }

}
