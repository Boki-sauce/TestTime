package all.dao;

import all.entity.Visitor;

import java.util.List;

public interface VisitorDao {
    public List<Visitor> list();
    public List<Visitor> search(String key,String value);
    public Integer save(Visitor visitor);
    public Integer delete(Integer id);
    public List<Visitor> init();
    public List<Visitor> initsearch(String key,String value);
}
