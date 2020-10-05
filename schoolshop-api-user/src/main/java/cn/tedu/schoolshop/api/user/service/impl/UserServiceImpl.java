package cn.tedu.schoolshop.api.user.service.impl;

import cn.tedu.schoolshop.util.PasswordUtils;
import cn.tedu.schoolshop.api.user.dto.RegisterUserDTO;
import cn.tedu.schoolshop.api.user.service.IUserService;
import cn.tedu.schoolshop.exception.service.InsertException;
import cn.tedu.schoolshop.exception.service.NickNameDuplicateException;
import cn.tedu.schoolshop.exception.service.PhoneDuplicateException;
import cn.tedu.schoolshop.api.user.mapper.UserMapper;
import cn.tedu.schoolshop.model.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param registerUserDTO 提交的参数
     */
    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        String nickname = registerUserDTO.getNickname();
        if (userMapper.selectByNickName(nickname) != null) {
            throw new NickNameDuplicateException("注册失败,该昵称已经被注册!");
        }
        String phone = registerUserDTO.getPhone();
        if (userMapper.selectByPhone(phone) != null) {
            throw new PhoneDuplicateException("注册失败,手机号码已经被注册!");
        }

        User user = new User();
        user.setNickname(registerUserDTO.getNickname());
        user.setPhone(phone);
        String encodePassword = PasswordUtils.encode(registerUserDTO.getPassword());
        user.setPassword(encodePassword);
        int insert = userMapper.insert(user);
        if (insert != 1) {
            throw new InsertException("注册失败！服务器忙，请稍后再次尝试！");
        }
    }
}
