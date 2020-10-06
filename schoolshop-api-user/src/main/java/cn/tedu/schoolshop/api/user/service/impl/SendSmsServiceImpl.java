package cn.tedu.schoolshop.api.user.service.impl;

import cn.tedu.schoolshop.api.user.Utils.RedisUtils;
import cn.tedu.schoolshop.api.user.service.SendSmsService;
import cn.tedu.schoolshop.exception.service.IllegalParameterException;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.util.aliyun.SendSmsUtil;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 15:02
 */
@Service
@Slf4j
public class SendSmsServiceImpl implements SendSmsService {
    @Autowired
    private RedisUtils redisUtils;
    @Value("${project.reg.templateCode}")
    private String templateCode;

    @Override
    public R sendRegCode(String phone) {
        if (phone == null) {
            return R.failure(R.State.ERR_ILLEGAL_PARAMETER, new IllegalParameterException("手机号错误"));
        }

        //创建随机4位验证码
        Map<String, Object> map = new HashMap<>();
        String code = RandomStringUtils.random(4, false, true);
        map.put("code", code);
        log.debug("{}:的验证码是:{}", phone, code);

        //redis存注册验证码
        redisUtils.setHash(phone, "regCode", code);
        //redis存超时时间
        redisUtils.setHash(phone, "regTimeOut", new Date().getTime());


        CommonResponse response = null;
        try {
            response = SendSmsUtil.getCommonResponse(phone, templateCode, map);
        } catch (ClientException e) {
            return R.failure(R.State.ERR_UNKNOWN, new ClientException("系统异常,发送验证码失败"));
        }


        log.debug("response.getData:{}", response.getData());
        log.debug("发送结果{}", response.getHttpResponse().isSuccess());

        if (response.getHttpResponse().isSuccess()) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UNKNOWN, new ClientException("发送验证码失败"));
        }

    }
}
