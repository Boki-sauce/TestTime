package all.service.impl;

import all.dao.VisitorDao;
import all.dao.impl.VisitorDaoImpl;
import all.entity.Visitor;
import all.service.VisitorService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VisitorServiceImpl implements VisitorService {

    private VisitorDao visitorDao = new VisitorDaoImpl();
    @Override
    public List<Visitor> list() {
        return this.visitorDao.list();
    }

    @Override
    public List<Visitor> search(String key, String value) {
        if (value.equals("")) return this.visitorDao.list();
        return this.visitorDao.search(key,value);
    }

    @Override
    public void save(Visitor visitor) {
        visitor.setState("未离开");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        visitor.setCreateDate(simpleDateFormat.format(date));
        Integer save = this.visitorDao.save(visitor);
        if(save != 1) throw new RuntimeException("添加来访人员信息失败");
    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.visitorDao.delete(id);
        if(delete != 1 )throw new RuntimeException("删除学来访人员信息失败");
    }
    public List<Visitor> init() {
        return this.visitorDao.init();
    }

    public List<Visitor> initsearch(String key, String value) {
        if (value.equals("")) return this.visitorDao.init();
        return this.visitorDao.initsearch(key,value);
    }
}
