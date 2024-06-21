package all.service.impl;

import all.dao.DormitoryDao;
import all.dao.MoveoutDao;
import all.dao.StudentDao;
import all.dao.impl.DormitoryDaoImpl;
import all.dao.impl.MoveoutDaoImpl;
import all.dao.impl.StudentDaoImpl;
import all.entity.Moveout;
import all.service.MoveoutService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoveoutServiceImpl implements MoveoutService {

    private MoveoutDao moveoutDao = new MoveoutDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    @Override
    public void save(Moveout moveout) {
        Integer updateStateById = this.studentDao.updateStateById(moveout.getStudentId());
        Integer addAvaliable = this.dormitoryDao.addAvaliable(moveout.getDormitoryId());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        moveout.setCreateDate(simpleDateFormat.format(date));
        Integer save = this.moveoutDao.save(moveout);
        if (save != 1 || updateStateById != 1 || addAvaliable != 1)throw new RuntimeException("迁出学生失败");
    }

    @Override
    public List<Moveout> list() {
        return this.moveoutDao.list();
    }

    @Override
    public List<Moveout> search(String key, String value) {
        if(value.equals(""))return this.moveoutDao.list();
        return this.moveoutDao.search(key, value);
    }
}
