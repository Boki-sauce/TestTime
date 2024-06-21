package all.controller;

import all.entity.DormitoryAdmin;
import all.entity.StudentAdmin;
import all.service.DormitoryAdminService;
import all.service.StudentAdminService;
import all.service.impl.DormitoryAdminServiceImpl;
import all.service.impl.StudentAdminServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
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

@WebServlet("/dormitoryAdmin")
public class DormitorAdminServlet extends HttpServlet {

    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();
    private StudentAdminService studentAdminService = new StudentAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
       String method = req.getParameter("method");
        switch (method){
            case "init":
                    HttpSession session = req.getSession();
                    DormitoryAdmin dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                    Integer id = dormitoryAdmin.getId();
              //  req.setAttribute("list",this.dormitoryAdminService.list());
                req.setAttribute("list", this.dormitoryAdminService.init(id));
                req.getRequestDispatcher("adminmanager.jsp").forward(req,resp);
                break;
            case "list":
                req.setAttribute("list", this.dormitoryAdminService.list());
                req.getRequestDispatcher("dormitoryadminmanager.jsp").forward(req,resp);
                break;
            case "search":
                String  key =req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",this.dormitoryAdminService.search(key,value));
                req.getRequestDispatcher("dormitoryadminmanager.jsp").forward(req,resp);
                break;
            case "save":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String telephone = req.getParameter("telephone");
                this.dormitoryAdminService.save(new DormitoryAdmin(username,password,name,gender,telephone));
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
            case "update":
                 String idStr = req.getParameter("id");
                 id = Integer.parseInt(idStr);
                 username = req.getParameter("username");
                 password = req.getParameter("password");
                 name = req.getParameter("name");
                 gender = req.getParameter("gender");
                 telephone = req.getParameter("telephone");
                 this.dormitoryAdminService.update(new DormitoryAdmin(id,username,password,name,gender,telephone));
                 resp.sendRedirect("/dormitoryAdmin?method=list");
                 break;
            case "delete":
                 idStr = req.getParameter("id");
                 id = Integer.parseInt(idStr);
                 this.dormitoryAdminService.delete(id);
                 resp.sendRedirect("/dormitoryAdmin?method=list");
                 break;
            case "updateinit":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                username = req.getParameter("username");
                password = req.getParameter("password");
                name = req.getParameter("name");
                gender = req.getParameter("gender");
                telephone = req.getParameter("telephone");
                this.dormitoryAdminService.update(new DormitoryAdmin(id,username,password,name,gender,telephone));
                resp.sendRedirect("/dormitoryAdmin?method=init");
                break;
            case "upload":
                session = req.getSession();
                dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                id = dormitoryAdmin.getId();
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
                            if (!fi.getName().equals("")){
                                String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
                                fileName = fileName + suffix;
                                fi.write(new File(path,fileName));
                                this.dormitoryAdminService.upload(id,fileName);
                            }
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/dormitoryAdmin?method=init");
                break;
        }

    }
}
