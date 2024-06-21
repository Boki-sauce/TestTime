package all.service.impl;

import all.dao.DormitoryAdminDao;
import all.dao.impl.DormitoryAdminDaoImpl;
import all.dto.DormitoryAdminDto;
import all.entity.DormitoryAdmin;
import all.service.DormitoryAdminService;

import java.util.List;

public class DormitoryAdminServiceImpl implements DormitoryAdminService{

    private DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDaoImpl();

    @Override
    public List<DormitoryAdmin> init(Integer id) {
        return this.dormitoryAdminDao.init(id);
    }

    @Override
    public void upload(Integer id, String fileName) {
        Integer upload = this.dormitoryAdminDao.upload(id, fileName);
        if (upload != 1)throw new RuntimeException("头像更换失败");
    }

    @Override
    public List<DormitoryAdmin> search(String key, String value) {
        //if(value.equals("")) return this.dormitoryAdminDao.list(userName);
        return this.dormitoryAdminDao.search(key, value);
    }

    @Override
    public List<DormitoryAdmin> list() {
        return this.dormitoryAdminDao.list();
    }

    @Override
    public void save(DormitoryAdmin dormitoryAdmin) {
        Integer save = this.dormitoryAdminDao.save(dormitoryAdmin);
        if(save != 1) throw new RuntimeException("宿管信息添加失败");
    }

    @Override
    public void update(DormitoryAdmin dormitoryAdmin) {
        Integer update = this.dormitoryAdminDao.update(dormitoryAdmin);
        if(update != 1) throw new RuntimeException("宿管个人信息更新失败");
    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.dormitoryAdminDao.delete(id);
        if(delete != 1) throw new RuntimeException("宿管个人信息删除失败");
    }

    @Override
    public DormitoryAdminDto login(String username, String password) {
        // 1、先用username查数据库，结果为空用户名不存在，不为空判断password
        //2、password错误，密码错误，正确可以登录
        DormitoryAdmin dormitoryAdmin = this.dormitoryAdminDao.findByUsername(username);
        DormitoryAdminDto dormitoryAdminDto = new DormitoryAdminDto();
        if(dormitoryAdmin == null){
            dormitoryAdminDto.setCode(-1);
        }else{
            if(!dormitoryAdmin.getPassword().equals(password)){
                dormitoryAdminDto.setCode(-2);
            }else{
                dormitoryAdminDto.setCode(0);
                dormitoryAdminDto.setDormitoryAdmin(dormitoryAdmin);
            }
        }
        return dormitoryAdminDto;
    }
}
