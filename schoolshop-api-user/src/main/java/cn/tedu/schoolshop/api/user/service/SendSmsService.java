package cn.tedu.schoolshop.api.user.service;

import cn.tedu.schoolshop.util.R;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下接口吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 15:02
 */
public interface SendSmsService {
    R sendRegCode(String phone);
}
