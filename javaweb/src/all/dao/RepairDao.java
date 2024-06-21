package all.dao;

import all.entity.Repair;

import java.util.List;

public interface RepairDao {
    public List<Repair> list();
    public List<Repair> search(String key, String value);
    public Integer save(Repair repair);
    public Integer update(Repair repair);
    public Integer delete(Integer id);
}
