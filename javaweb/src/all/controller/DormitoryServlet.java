package all.controller;

import all.entity.Dormitory;
import all.entity.Student;
import all.service.BuildingService;
import all.service.DormitoryService;
import all.service.StudentService;
import all.service.impl.BuildingServiceImpl;
import all.service.impl.DormitoryServiceImpl;
import all.service.impl.StudentServiceImpl;
import net.sf.json.JSONArray;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/dormitory")
public class DormitoryServlet extends HttpServlet {

    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private BuildingService buildingService = new BuildingServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
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
                req.setAttribute("list",this.dormitoryService.list());
                req.setAttribute("buildingList",this.buildingService.list());
                req.getRequestDispatcher("dormitorymanager.jsp").forward(req,resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",this.dormitoryService.search(key,value));
                req.setAttribute("buildingList",this.buildingService.list());
                req.getRequestDispatcher("dormitorymanager.jsp").forward(req,resp);
                break;
            case "save":
                String buildingIdStr = req.getParameter("buildingId");
                Integer buildingId = Integer.parseInt(buildingIdStr);
                String name = req.getParameter("name");
                String typeStr = req.getParameter("type");
                Integer type = Integer.parseInt(typeStr);
                String telephone = req.getParameter("telephone");
                this.dormitoryService.save(new Dormitory(buildingId,name,type,type,telephone));
                resp.sendRedirect("/dormitory?method=list");
                break;
            case "update":
                String IdStr = req.getParameter("id");
                Integer id = Integer.parseInt(IdStr);
                name = req.getParameter("name");
                telephone = req.getParameter("telephone");
                this.dormitoryService.update(new Dormitory(id,name,telephone));
                resp.sendRedirect("/dormitory?method=list");
                break;
            case "delete":
                IdStr = req.getParameter("id");
                id = Integer.parseInt(IdStr);
                this.dormitoryService.delete(id);
                resp.sendRedirect("/dormitory?method=list");
                break;
            case "findByBuildingId":
                buildingIdStr = req.getParameter("buildingId");
                buildingId = Integer.parseInt(buildingIdStr);
                List<Dormitory> dormitoryList = this.dormitoryService.findByBuildingId(buildingId);
                List<Student> studentList = this.studentService.findByDormitoryId(dormitoryList.get(0).getId());
                Map<String,List> map = new HashMap<>();
                map.put("dormitoryList",dormitoryList);
                map.put("studentList",studentList);
                JSONArray jsonArray = JSONArray.fromObject(map);
                resp.setContentType("text/json;charset=UTF-8");
                resp.getWriter().write(jsonArray.toString());
                break;
            case "init":
                req.setAttribute("list",this.dormitoryService.list());
                req.getRequestDispatcher("dormitoryquery.jsp").forward(req,resp);
                break;
            case "studentsearch":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("list",this.dormitoryService.search(key,value));
                req.setAttribute("buildingList",this.buildingService.list());
                req.getRequestDispatcher("dormitoryquery.jsp").forward(req,resp);
                break;
        }
    }
}
