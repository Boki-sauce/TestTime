package all.dao;

import all.entity.Building;

import java.util.List;

public interface BuildingDao {
    public List<Building> init();
    public List<Building> list();
    public List<Building> search(String key, String value);
    public Integer save (Building building);
    public Integer upadate(Building building);
    public Integer delete(Integer id);
}
