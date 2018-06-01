//package com.xyibq.lanxj.m.forum.util;
//
//import com.jcloud.jss.Credential;
//import com.jcloud.jss.JingdongStorageService;
//import com.jcloud.jss.client.ClientConfig;
//import com.xyibq.lanxj.m.forum.common.utils.CheckUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Properties;
//
//@Service
//public class JingdongStorageServiceUtil implements InitializingBean {
//	private final static Logger LOG = LoggerFactory.getLogger(JingdongStorageServiceUtil.class);
//
//	private static JingdongStorageService JSS_CURRENT = null;
//	private static JingdongStorageService JSS_JD = null;
//	private static JingdongStorageService JSS_BJ = null;
//
//	@Resource
//	private JssCenterPropertyPlaceholderConfigurer jssCenterPropertyConfigurer;
//
//	/**
//	 * 当前环境JSS
//	 */
//	public static String ACCESS_KEY;
//	public static String SECRET_KEY;
//	public static String BUCKET_NAME;
//	public static String HOST_NAME;
//	public static String PRIVATE_BUCKET_NAME;
//	public static int TIME_OUT ;
//	public static int URI_EXPIRES_TIME;
//
//	public static JingdongStorageService getJssCurrent(){
//		return JSS_CURRENT;
//	}
//
//	public static JingdongStorageService getJssJd(){
//		return JSS_JD;
//	}
//
//	public static JingdongStorageService getJssBj(){
//		return JSS_BJ;
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		try {
//			Properties props = jssCenterPropertyConfigurer.mergeProperties();
//
//			ACCESS_KEY = props.getProperty("ACCESS_KEY");
//			SECRET_KEY = props.getProperty("SECRET_KEY");
//			BUCKET_NAME = props.getProperty("BUCKET_NAME");
//			HOST_NAME = props.getProperty("HOST_NAME");
//			PRIVATE_BUCKET_NAME = props.getProperty("PRIVATE_BUCKET_NAME");
//			TIME_OUT = Integer.parseInt(props.getProperty("TIME_OUT"));
//			URI_EXPIRES_TIME = Integer.parseInt(props.getProperty("URI_EXPIRES_TIME"));
//
//
//			ClientConfig config = new ClientConfig();
//			config.setEndpoint(HOST_NAME);
//			config.setConnectionTimeout(TIME_OUT);
//			JSS_CURRENT = new JingdongStorageService(new Credential(ACCESS_KEY, SECRET_KEY), config);
//			LOG.info("创建当前环境jss服务成功" + JSS_CURRENT.getClass());
//		} catch (Exception e) {
//			LOG.error("初始化创建jss服务失败", e);
//		}
//
//	}
//
//	public static String getBucketName(String path) {
//		String bucketName;
//		if (!CheckUtils.checkEmpty(path)) {
//			if (path.contains(PRIVATE_BUCKET_NAME)) {
//				bucketName = PRIVATE_BUCKET_NAME;
//			} else {
//				bucketName = BUCKET_NAME;
//			}
//		} else {
//			bucketName = BUCKET_NAME;
//		}
//		return bucketName;
//	}
//
//}
