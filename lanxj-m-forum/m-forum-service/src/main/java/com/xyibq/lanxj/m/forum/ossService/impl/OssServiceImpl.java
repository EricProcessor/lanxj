//package com.xyibq.lanxj.m.forum.ossService.impl;
//
//import java.io.*;
//import java.util.UUID;
//import com.jcloud.jss.JingdongStorageService;
//import com.jcloud.jss.service.BucketService;
//import com.xyibq.lanxj.m.forum.ossService.OssService;
//import com.xyibq.lanxj.m.forum.util.JingdongStorageServiceUtil;
//import net.sf.jmimemagic.Magic;
//import net.sf.jmimemagic.MagicMatch;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class OssServiceImpl implements OssService{
//
//    private static final Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);
//
//    private static String URL_FORMAT = "//{0}/{1}";
//
//    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
//    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“API文档 >1 API简介和基本概念 > 访问域名”，
//    // 链接地址是：
//    // endpoint的格式形如“s-bj.jcloud.com”，注意http://后不带bucket名称，
//    // 比如“bucket-name.s-bj.jcloud.com”，是错误的endpoint，请去掉其中的“bucket-name”。
//
//    //@Value("${jd.oss.accessKey}")
//    private static String accessKey = "4143CEFDFCDB3FC380CF68F0207552F9";
//    //@Value("${jd.oss.secreteKey}")
//    private static String secreteKey = "78063F620A571BD4D14160A3655ECB40";
//
//    private static String endpoint = "s-bj.jcloud.com";
//
//    private static String bucketName = "jss-forum-picture";
//    private static String objectName = "objectTest";
//
//    private String uploadImage(Long platformId, byte[] bytes, boolean cdn) throws Exception {
//
//        String imgSuffix = "jpg";
//        Magic parser = new Magic() ;
//        MagicMatch match = parser.getMagicMatch(bytes);
//        if ("image/jpeg".equals(match.getMimeType())) {
//            imgSuffix = "jpg";
//        } else if ("image/gif".equals(match.getMimeType())) {
//            imgSuffix = "gif";
//        } else if ("image/png".equals(match.getMimeType())) {
//            imgSuffix = "png";
//        }
//
//        JingdongStorageService jss = null;
//        String fileUrl = null;
//        InputStream inputStream = null;
//        try{
//            jss = getJssCurrent();
//            BucketService bks = getBucketService(jss, JingdongStorageServiceUtil.BUCKET_NAME);
////            jss.bucket(bucket).acl().allowAnyoneRead().set();
//            String key = UUID.randomUUID().toString() + "." + imgSuffix;
//            inputStream = new ByteArrayInputStream(bytes);
//            bks.object(key).entity(inputStream.available(), inputStream).put();
//
////            if(cdn) {	//取cdn域名
////                String cdnHost;
////                fileUrl = MessageFormat.format(URL_FORMAT, new Object[]{cdnHost, key});
////            }else{
////                //fileUrl = MessageFormat.format(URL_FORMAT, new Object[]{JingdongStorageServiceUtil.JSS_JD_HOST_NAME_V2, JingdongStorageServiceUtil.BUCKET_NAME, key});
////                fileUrl = MessageFormat.format(URL_FORMAT, new Object[]{JingdongStorageServiceUtil.JSS_JD_HOST_NAME_V2, key});
////            }
//        }catch (Exception e){
//            logger.error("上传图片失败", e);
//            e.printStackTrace();
//            throw e;
//        } finally {
//            if(inputStream !=null){
//                inputStream.close();
//            }
//        }
//        logger.info("上传完成");
//        return fileUrl;
//    }
//
//    /**
//     * Jss 对象获取
//     */
//    private JingdongStorageService getJssCurrent() {
//        return JingdongStorageServiceUtil.getJssCurrent();
//    }
//
//    /**
//     * 获取桶
//     */
//    private BucketService getBucketService(JingdongStorageService jss,String bucketName) {
//        if (!jss.hasBucket(bucketName)) {
//            jss.bucket(bucketName).create();
//        }
//        return jss.bucket(bucketName);
//    }
//
//
//}
