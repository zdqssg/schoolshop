package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.getway.mapper.UserMapper;
import cn.tedu.schoolshop.getway.service.UserService;
import cn.tedu.schoolshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/12 15:24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByNickName(String nickname) {
        return userMapper.selectByNickname(nickname);
    }
}
