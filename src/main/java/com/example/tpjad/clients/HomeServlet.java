package com.example.tpjad.clients;
import com.example.beans.BeanJSF;
import com.example.interfaces.Facade;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.*;
import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
@WebServlet(name ="Login", urlPatterns = {"/Login"})
public class HomeServlet extends HttpServlet {
    @EJB
    private Facade facade;
    private BeanJSF bean;
    RequestDispatcher rdLogin;
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        //bean = (BeanJSF) req.getSession().getAttribute("bean");
        //if (bean == null) return;
        List<String> pars = Collections.list(req.getParameterNames());
        for (int i = 0; i < pars.size(); i++) {
            String p = pars.get(i);
            int k = p.indexOf("_");
            if (k > 0) {
                bean.setId(Long.parseLong(p.substring(k + 1)));
                pars.set(i, p.substring(0, k));
            }
        }
        rdLogin = req.getRequestDispatcher("/login.xhtml");
        rdLogin.forward(req, res);
    }
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
