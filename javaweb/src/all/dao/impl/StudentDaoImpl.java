package all.dao.impl;

import all.dao.StudentDao;
import all.entity.Student;
import all.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> list() {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state,s.create_date from student s,dormitory d where s.dormitory_id = d.id";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Student> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String number= resultSet.getString(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                Integer dormitoryId = resultSet.getInt(5);
                String dormitoryName = resultSet.getString(6);
                String state = resultSet.getString(7);
                String createDate = resultSet.getString(8);
                list.add(new Student(id,number,name,gender,dormitoryId,dormitoryName,state,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public List<Student> search(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        //   todo   两个%%里为空即为查询所有的数据（即value为空）
        String sql = "select s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state,s.create_date from student s,dormitory d where s.dormitory_id = d.id and s."+key+" like '%"+value+"%'";
          //todo    只能用来查找准确信息的，如整个学号或全部姓名
       // String sql ="select  s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state,s.create_date from student s,dormitory d where s.dormitory_id = d.id and s."+key+" like '"+value+"'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Student> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String number= resultSet.getString(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                Integer dormitoryId = resultSet.getInt(5);
                String dormitoryName = resultSet.getString(6);
                String state = resultSet.getString(7);
                String createDate = resultSet.getString(8);
                list.add(new Student(id,number,name,gender,dormitoryId,dormitoryName,state,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer save(Student student) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="insert into student(number,name,gender,dormitory_id,state,create_date)values(?,?,?,?,?,?)";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,student.getNumber());
            statement.setString(2,student.getName());
            statement.setString(3,student.getGender());
            statement.setInt(4,student.getDormitoryId());
            statement.setString(5,student.getState());
            statement.setString(6,student.getCreateDate());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public Integer getIdByNumber(String number) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  id from student where number = ?";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Student> list =new ArrayList<>();
        Integer id = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,number);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                 id =resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return id;
    }

    @Override
    public void saveAdmin(Student student) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="insert into student_admin(student_id,username,password,name,gender,telephone,file_name)values(?,?,'123123',?,?,'待添加','5.jpg')";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,student.getId());
            statement.setString(2,student.getNumber());
            statement.setString(3,student.getName());
            statement.setString(4,student.getGender());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
//        return result;
    }

    @Override
    public Integer update(Student student) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update student set number = ?,name = ?,gender = ?,dormitory_id = ? where id = ?";
        String sql2 ="update student_admin set name = ?,gender = ? where student_id = ?";
        PreparedStatement statement =null;
        PreparedStatement statement2 =null;
        Integer result =null;
        Integer result2 =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,student.getNumber());
            statement.setString(2,student.getName());
            statement.setString(3,student.getGender());
            statement.setInt(4,student.getDormitoryId());
            statement.setInt(5,student.getId());
            result = statement.executeUpdate();
            statement2 = connection.prepareStatement(sql2);
            statement2.setString(1,student.getName());
            statement2.setString(2,student.getGender());
            statement2.setInt(3,student.getId());
            result2 = statement2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
            JDBCUtil.release(connection,statement2,null);
        }
        return result;
    }



    @Override
    public Integer delete(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="delete from student where id = "+id;
        String sql2 ="delete from student_admin where student_id = "+id;
        PreparedStatement statement =null;
        PreparedStatement statement2 =null;
        Integer result =null;
        Integer result2 =null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
            statement2 = connection.prepareStatement(sql2);
            result2 = statement2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
            JDBCUtil.release(connection,statement2,null);
        }
        return result;
    }

    @Override
    public List<Integer> findStudentIdByDormitoryId(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  id from student where dormitory_id ="+id;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Integer> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                list.add(resultSet.getInt(1));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer updateDormitory(Integer studentId, Integer dormitoryId) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update student set dormitory_id = ? where id = ?";
        PreparedStatement statement =null;
        Integer result =null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,dormitoryId);
            statement.setInt(2,studentId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,null);
        }
        return result;
    }

    @Override
    public List<Student> moveoutList() {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state from student s,dormitory d where s.dormitory_id = d.id and s.state = '入住'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Student> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String number= resultSet.getString(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                Integer dormitoryId = resultSet.getInt(5);
                String dormitoryName = resultSet.getString(6);
                String state = resultSet.getString(7);
                list.add(new Student(id,number,name,gender,dormitoryId,dormitoryName,state));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public List<Student> searchForMoveout(String key, String value) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  s.id,s.number,s.name,s.gender,s.dormitory_id,d.name,s.state from student s,dormitory d where s.dormitory_id = d.id and s.state = '入住'and s."+key+" like '%"+value+"%'";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Student> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int id =resultSet.getInt(1);
                String number= resultSet.getString(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                Integer dormitoryId = resultSet.getInt(5);
                String dormitoryName = resultSet.getString(6);
                String state = resultSet.getString(7);
                list.add(new Student(id,number,name,gender,dormitoryId,dormitoryName,state));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Integer updateStateById(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="update student set state = '迁出' where id = "+id;
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
    public List<Student> findByDormitoryId(Integer id) {
        Connection connection = JDBCUtil.getconnection();
        String sql ="select  id,number,name from student where dormitory_id ="+id;
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        List<Student> list =new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                id = resultSet.getInt(1);
                String number = resultSet.getString(2);
                String name = resultSet.getString(3);
                list.add(new Student(id,number,name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.release(connection,statement,resultSet);
        }
        return list;
    }
}
