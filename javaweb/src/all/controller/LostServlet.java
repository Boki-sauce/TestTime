package all.controller;

import all.entity.Dormitory;
import all.entity.Lost;
import all.entity.Student;
import all.service.DormitoryService;
import all.service.LostService;
import all.service.StudentService;
import all.service.impl.DormitoryServiceImpl;
import all.service.impl.LostServiceImpl;
import all.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lost")
public class LostServlet extends HttpServlet {

    private LostService lostService = new LostServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
                List<Dormitory> dormitoryList = this.dormitoryService.init();
                req.setAttribute("dormitoryList",dormitoryList);
                List<Student> studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("dormitoryList",dormitoryList);
                req.setAttribute("studentList",studentList);
                req.setAttribute("list",this.lostService.list());
                req.getRequestDispatcher("lostmanager.jsp").forward(req,resp);
                break;
            case "search":
                dormitoryList = this.dormitoryService.init();
                req.setAttribute("dormitoryList",dormitoryList);
                studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("dormitoryList",dormitoryList);
                req.setAttribute("studentList",studentList);
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",this.lostService.search(key,value));
                req.getRequestDispatcher("lostmanager.jsp").forward(req,resp);
                break;
            case "save":
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String instruction = req.getParameter("instruction");
                String telephone = req.getParameter("telephone");
                this.lostService.save(new Lost(studentId,dormitoryId,instruction,telephone));
                resp.sendRedirect("/lost?method=list");
                break;
            case "delete":
                String IdStr = req.getParameter("id");
                Integer id = Integer.parseInt(IdStr);
                this.lostService.delete(id);
                resp.sendRedirect("/lost?method=list");
                break;
            case "init":
                req.setAttribute("list",this.lostService.list());
                req.getRequestDispatcher("lostquery.jsp").forward(req,resp);
                break;
            case "record":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("list",this.lostService.search(key,value));
                req.getRequestDispatcher("lostquery.jsp").forward(req,resp);
                break;
            case "start":
                dormitoryList = this.dormitoryService.init();
                req.setAttribute("dormitoryList",dormitoryList);
                studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("dormitoryList",dormitoryList);
                req.setAttribute("studentList",studentList);
                req.setAttribute("list",this.lostService.list());
                req.getRequestDispatcher("lostregister.jsp").forward(req,resp);
                break;
            case "register":
                dormitoryIdStr = req.getParameter("dormitoryId");
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                studentIdStr = req.getParameter("studentId");
                studentId = Integer.parseInt(studentIdStr);
                instruction = req.getParameter("instruction");
                telephone = req.getParameter("telephone");
                this.lostService.save(new Lost(studentId,dormitoryId,instruction,telephone));
                resp.sendRedirect("/lost?method=start");
                break;

        }
    }
}
