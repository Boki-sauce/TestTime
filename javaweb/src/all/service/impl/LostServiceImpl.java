package all.service.impl;

import all.dao.LostDao;
import all.dao.impl.LostDaoImpl;
import all.entity.Lost;
import all.service.LostService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LostServiceImpl implements LostService {
    private LostDao lostDao = new LostDaoImpl();
    @Override
    public List<Lost> list() {
        return this.lostDao.list();
    }

    @Override
    public List<Lost> search(String key, String value) {
        if(value.equals(""))return this.lostDao.list();
        return this.lostDao.search(key, value);
    }

    @Override
    public void save(Lost lost) {
        Date date =new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lost.setCreateDate(simpleDateFormat.format(date));
        Integer save =this.lostDao.save(lost);
        if(save != 1) throw new RuntimeException("添加物品信息失败");
    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.lostDao.delete(id);
        if (delete != 1) throw new RuntimeException("移除物品信息失败");
    }
}
