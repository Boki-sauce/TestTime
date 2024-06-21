package all.dao.impl;

import all.dao.DormitoryAdminDao;
import all.entity.DormitoryAdmin;
import all.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryAdminDaoImpl implements DormitoryAdminDao {
    @Override
    public List<DormitoryAdmin> init(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  * from dormitory_admin where id = ?";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<DormitoryAdmin> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                id =resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String telephone = resultSet.getString(6);
                String fileName = resultSet.getString(7);
                list.add(new DormitoryAdmin(id,username,password,name,gender,telephone,fileName));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer upload(Integer id, String fileName) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update dormitory_admin set file_name = ? where id = ?";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,fileName);
            statement.setInt(2,id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public List<DormitoryAdmin> list() {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  * from dormitory_admin";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<DormitoryAdmin> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String telephone = resultSet.getString(6);
                list.add(new DormitoryAdmin(id,username,password,name,gender,telephone));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public List<DormitoryAdmin> search(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        //   todo   两个%%里为空即为查询所有的数据（即value为空）
        String sql ="select  * from dormitory_admin where "+key+" like '%"+value+"%'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<DormitoryAdmin> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String gender = resultSet.getString(5);
                String telephone = resultSet.getString(6);
                list.add(new DormitoryAdmin(id,username,password,name,gender,telephone));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer save(DormitoryAdmin dormitoryAdmin) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="insert into dormitory_admin(username,password,name,gender,telephone,file_name)values(?,?,?,?,?,'5.jpg')";
        PreparedStatement statement =null;
        Integer result =null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,dormitoryAdmin.getUsername());
            statement.setString(2,dormitoryAdmin.getPassword());
            statement.setString(3,dormitoryAdmin.getName());
            statement.setString(4,dormitoryAdmin.getGender());
            statement.setString(5,dormitoryAdmin.getTelephone());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public Integer update(DormitoryAdmin dormitoryAdmin) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update dormitory_admin set username = ?,password = ? ,name = ?,gender = ?,telephone = ? where id = ?";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,dormitoryAdmin.getUsername());
            statement.setString(2,dormitoryAdmin.getPassword());
            statement.setString(3,dormitoryAdmin.getName());
            statement.setString(4,dormitoryAdmin.getGender());
            statement.setString(5,dormitoryAdmin.getTelephone());
            statement.setInt(6,dormitoryAdmin.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="delete from dormitory_admin where id = "+id;
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public DormitoryAdmin findByUsername(String username) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  * from dormitory_admin where username = ?";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        DormitoryAdmin dormitoryAdmin = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id = resultSet.getInt(1);
                username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                dormitoryAdmin = new DormitoryAdmin(id,username,password,name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return dormitoryAdmin;
    }
}
