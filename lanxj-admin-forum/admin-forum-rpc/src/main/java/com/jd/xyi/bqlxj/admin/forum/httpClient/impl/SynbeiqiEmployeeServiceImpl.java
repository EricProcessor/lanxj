package com.jd.xyi.bqlxj.admin.forum.httpClient.impl;

import com.jd.xyi.bqlxj.admin.forum.httpClient.SynbeiqiEmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SynbeiqiEmployeeServiceImpl implements SynbeiqiEmployeeService {

    /**
     * 同步北汽员工信息
     */
    public  void synbeiqiEmployeeInfo() {

        String appKey = "075db62710772d949ac11a25d1bc6dcc";
        String userId = "11111";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String signStr = "secret" + "&appKey=" + appKey +"&timestamp=" + timestamp +"&userId="+userId + "&secret";
        String sign = md5(signStr);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream is = null;
        String url = "http://xyi-uac.bjev.con.cn/getUserMobileById";
        //封装请求参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("appKey", "075db62710772d949ac11a25d1bc6dcc"));
        params.add(new BasicNameValuePair("sign", sign));
        params.add(new BasicNameValuePair("timestamp", "1111"));
        params.add(new BasicNameValuePair("userId", "1111"));

        String str = "";
        try {
            //转换为键值对
            str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            System.out.println(str);
            //创建Get请求
            HttpGet httpGet = new HttpGet(url + "?" + sign);
            //执行Get请求，
            response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                is = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            //关闭输入流，释放资源
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //消耗实体内容
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭相应 丢弃http连接
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * md5加密
     * @param str
     * @return
     */
    public static String md5(String str) {
        try {
            return DigestUtils.md5Hex(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void  main(String[] args){

        new SynbeiqiEmployeeServiceImpl().synbeiqiEmployeeInfo();
    }


}
