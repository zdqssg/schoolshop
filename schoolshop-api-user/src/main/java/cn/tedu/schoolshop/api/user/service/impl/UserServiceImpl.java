package cn.tedu.schoolshop.api.user.service.impl;

import cn.tedu.schoolshop.api.user.Utils.RedisUtils;
import cn.tedu.schoolshop.api.user.dto.RegisterUserDTO;
import cn.tedu.schoolshop.api.user.mapper.UserMapper;
import cn.tedu.schoolshop.api.user.service.IUserService;
import cn.tedu.schoolshop.exception.code.CodeErrorException;
import cn.tedu.schoolshop.exception.code.CodeTimeOutException;
import cn.tedu.schoolshop.exception.service.InsertException;
import cn.tedu.schoolshop.exception.service.NickNameDuplicateException;
import cn.tedu.schoolshop.exception.service.PhoneDuplicateException;
import cn.tedu.schoolshop.model.User;
import cn.tedu.schoolshop.util.PasswordUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-10
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;


    /**
     * 用户注册
     *
     * @param registerUserDTO 提交的参数
     */
    @Override
    public void registerUser(RegisterUserDTO registerUserDTO) {
        String nickname = registerUserDTO.getNickname();
        String phone = registerUserDTO.getPhone();
        String code = registerUserDTO.getCode();

        long regTimeOut = (long) redisUtils.getHash(phone, "regTimeOut");
        int time = (int) (new Date().getTime() - regTimeOut)/1000/60;
        log.debug("时间间隔{}",time);
        if (time>5){
            throw new CodeTimeOutException("验证码超时,请重新发送");
        }

        String regCode = (String) redisUtils.getHash(phone, "regCode");
        if (!code.equals(regCode)) {
            throw new CodeErrorException("验证码错误");
        }

        if (userMapper.selectByNickName(nickname) != null) {
            throw new NickNameDuplicateException("注册失败,该昵称已经被注册!");
        }

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
