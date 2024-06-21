package all.controller;

import all.entity.DormitoryAdmin;
import all.entity.StudentAdmin;
import all.service.StudentAdminService;
import all.service.impl.StudentAdminServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/studentAdmin")
public class StudentAdminServlet extends HttpServlet {
    private StudentAdminService studentAdminService = new StudentAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch(method){
            case "init":
                HttpSession session = req.getSession();
                StudentAdmin studentAdmin = (StudentAdmin) session.getAttribute("studentAdmin");
                Integer id = studentAdmin.getId();
                //  req.setAttribute("list",this.dormitoryAdminService.list());
                req.setAttribute("list", this.studentAdminService.init(id));
                req.getRequestDispatcher("stadminmanager.jsp").forward(req,resp);
                break;
            case "update":
                String idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String telephone = req.getParameter("telephone");
                this.studentAdminService.update(new StudentAdmin(id,username,password,name,gender,telephone));
                resp.sendRedirect("/studentAdmin?method=init");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                this.studentAdminService.delete(id);
                resp.sendRedirect("/studentAdmin?method=init");
                break;
            case "upload":
                session = req.getSession();
                studentAdmin = (StudentAdmin) session.getAttribute("studentAdmin");
                id = studentAdmin.getId();
                //  初始化FileUpload
                FileItemFactory factory = new DiskFileItemFactory();
                /*
                * FileItemFactory将前端表单数据转换为FileItem对象
                * ServletFileUpload 为FileUpload组件提供 java web的http请求解析
                */
                ServletFileUpload sf = new ServletFileUpload(factory);
                //遍历所有FileItem
                try {
                    List<FileItem> formData = sf.parseRequest(req);
                    for(FileItem fi:formData){
                        if(fi.isFormField()){
                        }else{
                            //文件保存到服务器目录
                            /*String path = req.getServletContext().getRealPath("/img");*/
                            String path ="D:\\idea\\data\\javaweb\\web\\upload";
                            String fileName = UUID.randomUUID().toString();
                            //file.getName()得到原始文件名，截取最后一个.后所有字符串，例 test.jpg——>.jpg
                            if(!fi.getName().equals("")){
                                String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
                                fileName = fileName + suffix;
                                fi.write(new File(path,fileName));
                                this.studentAdminService.upload(id,fileName);
                            }
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/studentAdmin?method=init");
                break;
        }
    }
}
