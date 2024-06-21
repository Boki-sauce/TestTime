package all.service.impl;

import all.dao.StudentAdminDao;
import all.dao.impl.StudentAdminDaoImpl;
import all.dto.StudentAdminDto;
import all.entity.StudentAdmin;
import all.service.StudentAdminService;

import java.util.List;

public class StudentAdminServiceImpl implements StudentAdminService {

    private StudentAdminDao studentAdminDao = new StudentAdminDaoImpl();

    @Override
    public List<StudentAdmin> init(Integer id) {
        return this.studentAdminDao.init(id);
    }


    @Override
    public void update(StudentAdmin studentAdmin) {
        Integer update = this.studentAdminDao.update(studentAdmin);
        if(update != 1) throw new RuntimeException("学生个人信息更新失败");
    }

    @Override
    public void upload(Integer id,String fileName) {

        Integer upload = this.studentAdminDao.upload(id, fileName);
        if (upload != 1)throw  new RuntimeException("头像更换失败");
    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.studentAdminDao.delete(id);
        if(delete != 1) throw new RuntimeException("学生个人信息删除失败");
    }

    @Override
    public StudentAdminDto login(String username, String password) {
        // TODO: 2023/8/4 用户登录判断 
        // 1、先用username查数据库，结果为空用户名不存在，不为空判断password
        //2、password错误，密码错误，正确可以登录
        StudentAdmin studentAdmin = this.studentAdminDao.findByUsername(username);
        StudentAdminDto studentAdminDto = new StudentAdminDto();
        if(studentAdmin == null){
            studentAdminDto.setCode(-1);
        }else{
            if(!studentAdmin.getPassword().equals(password)){
                studentAdminDto.setCode(-2);
            }else{
                studentAdminDto.setCode(0);
                studentAdminDto.setStudentAdmin(studentAdmin);
            }
        }
        return studentAdminDto;
    }
}
