package all.dao.impl;

import all.dao.LostDao;
import all.entity.Lost;
import all.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LostDaoImpl implements LostDao {
    @Override
    public List<Lost> list() {
        Connection connection = JDBCUtil.getconnection();
        String sql = "select l.id,d.name,d.id,s.id,s.number,s.name,l.instruction,l.telephone,l.create_date from lost l,student s,dormitory d where l.student_id = s.id and l.dormitory_id = d.id";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Lost> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id =resultSet.getInt(1);
                String dormitoryName = resultSet.getString(2);
                Integer dormitoryId = resultSet.getInt(3);
                Integer studentId = resultSet.getInt(4);
                String studentNumber = resultSet.getString(5);
                String studentName = resultSet.getString(6);
                String instruction = resultSet.getString(7);
                String telephone = resultSet.getString(8);
                String createDate = resultSet.getString(9);
                list.add(new Lost(id,studentId,studentName,studentNumber,dormitoryId,dormitoryName,instruction,telephone,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public List<Lost> search(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        String sql = "select l.id,d.name,d.id,s.id,s.number,s.name,l.instruction,l.telephone,l.create_date from lost l,student s,dormitory d where l.student_id = s.id and l.dormitory_id = d.id";
        String keyStatement = "";
        if(key.equals("studentName")){
            keyStatement = " and s.name";
        }
        if (key.equals("dormitoryName")){
            keyStatement = " and d.name";
        }
        if(key.equals("createDate")){
            keyStatement = " and l.create_date";
        }
        sql = sql + keyStatement + " like '%"+value+"%'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Lost> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer id =resultSet.getInt(1);
                String dormitoryName = resultSet.getString(2);
                Integer dormitoryId = resultSet.getInt(3);
                Integer studentId = resultSet.getInt(4);
                String studentNumber = resultSet.getString(5);
                String studentName = resultSet.getString(6);
                String instruction = resultSet.getString(7);
                String telephone = resultSet.getString(8);
                String createDate = resultSet.getString(9);
                list.add(new Lost(id,studentId,studentName,studentNumber,dormitoryId,dormitoryName,instruction,telephone,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer save(Lost lost) {
        Connection connection = JDBCUtil.getconnection();
        String sql = "insert into lost(student_id,dormitory_id,instruction,telephone,create_date)values(?,?,?,?,?)";
        PreparedStatement statement =null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);
            statement.setInt(1,lost.getStudentId());
            statement.setInt(2,lost.getDormitoryId());
            statement.setString(3,lost.getInstruction());
            statement.setString(4,lost.getTelephone());
            statement.setString(5, lost.getCreateDate());
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
        String sql ="delete from lost where id = "+id;
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
