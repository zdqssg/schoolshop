package cn.tedu.schoolshop.api.user.service.impl;

import cn.tedu.schoolshop.api.user.service.SendSmsService;
import cn.tedu.schoolshop.exception.service.IllegalParameterException;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.util.aliyun.SendSms;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 15:02
 */
@Service
public class SendSmsServiceImpl implements SendSmsService {
    @Override
    public R sendRegCode(Integer phone) {
        if (phone == null) {
            return R.failure(R.State.ERR_ILLEGAL_PARAMETER, new IllegalParameterException("手机号错误"));
        }
        CommonResponse response = null;
        try {
            response = SendSms.getCommonResponse(String.valueOf(phone));

        } catch (ClientException e) {
            return R.failure(R.State.ERR_UNKNOWN, new ClientException("系统异常"));
        }
        System.out.println(response.getData());
        if (response.getHttpResponse().isSuccess()) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_UNKNOWN, new ClientException("发送验证码失败"));
        }

    }
}
