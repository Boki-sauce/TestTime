package all.dao.impl;

import all.dao.RepairDao;
import all.entity.Repair;
import all.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDaoImpl implements RepairDao {
    @Override
    public List<Repair> list() {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select r.id,d.name,s.number,s.name,da.name,r.state,r.instruction,r.create_date,d.id,s.id from repair r,student s,dormitory d,dormitory_admin da where r.student_id = s.id and r.dormitory_id= d.id and da.id = r.dormitory_admin_id";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Repair> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id =resultSet.getInt(1);
                String dormitoryName= resultSet.getString(2);
                String studentNumber = resultSet.getString(3);
                String studentName = resultSet.getString(4);
                String dormitoryAdminName = resultSet.getString(5);
                String state = resultSet.getString(6);
                String instruction = resultSet.getString(7);
                String createDate = resultSet.getString(8);
                Integer dormitoryId = resultSet.getInt(9);
                Integer studentId = resultSet.getInt(10);
                list.add(new Repair(id,dormitoryName,studentNumber,studentName,dormitoryAdminName,state,instruction,createDate,dormitoryId,studentId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public List<Repair> search(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select r.id,d.name,s.number,s.name,da.name,r.state,r.instruction,r.create_date from repair r,student s,dormitory d,dormitory_admin da where r.student_id = s.id and r.dormitory_id= d.id and da.id = r.dormitory_admin_id";
        String keyStatement = "";
        if(key.equals("studentName")){
            keyStatement = " and s.name";
        }
        if (key.equals("dormitoryName")){
            keyStatement = " and d.name";
        }
        if(key.equals("createDate")){
            keyStatement = " and r.create_date";
        }
        if(key.equals("state")){
            keyStatement = " and r.state";
        }
        sql = sql + keyStatement + " like '%"+value+"%'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Repair> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id =resultSet.getInt(1);
                String dormitoryName= resultSet.getString(2);
                String studentNumber = resultSet.getString(3);
                String studentName = resultSet.getString(4);
                String dormitoryAdminName = resultSet.getString(5);
                String state = resultSet.getString(6);
                String instruction = resultSet.getString(7);
                String createDate = resultSet.getString(8);
                list.add(new Repair(id,dormitoryName,studentNumber,studentName,dormitoryAdminName,state,instruction,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer save(Repair repair) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="insert into repair(dormitory_id,student_id,dormitory_admin_id,state,instruction,create_date)values(?,?,?,?,?,?)";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,repair.getDormitoryId());
            statement.setInt(2,repair.getStudentId());
            statement.setInt(3,repair.getDormitoryAdminId());
            statement.setString(4,repair.getState());
            statement.setString(5, repair.getInstruction());
            statement.setString(6,repair.getCreateDate());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public Integer update(Repair repair) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update repair set dormitory_id = ?,student_id = ?,dormitory_admin_id = ?,state = ?,instruction = ? where id = ?";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,repair.getDormitoryId());
            statement.setInt(2,repair.getStudentId());
            statement.setInt(3,repair.getDormitoryAdminId());
            statement.setString(4,repair.getState());
            statement.setString(5,repair.getInstruction());
            statement.setInt(6,repair.getId());
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
        String sql ="delete from repair where id = "+id;
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
}
