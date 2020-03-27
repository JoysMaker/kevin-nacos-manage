package com.kevin.usc.base.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class JsonUtils {

    private static List<Map.Entry<String, JsonNode>> resultMap = new ArrayList<>();

    public static List<Map.Entry<String, JsonNode>> jsonNodeFind(JsonNode jsonNode) throws IOException {

            Iterator<Map.Entry<String, JsonNode>> nodes = jsonNode.fields();
            while (nodes.hasNext()) {
                Map.Entry<String, JsonNode> next = nodes.next();
                resultMap.add(next);
                if(next.getValue().isArray()){
                    for(JsonNode jsonNode1 : next.getValue()){
                        jsonNodeFind(jsonNode1);
                    }
                }
                if(next.getValue().isContainerNode()){
                    jsonNodeFind(next.getValue());
                }

            }
        //System.out.println(jsonNode.textValue());

        return resultMap;
    }

    public static void main(String[] args) throws IOException {
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

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode1 = mapper.readTree(content);
        List<Map.Entry<String, JsonNode>> entries = JsonUtils.jsonNodeFind(jsonNode1);
        entries.forEach( t-> {if(t.getKey().equals("PosProdInst")){
            System.out.println(t.getValue());
            return;
        }});
        System.out.println(entries.size());
    }
}
