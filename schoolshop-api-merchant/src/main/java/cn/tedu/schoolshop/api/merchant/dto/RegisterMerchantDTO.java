package cn.tedu.schoolshop.api.merchant.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 3:04
 */
@Data
public class RegisterMerchantDTO implements Serializable {
    @Pattern(regexp = "^[\\u0391-\\uFFE5\\w]+$", message = "注册失败!用户名只支持汉字、英文字母、数字及下划线")
    private String nickname;
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "注册失败！密码至少包含数字和英文，长度6-20!")
    private String password;
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "注册失败！手机号码格式错误！")
    private String phone;
    @Pattern(regexp = "^[\\u0391-\\uFFE5\\w]+$", message = "注册失败！店铺名支持汉字、英文字母、数字及下划线！")
    private String storeName;
    private Integer category1Id;
    private Integer category2Id;


    //备用注册属性
    private String username;
    private Integer gender;
    private String email;
}
