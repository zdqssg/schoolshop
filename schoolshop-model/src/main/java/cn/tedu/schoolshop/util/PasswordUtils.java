package cn.tedu.schoolshop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 处理密码加密相关任务的工具类
 */
public class PasswordUtils {

    /**
     * 密码加密器
     */
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 私有化构造方法，不允许直接创建对象
     */
    private PasswordUtils() {
        super();
    }

    /**
     * 执行密码加密
     *
     * @param rawPassword 密码的原文，即原始密码
     * @return 密码的密码，即基于原文加密得到的结果
     */
    public static String encode(String rawPassword) {
        String encodePassword = "{bcrypt}"+passwordEncoder.encode(rawPassword);
        return encodePassword;
    }

}
