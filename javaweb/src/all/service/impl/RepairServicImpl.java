package all.service.impl;

import all.dao.RepairDao;
import all.dao.impl.RepairDaoImpl;
import all.entity.Repair;
import all.service.RepairService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RepairServicImpl implements RepairService {
    private RepairDao repairDao = new RepairDaoImpl();
    @Override
    public List<Repair> list() {
        return this.repairDao.list();
    }

    @Override
    public List<Repair> search(String key, String value) {
        if(value.equals("")) return this.repairDao.list();
        return this.repairDao.search(key, value);
    }

    @Override
    public void save(Repair repair) {
        repair.setState("未处理");
        Date date =new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        repair.setCreateDate(simpleDateFormat.format(date));
        Integer save =this.repairDao.save(repair);
        if(save != 1) throw new RuntimeException("添加维修信息失败");
    }

    @Override
    public void update(Repair repair) {
        Integer update = this.repairDao.update(repair);
        if(update != 1 )throw new RuntimeException("更新维修信息失败");

    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.repairDao.delete(id);
        if (delete != 1)throw new RuntimeException("删除维修信息失败");
    }
}
