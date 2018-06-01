package com.xyibq.lanxj.admin.forum.web.filter;

import com.xyibq.lanxj.admin.forum.domain.vo.UserRoleMenuVo;
import com.xyibq.lanxj.admin.forum.service.RoleMenuService;
import com.xyibq.lanxj.admin.forum.service.impl.RoleMenuServiceImpl;
import com.xyibq.lanxj.admin.forum.web.controller.user.UserTopicAuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

@Component
public class LoginInterceptorAdapter extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptorAdapter.class);

    @Resource
    RoleMenuService roleMenuService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        logger.info("LoginInterceptorAdapter ____ requst server name:" + request.getServerName());
        logger.info("LoginInterceptorAdapter ____ request client: " + this.getIpAddress(request));
        logger.info("LoginInterceptorAdapter ____ request url:" + request.getRequestURL());
        logger.info("LoginInterceptorAdapter ____ request params:" + request.getQueryString());
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
       /* if(userId==null){
            logger.info("------:跳转到无权限页面！");
            response.sendRedirect(request.getContextPath()+"/postInfo/");
            return true;
        }*/

       /*  查询当前登陆用户 角色*/
        String roleId = roleMenuService.queryUserRole("1001");

        //普通用户权限默认为0
        if(roleId==null || roleId!=null && "0".equals(roleId)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.print("您暂未开通此系统登录权限");
            pw.flush();
            pw.close();
            return false;
        }

        logger.info("登陆用户");

        return true;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，所以取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
