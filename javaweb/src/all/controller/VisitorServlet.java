package all.controller;

import all.entity.Visitor;
import all.service.VisitorService;
import all.service.impl.VisitorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/visitor")
public class VisitorServlet extends HttpServlet {

    private VisitorService visitorService = new VisitorServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
                req.setAttribute("list",this.visitorService.list());
                req.getRequestDispatcher("visitormanager.jsp").forward(req,resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",this.visitorService.search(key,value));
                req.getRequestDispatcher("visitormanager.jsp").forward(req,resp);
                break;
            case "save":
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String instruction = req.getParameter("instruction");
                String telephone = req.getParameter("telephone");
                this.visitorService.save(new Visitor(name,gender,instruction,telephone));
                resp.sendRedirect("/visitor?method=list");
                break;
            case "delete":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                this.visitorService.delete(id);
                resp.sendRedirect("/visitor?method=list");
                break;
            case "init":
                req.setAttribute("list",this.visitorService.init());
                req.getRequestDispatcher("visitorquery.jsp").forward(req,resp);
                break;
            case "initsearch":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("list",this.visitorService.initsearch(key,value));
                req.getRequestDispatcher("visitorquery.jsp").forward(req,resp);
                break;
        }
    }
}
