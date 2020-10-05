package cn.tedu.schoolshop.api.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RegisterUserDTO implements Serializable {

    @Pattern(regexp = "^[\\u0391-\\uFFE5\\w]+$", message = "注册失败!只允许汉字、英文字母、数字及下划线")
    private String nickname;
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "注册失败！密码至少包含数字和英文，长度6-20!")
    private String password;
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "注册失败！手机号码格式错误！")
    private String phone;

    //备用注册属性
    private String username;
    private Integer gender;
    private String email;


}
