package cn.tedu.schoolshop.getway.security;


import cn.tedu.schoolshop.getway.mapper.MerchantMapper;
import cn.tedu.schoolshop.getway.service.IMerchantService;
import cn.tedu.schoolshop.model.Merchant;
import cn.tedu.schoolshop.security.LoginMerchantInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 10:27
 */
@Component
public class MerchantDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("nickname",username);
        Merchant merchant = merchantMapper.selectOne(queryWrapper);
        if (merchant == null) {
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("/api-merchant/**"));


        LoginMerchantInfo merchantInfo = new LoginMerchantInfo(
                merchant.getNickname(),
                merchant.getPassword(),
                merchant.getIsEnabled() == 1,
                true,
                true,
                merchant.getIsLocked() == 0,
                authorities
        );
        merchantInfo
                .setId(merchant.getId())
                .setNickname(merchant.getNickname())
                .setStoreName(merchant.getStoreName())
                .setPhone(merchant.getPhone())
                .setEmail(merchant.getEmail())
//                .setStoreHeadPhoto(merchant.getStoreHeadPhoto())
                .setCategory1Id(merchant.getCategory1Id())
                .setCategory2Id(merchant.getCategory2Id());
        return merchantInfo;
    }
}
