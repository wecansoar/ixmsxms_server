package net.flower.ixmsxms_server.utils;

import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


/**
 * Created by hyeyoungkang on 2014. 7. 21..
 */
public class RequiredRefererInterceptor extends HandlerInterceptorAdapter {

//    private final String[] defaultReferer;

    protected static final Logger logger = LoggerFactory.getLogger(RequiredRefererInterceptor.class);
//
//    RequiredRefererInterceptor(String[] defaultReferer) {
//        this.defaultReferer = defaultReferer;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws URISyntaxException {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequiredReferer requiredReferer = handlerMethod.getMethod().getAnnotation(RequiredReferer.class);
       System.out.println("violet----");
//
//        if( requiredReferer == null){
//            System.out.println("violet---- null : " + requiredReferer);
//            return true;
//        }
//
//        String referer = request.getHeader("Referer");
//        System.out.println("violet----referer : " + referer);
//
//        if(StringUtils.isEmpty(referer)){
//            response.setStatus(HttpStatus.SC_BAD_REQUEST);
//            return false;
//        }
//        URI uri = new URI(referer);
//        String host = uri.getHost();
//        System.out.println("host:" + host);
//
//        if( host.endsWith( "localhost") ){
//            System.out.println("violet----올래 : " + referer);
//            return true;
//        }
//
        response.setStatus(HttpStatus.SC_BAD_REQUEST);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
