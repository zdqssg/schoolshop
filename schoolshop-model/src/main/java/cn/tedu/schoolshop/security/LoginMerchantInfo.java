package cn.tedu.schoolshop.security;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 10:38
 */
@Getter
@Setter
@Accessors(chain = true)
public class LoginMerchantInfo extends User {
    private Integer id;
    private String nickname;
    private String phone;
    private String storeName;
    private String email;
//    private String storeHeadPhoto;
    private Integer category1Id;
    private Integer category2Id;

    public LoginMerchantInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public LoginMerchantInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    @Override
    public String toString() {
        return "LoginMerchantInfo{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", category1Id=" + category1Id +
                ", category2Id=" + category2Id +
                '}';
    }
}
