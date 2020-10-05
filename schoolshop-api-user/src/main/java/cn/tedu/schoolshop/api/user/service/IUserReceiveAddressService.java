package cn.tedu.schoolshop.api.user.service;


import cn.tedu.schoolshop.model.UserReceiveAddress;
import cn.tedu.schoolshop.vo.userReceiveAddress.UserReceiveAddressVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-28
 */
public interface IUserReceiveAddressService extends IService<UserReceiveAddress> {

    List<UserReceiveAddressVo> findByUserId(Integer userId);

    boolean updateDefault(UserReceiveAddress address);

    UserReceiveAddressVo findDefaultAddrByUserId(Integer userId);
}
