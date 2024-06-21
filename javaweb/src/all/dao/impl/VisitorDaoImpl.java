package all.dao.impl;

import all.dao.VisitorDao;
import all.entity.Visitor;
import all.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitorDaoImpl implements VisitorDao {
    @Override
    public List<Visitor> list() {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select * from visitor where state = '未离开'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Visitor> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String name= resultSet.getString(2);
                String gender = resultSet.getString(3);
                String instruction = resultSet.getString(4);
                String telephone = resultSet.getString(5);
                String state = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                list.add(new Visitor(id,name,gender,instruction,telephone,state,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public List<Visitor> search(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        //   todo   两个%%里为空即为查询所有的数据（即value为空）
        String sql = "select * from visitor s where state = '未离开' and s."+key+" like '%"+value+"%'";
        //todo    只能用来查找准确信息的，如整个学号或全部姓名
        // String sql ="select  s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state,s.create_date from student s,dormitory d where s.dormitory_id = d.id and s."+key+" like '"+value+"'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Visitor> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String name= resultSet.getString(2);
                String gender = resultSet.getString(3);
                String instruction = resultSet.getString(4);
                String telephone = resultSet.getString(5);
                String state = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                list.add(new Visitor(id,name,gender,instruction,telephone,state,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer save(Visitor visitor) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="insert into visitor(name,gender,instruction,telephone,state,create_date)values(?,?,?,?,?,?)";
        PreparedStatement statement =null;
        List<Visitor> list =new ArrayList<>();
        Integer result =null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,visitor.getName());
            statement.setString(2,visitor.getGender());
            statement.setString(3,visitor.getInstruction());
            statement.setString(4,visitor.getTelephone());
            statement.setString(5,visitor.getState());
            statement.setString(6,visitor.getCreateDate());
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
        String sql ="update visitor set state = '已离开' where id = ?";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public List<Visitor> init() {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select * from visitor where state = '已离开'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Visitor> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String name= resultSet.getString(2);
                String gender = resultSet.getString(3);
                String instruction = resultSet.getString(4);
                String telephone = resultSet.getString(5);
                String state = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                list.add(new Visitor(id,name,gender,instruction,telephone,state,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }
    public List<Visitor> initsearch(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        //   todo   两个%%里为空即为查询所有的数据（即value为空）
        String sql = "select * from visitor s where state = '已离开' and s."+key+" like '%"+value+"%'";
        //todo    只能用来查找准确信息的，如整个学号或全部姓名
        // String sql ="select  s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state,s.create_date from student s,dormitory d where s.dormitory_id = d.id and s."+key+" like '"+value+"'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Visitor> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String name= resultSet.getString(2);
                String gender = resultSet.getString(3);
                String instruction = resultSet.getString(4);
                String telephone = resultSet.getString(5);
                String state = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                list.add(new Visitor(id,name,gender,instruction,telephone,state,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }
}
