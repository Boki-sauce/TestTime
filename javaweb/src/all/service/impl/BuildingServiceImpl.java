package all.service.impl;

import all.dao.BuildingDao;
import all.dao.DormitoryDao;
import all.dao.StudentDao;
import all.dao.impl.BuildingDaoImpl;
import all.dao.impl.DormitoryDaoImpl;
import all.dao.impl.StudentDaoImpl;
import all.entity.Building;
import all.service.BuildingService;

import java.util.List;

public class BuildingServiceImpl implements BuildingService {

    private BuildingDao buildingDao = new BuildingDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Building> init() {
        return this.buildingDao.init();
    }

    @Override
    public List<Building> list() {
        return this.buildingDao.list();
    }

    @Override
    public List<Building> search(String key, String value) {
        if(value.equals("")) return this.buildingDao.list();
        return this.buildingDao.search(key, value);
    }

    @Override
    public void save(Building building) {
        Integer save = this.buildingDao.save(building);
        if(save != 1)throw new RuntimeException("添加楼宇信息失败");
    }

    @Override
    public void update(Building building) {
        Integer update = this.buildingDao.upadate(building);
        if(update != 1)throw new RuntimeException("更新楼宇信息失败");
    }

    @Override
    public void delete(Integer id) {
        //  todo  1、先给学生换宿舍
        //   先通过楼宇id找到宿舍id，在通过宿舍id找到学生id
        //   id为楼宇id （需要拆除的楼）     dormitoryId为宿舍id （需要删除拆除的宿舍）      studentId为学生id （需要搬宿舍的学生）    availableId为有空闲床位的宿舍的id
        List<Integer> dormitoryIdList = this.dormitoryDao.findDormitoryIdByBuildingId(id);
        for (Integer dormitoryId : dormitoryIdList) {
            List<Integer> studentIdList = this.studentDao.findStudentIdByDormitoryId(dormitoryId);
            for (Integer studentId : studentIdList) {
                Integer availableId = this.dormitoryDao.availableId();
                Integer updateDormitory = this.studentDao.updateDormitory(studentId, availableId);
                Integer subAvaliable = this.dormitoryDao.subAvaliable(availableId);
                if(updateDormitory != 1 || subAvaliable != 1) throw new RuntimeException("学生更换宿舍失败");
            }
        }

        //           2、删除宿舍
        for (Integer dormitoryId : dormitoryIdList) {
            Integer delete = this.dormitoryDao.deleteById(dormitoryId);
            if(delete != 1) throw new RuntimeException("宿舍信息删除失败");
        }
           //             3、删除楼宇
        Integer delete = this.buildingDao.delete(id);
        if(delete != 1)throw new RuntimeException("楼宇信息删除失败");
    }
}
