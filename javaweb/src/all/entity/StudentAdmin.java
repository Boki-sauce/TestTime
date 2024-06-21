package all.entity;

public class StudentAdmin {
    private Integer id;
    private Integer studentId;
    private String username;
    private String password;
    private String name;
    private String telephone;
    private String gender;
    private String fileName;


    public StudentAdmin(Integer id, Integer studentId, String username, String password, String name, String gender,String telephone,  String fileName) {
        this.id = id;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.telephone = telephone;
        this.fileName = fileName;
    }

    public StudentAdmin(Integer id, String username, String password, String name, String gender, String telephone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.telephone = telephone;
    }

    public StudentAdmin(Integer id, Integer studentId, String username, String password, String name, String gender, String telephone) {
        this.id = id;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.gender = gender;
    }

    public StudentAdmin(Integer id, String username, String password, String name, String telephone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
