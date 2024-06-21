package all.controller;

import all.entity.Dormitory;
import all.entity.DormitoryAdmin;
import all.entity.Repair;
import all.entity.Student;
import all.service.DormitoryAdminService;
import all.service.DormitoryService;
import all.service.RepairService;
import all.service.StudentService;
import all.service.impl.DormitoryAdminServiceImpl;
import all.service.impl.DormitoryServiceImpl;
import all.service.impl.RepairServicImpl;
import all.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/repair")
public class RepairServlet extends HttpServlet {

    private RepairService repairService = new RepairServicImpl();
    private DormitoryService dormitoryService =new DormitoryServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "init":
                List<Dormitory> dormitoryList = this.dormitoryService.init();
                List<Student> studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("dormitoryList",dormitoryList);
                req.setAttribute("studentList",studentList);
                req.setAttribute("list",this.repairService.list());
                req.getRequestDispatcher("repairregister.jsp").forward(req,resp);
                break;
            case "save":
                HttpSession session = req.getSession();
                DormitoryAdmin dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                String dormitoryIdStr = req.getParameter(("dormitoryId"));
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                Integer dormitoryAdminId = dormitoryAdmin.getId();
                String instruction = req.getParameter("instruction");
                this.repairService.save(new Repair(dormitoryId,studentId,dormitoryAdminId,instruction));
                resp.sendRedirect("/repair?method=init");
                break;
            case "list":
                dormitoryList = this.dormitoryService.init();
                req.setAttribute("dormitoryList",dormitoryList);
                req.setAttribute("list",this.repairService.list());
                req.getRequestDispatcher("repairmanager.jsp").forward(req,resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",this.repairService.search(key,value));
                req.getRequestDispatcher("repairmanager.jsp").forward(req,resp);
                break;
            case "update":
                session = req.getSession();
                dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                dormitoryAdminId = dormitoryAdmin.getId();
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                dormitoryIdStr = req.getParameter("dormitoryId");
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                studentIdStr = req.getParameter("studentId");
                /*System.out.println(studentIdStr);*/
                studentId = Integer.parseInt(studentIdStr);
                String dormitoryName = req.getParameter("dormitoryName");
                String studentNumber = req.getParameter("studentNumber");
                String studentName = req.getParameter("studentName");
                instruction = req.getParameter("instruction");
                String createDate = req.getParameter("createDate");
                String state = req.getParameter("state");
                this.repairService.update(new Repair(id,dormitoryId,dormitoryName,studentNumber,studentName,state,instruction,createDate,studentId,dormitoryAdminId));
                resp.sendRedirect("/repair?method=list");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                this.repairService.delete(id);
                resp.sendRedirect("/repair?method=list");
                break;
            case "studentlist":
                req.setAttribute("dormitoryList",this.dormitoryService.init());
                req.setAttribute("list",this.repairService.list());
                req.getRequestDispatcher("strepairmanager.jsp").forward(req,resp);
                break;
            case "studentsearch":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("list",this.repairService.search(key,value));
                req.getRequestDispatcher("strepairmanager.jsp").forward(req,resp);
                break;
            case "studentinit":
                dormitoryList = this.dormitoryService.init();
                req.setAttribute("dormitoryAdminList", this.dormitoryAdminService.list());
                studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("dormitoryList",dormitoryList);
                req.setAttribute("studentList",studentList);
                req.setAttribute("list",this.repairService.list());
                req.getRequestDispatcher("strepairregister.jsp").forward(req,resp);
                break;
            case "studentsave":
                req.setAttribute("dormitoryAdminList", this.dormitoryAdminService.list());
                dormitoryIdStr = req.getParameter(("dormitoryId"));
                dormitoryId = Integer.parseInt(dormitoryIdStr);
                studentIdStr = req.getParameter("studentId");
                studentId = Integer.parseInt(studentIdStr);
                instruction = req.getParameter("instruction");
                String dormitoryAdminIdStr = req.getParameter("dormitoryAdminId");
                dormitoryAdminId = Integer.parseInt(dormitoryAdminIdStr);
                this.repairService.save(new Repair(dormitoryId,studentId,dormitoryAdminId,instruction));
                resp.sendRedirect("/repair?method=studentinit");
                break;
        }
    }
}
