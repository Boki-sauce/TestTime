package all.dto;

import all.entity.StudentAdmin;

public class StudentAdminDto {
    private Integer code;
    private StudentAdmin studentAdmin;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public StudentAdmin getStudentAdmin() {
        return studentAdmin;
    }

    public void setStudentAdmin(StudentAdmin studentAdmin) {
        this.studentAdmin = studentAdmin;
    }
}
