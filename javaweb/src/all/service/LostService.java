package all.service;

import all.entity.Lost;

import java.util.List;

public interface LostService {
    public List<Lost> list();
    public List<Lost> search(String key,String value);
    public void save(Lost lost);
    public void delete(Integer id);
}
