package all.service;

import all.entity.Repair;

import java.util.List;

public interface RepairService {
    public List<Repair> list();
    public List<Repair> search(String key,String value);
    public void save(Repair repair);
    public void update(Repair repair);
    public void delete(Integer id);
}
