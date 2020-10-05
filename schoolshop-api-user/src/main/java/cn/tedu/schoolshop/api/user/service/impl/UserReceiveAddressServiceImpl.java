package cn.tedu.schoolshop.api.user.service.impl;


import cn.tedu.schoolshop.api.user.mapper.UserReceiveAddressMapper;
import cn.tedu.schoolshop.api.user.service.IUserReceiveAddressService;
import cn.tedu.schoolshop.model.UserReceiveAddress;
import cn.tedu.schoolshop.vo.userReceiveAddress.UserReceiveAddressVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-28
 */
@Service
public class UserReceiveAddressServiceImpl extends ServiceImpl<UserReceiveAddressMapper, UserReceiveAddress> implements IUserReceiveAddressService {

    @Autowired
    UserReceiveAddressMapper addressMapper;

    @Override
    public List<UserReceiveAddressVo> findByUserId(Integer userId) {
        return addressMapper.findByUserId(userId);
    }

    @Override
    public boolean updateDefault(UserReceiveAddress address) {
        List<UserReceiveAddressVo> addressVos = addressMapper.findByUserId(address.getUserId());
        for (UserReceiveAddressVo addressVo : addressVos) {
            if (addressVo.getState() == 2) {
                UserReceiveAddress address1 = new UserReceiveAddress()
                        .setId(addressVo.getId())
                        .setState(1);
                addressMapper.updateById(address1);
            }
        }
        int update = addressMapper.updateById(address);
        if (update==1){
            return true;
        }
        return false;
    }

    @Override
    public UserReceiveAddressVo findDefaultAddrByUserId(Integer userId) {

        return addressMapper.findDefaultAddrByUserId(userId);
    }


}
