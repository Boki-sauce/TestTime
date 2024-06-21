package all.service;

import all.dto.DormitoryAdminDto;
import all.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminService {
    public List<DormitoryAdmin> init(Integer id);
    public void upload(Integer id,String fileName);
    public List<DormitoryAdmin> list();
    public List<DormitoryAdmin> search(String key,String value);

    public void save(DormitoryAdmin dormitoryAdmin);
    public void update(DormitoryAdmin dormitoryAdmin);
    public void delete(Integer id);
    public DormitoryAdminDto login(String username, String password);
}
