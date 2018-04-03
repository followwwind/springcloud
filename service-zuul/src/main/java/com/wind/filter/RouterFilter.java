package com.wind.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤器
 * zuul还提供了一类特殊的过滤器，分别为：StaticResponseFilter和SurgicalDebugFilter
 * StaticResponseFilter：StaticResponseFilter允许从Zuul本身生成响应，而不是将请求转发到源。
 * SurgicalDebugFilter：SurgicalDebugFilter允许将特定请求路由到分隔的调试集群或主机。
 * @author wind
 */
@Component
public class RouterFilter  extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(RouterFilter.class);

    /**
     * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"token is empty!\"}");
            //上下文绑定参数
            ctx.set("isSuccess", false);
            return null;
        }else{
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }
    }
}
