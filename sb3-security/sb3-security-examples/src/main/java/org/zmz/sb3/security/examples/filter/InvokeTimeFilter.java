package org.zmz.sb3.security.examples.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.zmz.sb3.security.examples.filter.anno.InvokeTimeCalculate;

public class InvokeTimeFilter extends OncePerRequestFilter {

    public static final Logger LOG = LoggerFactory.getLogger(InvokeTimeFilter.class);

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) {

        try {
            HandlerExecutionChain handler = requestMappingHandlerMapping.getHandler(request);
            if (handler.getHandler() instanceof HandlerMethod handlerMethod) {
                InvokeTimeCalculate invokeTimeCalculate = handlerMethod.getMethodAnnotation(InvokeTimeCalculate.class);
                if (invokeTimeCalculate != null) {
                    long start = System.currentTimeMillis();
                    filterChain.doFilter(request, response);
                    long end = System.currentTimeMillis();
                    String requestURI = request.getRequestURI();
                    LOG.info("调用 {} , 耗时: {} 毫秒", requestURI, (end - start));
                    return;
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            LOG.error("InvokeTimeFilter # 过滤器异常", e);
            throw new RuntimeException(e);
        }
    }

    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return requestMappingHandlerMapping;
    }

    public void setRequestMappingHandlerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }
}
