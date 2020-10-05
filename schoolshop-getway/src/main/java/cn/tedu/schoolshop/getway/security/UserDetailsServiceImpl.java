package cn.tedu.schoolshop.getway.security;

import cn.tedu.schoolshop.getway.service.UserService;
import cn.tedu.schoolshop.model.User;
import cn.tedu.schoolshop.security.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/11 17:13
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = userService.findByNickName(username);
        System.out.println(user);
        if (user == null) {
            return null;
        }


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("/api-user/**"));

        LoginUserInfo userInfo = new LoginUserInfo(
                user.getNickname(),
                user.getPassword(),
                user.getIsEnabled() == 1,
                true,
                true,
                user.getIsLocked() == 0,
                authorities
        );
        userInfo.setId(user.getId());
        userInfo.setNickname(user.getNickname());
        return userInfo;

    }

}
