package cn.tedu.schoolshop.util.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/10/6 12:33
 */
public class SendSms {


    public static CommonResponse getCommonResponse(String phone) throws ClientException {
        DefaultProfile profile =
                DefaultProfile.getProfile("cn-hangzhou", "LTAI4G5S9dPANY2USonRN6k5", "M12htn9eUaFp0o6Xyl8NKvDKlsHu2l");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "校园购科技");

        Map<String, Object> map = new HashMap<>();
        map.put("code",2233);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));

        return client.getCommonResponse(request);
//        try {

//        System.out.println(response.getData());
//    } catch (
//    ServerException e) {
//        e.printStackTrace();
//    } catch (ClientException e) {
//        e.printStackTrace();
//    }
    }
}
