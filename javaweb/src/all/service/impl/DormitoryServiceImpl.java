package all.service.impl;

import all.dao.DormitoryDao;
import all.dao.StudentDao;
import all.dao.impl.DormitoryDaoImpl;
import all.dao.impl.StudentDaoImpl;
import all.entity.Dormitory;
import all.service.DormitoryService;

import java.util.List;

public class DormitoryServiceImpl implements DormitoryService {

    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Dormitory> init() {
        return this.dormitoryDao.init();
    }

    @Override
    public List<Dormitory> availableList() {
        return this.dormitoryDao.availableList();
    }

    @Override
    public List<Dormitory> list() {
        return this.dormitoryDao.list();
    }

    @Override
    public List<Dormitory> search(String key, String value) {
        if(value.equals(""))return this.dormitoryDao.list();
        return this.dormitoryDao.search(key, value);
    }

    @Override
    public void save(Dormitory dormitory) {
        Integer save = this.dormitoryDao.save(dormitory);
        if (save != 1) throw new RuntimeException("添加宿舍信息失败");
    }

    @Override
    public void update(Dormitory dormitory) {
        Integer update = this.dormitoryDao.update(dormitory);
        if (update != 1)throw new RuntimeException("更新宿舍信息失败");
    }

    @Override
    public void delete(Integer id) {
        // TODO: 2023/8/6  先给学生换宿舍   再删除宿舍
        List<Integer> studentIdList = this.studentDao.findStudentIdByDormitoryId(id);
        for (Integer studentId : studentIdList) {
            Integer availableId = this.dormitoryDao.availableId();
            Integer updateDormitory = this.studentDao.updateDormitory(studentId, availableId);
            Integer subAvaliable = this.dormitoryDao.subAvaliable(availableId);
            if(updateDormitory != 1 || subAvaliable != 1) throw new RuntimeException("学生更换宿舍失败");
        }
        Integer delete = this.dormitoryDao.deleteById(id);
        if (delete != 1)throw new RuntimeException("删除宿舍信息失败");
    }

    @Override
    public List<Dormitory> findByBuildingId(Integer buildingId) {
        return this.dormitoryDao.findByBuildingId(buildingId);

    }
}
