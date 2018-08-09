package com.ten.controller;

import com.meterware.httpunit.WebResponse;
import org.apache.cactus.ServletTestCase;
import org.apache.cactus.WebRequest;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * ActionServlet Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2018</pre>
 */
public class ActionServletTest extends ServletTestCase {

    private ActionServlet actionServlet;
   /*
     * ��begin��ʼ�ķ���,������Ϊ�ͻ��˷���,����Ĳ��� ��Ӧ���Ǵ�
     * �ͻ��˷�����,����ͻ��˴��ݸ�����˵Ĳ�����Ӧ�ô�begin����
     * �д���,����WebRequestΪ����request����Ҫ
     */
    public void beginLogin(WebRequest request) {
        // ͨ��request.addParameter()�����parameter
        request.addParameter("username", "Ray");
    }

    /*
     * ��ʼ������
     */
    public void setUp() {
        actionServlet = new ActionServlet();
    }

    /*
     * ����doGet����
     */
    public void testDoGet() throws ServletException, IOException {
        // ע��,��ʱrequest��responseΪServletTestCase������
        actionServlet.doGet(super.request, super.response);
    }

    /*
     * ��end��ʼ�ķ���,������Ϊ��������Ӧ����,�������
     * ��Ӧ������֤�������������Ľ��,���緵�ص�JSP�Ƿ���ȷ
     * ,����WebResponseΪ��֤�ṩ�˴����ķ���,����getTable
     */
    public void endDoGet(WebResponse response) throws SAXException {
        //�����ѱ�����htmlҳ���title
        assertEquals("test", response.getTitle());
        //��������html��document
        assertEquals("test", response.getDOM().getElementById("Ray").getNodeValue());
        //����ָ�����Ƶ�Ԫ��
        assertEquals(3, response.getElementsByTagName("input").length);
    }
}


