package all.entity;

public class Lost {
    private Integer id;
    private Integer studentId;
    private String studentName;
    private String studentNumber;
    private Integer dormitoryId;
    private String dormitoryName;
    private String instruction;
    private String telephone;
    private String createDate;

    public Lost(Integer studentId, Integer dormitoryId, String instruction, String telephone) {
        this.studentId = studentId;
        this.dormitoryId = dormitoryId;
        this.instruction = instruction;
        this.telephone = telephone;
    }

    public Lost(Integer id, Integer studentId, String studentName, String studentNumber, Integer dormitoryId, String dormitoryName, String instruction, String telephone, String createDate) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.dormitoryId = dormitoryId;
        this.dormitoryName = dormitoryName;
        this.instruction = instruction;
        this.telephone = telephone;
        this.createDate = createDate;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
