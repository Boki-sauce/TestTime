package all.service.impl;

import all.dao.DormitoryDao;
import all.dao.StudentDao;
import all.dao.impl.DormitoryDaoImpl;
import all.dao.impl.StudentDaoImpl;
import all.entity.Student;
import all.service.StudentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();



    @Override
    public List<Student> list() {
        return this.studentDao.list();
    }

    @Override
    public List<Student> search(String key, String value) {
        if(value.equals("")) return this.studentDao.list();
        return this.studentDao.search(key, value);
    }

    @Override
    public void save(Student student) {
        student.setState("入住");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        student.setCreateDate(simpleDateFormat.format(date));
        Integer save = this.studentDao.save(student);
        Integer sub = this.dormitoryDao.subAvaliable((student.getDormitoryId()));
        if(save != 1|| sub != 1) throw new RuntimeException("添加学生信息失败");
    }

    @Override
    public Integer getIdByNumber(String number) {
        Integer id = this.studentDao.getIdByNumber(number);
        return id;
    }

    @Override
    public void saveAdmin(Student student) {
        this.studentDao.saveAdmin(student);
    }

    @Override
    public void update(Student student,Integer oldDormitoryId) {
        Integer update = this.studentDao.update(student);
        //todo 更新宿舍床位
        //原宿舍available-1,新宿舍available+1
        Integer dormitory1 = this.dormitoryDao.addAvaliable(oldDormitoryId);
        Integer dormitory2 = this.dormitoryDao.subAvaliable(student.getDormitoryId());

        if(update != 1 || dormitory1 != 1 || dormitory2 != 1)throw new RuntimeException("更新学生信息失败");
    }

    @Override
    public void delete(Integer id, Integer dormitoryId) {
        Integer delete = this.studentDao.delete(id);
        Integer avaliable = this.dormitoryDao.addAvaliable(dormitoryId);
        if(delete != 1 || avaliable != 1)throw new RuntimeException("删除学生信息失败");
    }

    @Override
    public List<Student> moveoutList() {
        return this.studentDao.moveoutList();
    }

    @Override
    public List<Student> searchForMoveout(String key, String value) {
        if(value.equals("")) return this.studentDao.moveoutList();
        return this.studentDao.searchForMoveout(key, value);
    }

    @Override
    public List<Student> findByDormitoryId(Integer dormitoryId) {
        return this.studentDao.findByDormitoryId(dormitoryId);
    }
}
