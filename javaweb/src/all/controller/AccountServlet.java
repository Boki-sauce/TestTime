package all.controller;

import all.dto.DormitoryAdminDto;
import all.dto.StudentAdminDto;
import all.service.DormitoryAdminService;
import all.service.StudentAdminService;
import all.service.impl.DormitoryAdminServiceImpl;
import all.service.impl.StudentAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private StudentAdminService studentAdminService = new StudentAdminServiceImpl();
    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch(method){
            case "login":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String type = req.getParameter("type");
                switch(type){
                    case "dormitoryAdmin":
                        DormitoryAdminDto dormitoryAdminDto = this.dormitoryAdminService.login(username, password);
                        switch (dormitoryAdminDto.getCode()){
                            case -1:
                                req.setAttribute("usernameError","用户名不存在");
                                req.getRequestDispatcher("login.jsp").forward(req,resp);
                                break;
                            case -2:
                                req.setAttribute("passwordError","密码错误");
                                req.getRequestDispatcher("login.jsp").forward(req,resp);
                                break;
                            case 0:
                                //todo  准备跳转到登录成功页面
                                HttpSession session = req.getSession();
                                session.setAttribute("dormitoryAdmin",dormitoryAdminDto.getDormitoryAdmin());
                                resp.sendRedirect("systemadmin.jsp");
                                break;
                        }
                        break;
                    case "studentAdmin":
                        StudentAdminDto studentAdminDto = this.studentAdminService.login(username, password);
                        switch (studentAdminDto.getCode()){
                            case -1:
                                req.setAttribute("usernameError","用户名不存在");
                                req.getRequestDispatcher("login.jsp").forward(req,resp);
                                break;
                            case -2:
                                req.setAttribute("passwordError","密码错误");
                                req.getRequestDispatcher("login.jsp").forward(req,resp);
                                break;
                            case 0:
                                //todo  准备跳转到登录成功页面
                                HttpSession session = req.getSession();
                                session.setAttribute("studentAdmin", studentAdminDto.getStudentAdmin());
                                // session.setAttribute("dormitoryAdmin",dormitoryAdminDto.getDormitoryAdmin());
                                resp.sendRedirect("studentadmin.jsp");
                                break;
                        }
                        break;
                }
     //===========================================
                break;
            case "logout":
                req.getSession().invalidate();
                resp.sendRedirect("/login.jsp");
                break;
        }


    }
}
