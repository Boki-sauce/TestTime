package all.dao.impl;

import all.dao.StudentAdminDao;
import all.entity.StudentAdmin;
import all.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentAdminDaoImpl implements StudentAdminDao {
    @Override
    public List<StudentAdmin> init(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  * from student_admin where id = ?";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<StudentAdmin> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                id =resultSet.getInt(1);
                Integer studentId = resultSet.getInt(2);
                String username= resultSet.getString(3);
                String password = resultSet.getString(4);
                String name = resultSet.getString(5);
                String gender = resultSet.getString(6);
                String telephone = resultSet.getString(7);
                String fileName = resultSet.getString(8);
                list.add(new StudentAdmin(id,studentId,username,password,name,gender,telephone,fileName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer update(StudentAdmin studentAdmin) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update student_admin set username = ?,password = ? ,name = ?,gender = ?,telephone = ? where id = ?";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,studentAdmin.getUsername());
            statement.setString(2,studentAdmin.getPassword());
            statement.setString(3,studentAdmin.getName());
            statement.setString(4,studentAdmin.getGender());
            statement.setString(5,studentAdmin.getTelephone());
            statement.setInt(6,studentAdmin.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public Integer upload(Integer id,String fileName) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update student_admin set file_name = ? where id = ?";
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
    public Integer delete(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="delete from student_admin where id = "+id;
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
    public StudentAdmin findByUsername(String username) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select * from student_admin where username = '"+username+"'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id =resultSet.getInt(1);
                Integer studentId = resultSet.getInt(2);
                username = resultSet.getString(3);
                String password = resultSet.getString(4);
                String name = resultSet.getString(5);
                String gender = resultSet.getString(6);
                String telephone = resultSet.getString(7);
                return new StudentAdmin(id,studentId,username,password,name,telephone,gender);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return null;
    }
}
