package cn.tedu.schoolshop.util.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Map;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 12:33
 */
public class SendSmsUtil {


    public static CommonResponse getCommonResponse(String phone, String templateCode, Map<String, Object> map) throws ClientException {
        DefaultProfile profile =
                DefaultProfile.getProfile("cn-hangzhou", "LTAI4G5S9dPANY2USonRN6k5", "M12htn9eUaFp0o6Xyl8NKvDKlsHu2l");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "邓强的校园平台");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));

        return client.getCommonResponse(request);
    }
}
