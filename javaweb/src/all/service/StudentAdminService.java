package all.service;

import all.dto.StudentAdminDto;
import all.entity.StudentAdmin;

import java.util.List;

public interface StudentAdminService {

    public List<StudentAdmin> init(Integer id);
    public void update(StudentAdmin studentAdmin);
    public void upload(Integer id, String fileName);
    public void delete(Integer id);
    public StudentAdminDto login(String username, String password);
}
