package com.kevin.usc.portal.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.usc.base.utils.SpringBeanUtil;
import com.kevin.usc.portal.config.TestDbConfig;
import com.kevin.usc.security.dynamicPermission.DynamicFilterInvocationSecurityMetadataSource;
import com.kevin.usc.security.dynamicPermission.RoleHasPermissionBean;
import com.kevin.usc.security.dynamicPermission.RoleHasPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@Slf4j
public class TestController {

    //private DynamicFilterInvocationSecurityMetadataSource dynamicFilterInvocationSecurityMetadataSource;

    @RequestMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "hello";
    }
    @RequestMapping("/hello2")
    public String hello2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "hello2";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }
    @GetMapping("/session/out")
    public String sessionOut() {
        return "sessionOut";
    }

    @GetMapping("/sms/code")
    @ResponseBody
    public String sms(String mobile, HttpSession session) {
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);
        Map<String, Object> map = new HashMap<>(16);
        map.put("mobile", mobile);
        map.put("code", code);
        session.setAttribute("mobileInfo", map);
        log.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
        return "你的手机号"+mobile+"验证码是"+code;
    }


    @RequestMapping("/tt")
    @ResponseBody
    public String tt() {
        System.out.println("1");
        return "tt";
    }
    @RequestMapping("/reSetPermission")
    @ResponseBody
    public String reSetPermission() {
        TestDbConfig.clearRoleHasPermissionList();
        TestDbConfig.roleHasPermissionList.add(new RoleHasPermissionBean("/tt", "ROLE_DOCKER"));
        SpringBeanUtil.getBean(DynamicFilterInvocationSecurityMetadataSource.class).reSetPermissionList();
        System.out.println("1");
        return "yyy";
    }

    public static void main(String[] args) {
        String content = "{\n" +
                "  \"contractRoot\": {\n" +
                "    \"svcCont\": {\n" +
                "      \"requestObject\": {\n" +
                "        \"PosOrderRequest\": {\n" +
                "          \"lan_id\": \"731\",\n" +
                "          \"service_action\": \"instChange\",\n" +
                "          \"order_action\": \"saveOrder\",\n" +
                "          \"service_offer_id\": \"2573\",\n" +
                "          \"cust_id\": \"30335049\",\n" +
                "          \"user_id\": \"1965868\",\n" +
                "          \"PosCompInstance\": [\n" +
                "            {\n" +
                "              \"action_type\": \"M\",\n" +
                "              \"comp_inst_id\": \"7202010903\",\n" +
                "              \"PosProdInst\": [\n" +
                "                {\n" +
                "                  \"acc_num\": \"731CSE000007114\",\n" +
                "                  \"action_type\": \"M\",\n" +
                "                  \"lan_id\": \"731\",\n" +
                "                  \"prod_id\": \"80000030\",\n" +
                "                  \"attributes\": {\n" +
                "                    \"col8\": \"1000\"\n" +
                "                  }\n" +
                "                }\n" +
                "              ],\n" +
                "              \"PosOfferInst\": [\n" +
                "                {\n" +
                "                  \"action_type\": \"M\",\n" +
                "                  \"offer_id\": \"755773030\",\n" +
                "                  \"attributes\": {\n" +
                "                    \"attain_max_speed\": \"N\",\n" +
                "                    \"lower_speed_volume\": \"1000\"\n" +
                "                  },\n" +
                "                  \"offer_inst_id\": \"7201753907\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"tcpCont\": {\n" +
                "      \"svcCode\": \"20200200030003\",\n" +
                "      \"appPwd\": \"4QrcOUm6Wau+VuBX8g+IPg==\",\n" +
                "      \"sign\": \"bAfWWOd9Dj2gLD071CDjeg==\",\n" +
                "      \"appKey\": \"CRMOC\",\n" +
                "      \"reqTime\": \"20191219133718493\",\n" +
                "      \"dstSysId\": \"OrderCenter\",\n" +
                "      \"version\": \"1.0\",\n" +
                "      \"transactionId\": \"CRMOC201912191337180000017303\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        JSONObject jsonObject = new JSONObject();
        jsonObject = jsonObject.parseObject(content);
        JSONObject contractRoot = (JSONObject) jsonObject.get("contractRoot");
        JSONObject svcCont = (JSONObject) contractRoot.get("svcCont");
        JSONObject requestObject = (JSONObject) svcCont.get("requestObject");
        JSONObject PosOrderRequest = (JSONObject) requestObject.get("PosOrderRequest");
        JSONArray PosCompInstance = (JSONArray) PosOrderRequest.get("PosCompInstance");
        JSONObject PosCompInstance0 = (JSONObject)PosCompInstance.get(0);
        JSONArray PosProdInst = (JSONArray) PosCompInstance0.get("PosProdInst");
        JSONObject PosProdInst0 = (JSONObject) PosProdInst.get(0);
        JSONObject attributes = (JSONObject) PosProdInst0.get("attributes");
        Object col8 = attributes.get("col8");
        Map<String, Object> map = new HashMap();
        map.put("isCycle","Y");
        map.put("col8",attributes.get("col8"));
        attributes.put("col8",map);
        String o = JSON.toJSONString(contractRoot);
        System.out.println(o);
    }

}