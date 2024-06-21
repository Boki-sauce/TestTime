package all.dao;

import all.entity.Lost;

import java.util.List;

public interface LostDao {
    public List<Lost> list();
    public List<Lost> search(String key,String value);
    public Integer save(Lost lost);
    public Integer delete(Integer id);
}
