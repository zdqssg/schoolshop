package cn.tedu.schoolshop.api.user.mapper;


import cn.tedu.schoolshop.model.UserReceiveAddress;
import cn.tedu.schoolshop.vo.userReceiveAddress.UserReceiveAddressVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-28
 */
@Repository
public interface UserReceiveAddressMapper extends BaseMapper<UserReceiveAddress> {

    List<UserReceiveAddressVo> findByUserId(Integer userId);

    UserReceiveAddressVo findDefaultAddrByUserId(Integer userId);
}
