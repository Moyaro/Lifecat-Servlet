package com.wang.filter;

import com.wang.form.Form;
import com.wang.form.FormResult;
import com.wang.form.FormSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter - Form
 * <p>
 * 对(*.do)进行表单验证
 * 若不存在验证表单 则跳过
 *
 * @auther ten
 */
public class FormFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(FormFilter.class);

    /**
     * Form表单验证
     * <p>
     * 1. 获取 .do 请求url
     * 2. 获取对应form对象
     * 3. 执行validate(),获取返回结果
     * 4. 分析返回结果
     * 5. 若验证通过,执行转发到请求处理层
     *
     * @throws IOException          IO异常
     * @throws ServletException     servlet异常
     * @throws NullPointerException 过滤器链异常
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 检查NPE
        if (chain == null) {
            throw new NullPointerException("FormFilter is null");
        }

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 1. 提取url请求信息
        String path = request.getRequestURI();
        String url = path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));

        logger.info("form filter request url: {}", url);

        // 2. 获取对应form对象
        Form form = FormSelector.select(url);

        if (form != null) {
            // 3. 执行validate(),获取返回结果
            FormResult result = form.validate(request);

            // 5. 验证通过
            if (result.isSuccess()) {
                chain.doFilter(req, resp);
            }

            // 验证未通过
            logger.warn("form filter failure");

            request.setAttribute("formresult", result);
            request.getRequestDispatcher(String.valueOf(request.getRequestURL())).forward(req, resp);
        }

        // if form == null : do nothing and break
    }

    /**
     * com.wang.filter.init()
     *
     * @throws ServletException com.wang.filter init failure
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        logger.config("FormFilter init()");
    }

    /**
     * com.wang.filter.destroy()
     */
    @Override
    public void destroy() {
        logger.config("FormFilter destroy()");
    }
}