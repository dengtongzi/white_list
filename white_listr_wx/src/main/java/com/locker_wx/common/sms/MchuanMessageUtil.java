package com.locker_wx.common.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.locker_wx.common.sms.entity.MchuanMessage;
import com.locker_wx.common.sms.entity.MchuanMessageParams;
import com.locker_wx.common.sms.entity.MchuanMessageSubmit;

public class MchuanMessageUtil {

    private static final String SYS_CONFIG_USER_ID = "203639";
    private static final String SYS_CONFIG_PASSWORD = "032382db8c417b57d79bbe81c11ae82b";
    private static final String SYS_CONFIG_ADDRESS = "http://112.74.139.4:8002/sms3_api/jsonapi/jsonrpc2.jsp";

    public static JSONObject send(List<String> phone, String msg) {
        if (msg == null || phone == null || phone.isEmpty()) {
            return null;
        }

        // 生成请求内容
        String content = bulidSendContent(phone, msg);
        if (StringUtils.isBlank(content)) {
            return null;
        }

        System.out.println("================request=======================");
        System.out.println(content);
        System.out.println("================request=======================");

        // 发送请求
        String sendApi = SYS_CONFIG_ADDRESS;
        String result = HttpUtil.doPostJson(sendApi, content);
        System.out.println("================response=======================");
        System.out.println(result);
        System.out.println("================response=======================");
        // 解析返回的请求, 返回发送结果
        
        JSONObject json = JSONObject.parseObject(result);
        if(json.get("error") ==  null) {
        	JSONArray jsonArray = json.getJSONArray("result");
        	if(jsonArray.isEmpty()) {
        		Object obj = jsonArray.get(0);
        		JSONObject resJson = JSONObject.parseObject(JSON.toJSONString(obj));
        		if(json.isEmpty()) {
        			return null;
        		}else {
        			return resJson;
        		}
        	}
        }
		return null;
    }

    private static String getErrorMsg(String result) {
        if (StringUtils.isBlank(result)) {
            return "接口无返回数据";
        }

        Map map = JsonUtil.stringToCollect(result);
        if (map.containsKey("error")
                && map.get("error") instanceof JSONObject) {
            JSONObject obj = (JSONObject) map.get("error");
            return obj.get("message").toString();
        }

        return "接口数据返回格式变动或不正确";
    }

    /**
     * 只支持单个校验
     * @param result
     * @return
     */
    public static boolean isSuccess(String result) {
        if (StringUtils.isBlank(result)) {
            return false;
        }

        Map map = JsonUtil.stringToCollect(result);
        if (map.containsKey("result")
                && map.get("result") instanceof JSONArray) {
            JSONArray arr = (JSONArray) map.get("result");
            if (!arr.isEmpty()) {
                JSONObject obj = (JSONObject) arr.get(0);
                if (obj.get("return").equals("0")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Map parseResult(String result) {
        if (StringUtils.isBlank(result)) {
            return new HashMap();
        }
        Map<String, String> resultMap = new HashMap<>();
        Map map = JsonUtil.stringToCollect(result);
        if (map.containsKey("result") && map.get("result") instanceof JSONArray) {
            JSONArray arr = (JSONArray) map.get("result");
            if (!arr.isEmpty()) {
                for (Object arrObj : arr) {
                    JSONObject obj = (JSONObject) arrObj;
                    resultMap.put(obj.get("phone").toString(), obj.get("return").toString());
                }
            }
        }

        return resultMap;
    }

    /**
     * {
     *     "id": id ,
     *     "method":"接口类型，发送为send(必填)",
     *     "params":{
     *         "userid":"账号(必填)",
     *         "password":"密码(必填)",
     *         "submit": [{
     *             "content":"短信内容(必选)",
     *             "phone":"电话号码，若多个号码用”,”隔开(必选)"
     *          },{
     *             "content":"短信内容(必选)",
     *             "phone":"电话号码，若多个号码用”,”隔开(必选)"
     *          },
     *          ……
     *          ,{
     *             "content":"短信内容(必选)",
     *             "phone":"电话号码，若多个号码用”,”隔开(必选)"
     *          }]
     *     }
     * }
     *
     * @param msg
     * @return
     */
    private static String bulidSendContent(List<String> phones, String msg) {
        MchuanMessage content = new MchuanMessage();
        content.setId(String.valueOf(System.currentTimeMillis()));
        content.setMethod("send");

        MchuanMessageParams params = new MchuanMessageParams();
        String USER_ID = SYS_CONFIG_USER_ID;
        String PASSWORD = SYS_CONFIG_PASSWORD;
        params.setUserid(USER_ID);
        params.setPassword(PASSWORD);

        List<MchuanMessageSubmit> submits = new ArrayList<>();
        for (String phone : phones) {
            MchuanMessageSubmit submit = new MchuanMessageSubmit();
            submit.setPhone(phone);
            submit.setContent(msg);
            submits.add(submit);
        }

        params.setSubmit(submits);
        content.setParams(params);
        String contentStr = JsonUtil.toJSONString(content);

        return contentStr;
    }
    /**
	 * 生成6位随机数验证码
	 * @return
	 */
	public static String vcode(){
		String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
	}
}


