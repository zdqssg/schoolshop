package cn.tedu.schoolshop.getway.mapper;


import cn.tedu.schoolshop.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-10
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByNickname(String nickname);
}
