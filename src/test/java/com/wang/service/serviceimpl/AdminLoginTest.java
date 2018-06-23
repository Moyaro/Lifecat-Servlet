package com.wang.service.serviceimpl;

import com.wang.service.Service;
import com.wang.service.ServiceMaker;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertNotNull;

/**
 * AdminLoginServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 16, 2018</pre>
 */
public class AdminLoginTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void before() throws Exception { // ����request��response��Mock
        request = EasyMock.createMock(HttpServletRequest.class);
        response = EasyMock.createMock(HttpServletResponse.class);
        session = EasyMock.createMock(HttpSession.class);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: newService()
     */
    @Test
    public void testNewService() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: execute(HttpServletRequest req, HttpServletResponse resp)
     */
    @Test
    public void testExecute() throws Exception {
        Service service = ServiceMaker.newService();

        EasyMock.expect(request.getParameter("rUserName")).andReturn("UserRegisterTest").once();    //����ʹ�ò���
        EasyMock.expect(request.getParameter("rUserPassword1")).andReturn("testtest").times(1);  //�������õĴ���

        EasyMock.replay(request);   //�����������

        ServiceResult result = service.execute(request, response);

        System.out.println("page " + result.getPage());
        System.out.println("msg" + result.getErrormsg());

        assertNotNull(result.getPage());
        assertNotNull(result.getErrormsg());
    }


} 
