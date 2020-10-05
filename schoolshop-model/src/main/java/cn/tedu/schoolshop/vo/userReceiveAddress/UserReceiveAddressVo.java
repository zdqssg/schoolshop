package cn.tedu.schoolshop.vo.userReceiveAddress;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-28
 */
@Data
@Accessors(chain = true)
public class UserReceiveAddressVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String phone;

    private String username;

    private Integer state;

    private String provinceName;
    private String provinceCode;

    private String cityName;
    private String cityCode;

    private String areaName;
    private String areaCode;

    private String streetName;
    private String streetCode;

    private String detail;


}
