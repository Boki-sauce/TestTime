package all.dao;

import all.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminDao {
    public List<DormitoryAdmin> init(Integer id);
    public Integer upload(Integer id,String fileName);
    public List<DormitoryAdmin> list();
    public List<DormitoryAdmin> search(String key,String value);
    public Integer save(DormitoryAdmin dormitoryAdmin);
    public Integer update(DormitoryAdmin dormitoryAdmin);
    public Integer delete(Integer id);
    public DormitoryAdmin findByUsername(String username);
}
