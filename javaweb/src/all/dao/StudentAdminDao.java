package all.dao;

import all.entity.StudentAdmin;

import java.util.List;

public interface StudentAdminDao {
    public List<StudentAdmin> init(Integer id);
    public Integer update(StudentAdmin studentAdmin);
    public Integer upload(Integer id,String fileName);
    public Integer delete(Integer id);
    public StudentAdmin findByUsername(String username);
}
