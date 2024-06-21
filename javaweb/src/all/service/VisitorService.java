package all.service;

import all.entity.Visitor;

import java.util.List;

public interface VisitorService {
    public List<Visitor> list();
    public List<Visitor> search(String key,String value);
    public void save(Visitor visitor);
    public void delete(Integer id);
    public List<Visitor> init();
    public List<Visitor> initsearch(String key,String value);
}
