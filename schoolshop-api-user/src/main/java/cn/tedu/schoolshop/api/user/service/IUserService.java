package cn.tedu.schoolshop.api.user.service;

import cn.tedu.schoolshop.api.user.dto.RegisterUserDTO;
import cn.tedu.schoolshop.model.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-10
 */
public interface IUserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param registerUserDTO 提交的参数
     */
    void registerUser(RegisterUserDTO registerUserDTO);
}
