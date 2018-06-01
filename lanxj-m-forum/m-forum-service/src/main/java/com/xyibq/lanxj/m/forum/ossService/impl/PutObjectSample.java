//package com.xyibq.lanxj.m.forum.service.impl;
//
//import com.jcloud.jss.JingdongStorageService;
//import com.jcloud.jss.service.ObjectService;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.io.File;
//import com.jcloud.jss.Credential;
//import com.jcloud.jss.JingdongStorageService;
//import com.jcloud.jss.client.ClientConfig;
//import com.jcloud.jss.constant.ByteUnit;
//import com.jcloud.jss.constant.JssHeaders;
//import com.jcloud.jss.domain.StorageClass;
//import com.jcloud.jss.service.ObjectService;
//
//
//public class PutObjectSample {
//
//    public static void main(String[] args) throws FileNotFoundException {
//
//        //您的AccessKey和SecretKey可以登陆到京东云存储的控制台，在【Access Key 管理】中查看。
//        String accessKey = "4143CEFDFCDB3FC380CF68F0207552F9";
//        String secreteKey = "78063F620A571BD4D14160A3655ECB40";
//        // endpoint以华北为例，其它region请按实际情况填写
//        String endPoint = "s-bj.jcloud.com";
//        File file = new File("D:/test.png");
//        String bucketName = "sdk-example";
//        String objectName = "index.html";
//        //ClientConfig当前为默认配置，用户可根据需要自行配置，如设置连接超时时间等
//        ClientConfig config = new ClientConfig();
//
//        //构造JingdongStorageService实例
//        Credential credential = new Credential(accessKey, secreteKey);
//        JingdongStorageService jss = new JingdongStorageService(credential, config);
//        //配置endPoint
//        jss.setEndpoint(endPoint);
//
//
//        //PutObject -- 流式上传
//        //创建objectService实例
//        ObjectService inputStreamPutService = jss.bucket(bucketName).object(objectName);
//        //使用低冗余存储，则使用该句代码
//        //putInputStreamService.getBuilder().getHeaders().put(JssHeaders.X_JSS_STORAGE_CLASS, StorageClass.ReducedRedundancy.toString());
//
//        //获取输入流
//        InputStream inputStream = new FileInputStream(file);
//        //获取流长度
//        long contentLength = file.length();
//        //设置上传文件Content-type为"text/html"。函数返回上传数据的Etag,目前Etag的值为上传数据的MD5
//        String inputStreamMd5 = inputStreamPutService.entity(contentLength, inputStream).contentType("text/html").put();
//        //若对上传文件进行加密，则使用该句代码
//        //String inputStreamMd5 = objectService.entity(contentLength,inputStream).contentType("text/html").put(true);
//
//
//        //PutObject -- 文件上传
//        //创建objectService实例
//        ObjectService filePutService = jss.bucket(bucketName).object(objectName);
//        //使用低冗余存储，则使用该句代码
//        //putFileService.getBuilder().getHeaders().put(JssHeaders.X_JSS_STORAGE_CLASS, StorageClass.ReducedRedundancy.toString());
//
//        //设置上传文件Content-type为"text/html"。函数返回上传数据的Etag,目前Etag的值为上传数据的MD5
//        String fileMd5 = filePutService.entity(file).contentType("text/html").put();
//        //若对上传文件进行加密，则使用该句代码
////		System.out.println("putObject -- 文件上传\tMD5 = "+fileMd5);
//
//        //JingdongStorageService对象内部维护一组HTTP连接池，在不使用该对象之前需要调用其destroy方法关闭连接池，
//        //请注意，一旦调用destroy方法，该对象就不能再次被使用，否则将会抛出异常。
//        jss.destroy();
//
//    }
//}
