package all.entity;

public class Repair {
    private Integer id;
    private Integer dormitoryId;
    private String dormitoryName;
    private Integer studentId;
    private String studentNumber;
    private String studentName;
    private Integer dormitoryAdminId;
    private String dormitoryAdminName;
    private String state;
    private String instruction;
    private String createDate;

    public Repair(Integer dormitoryId, Integer studentId, String instruction) {
        this.dormitoryId = dormitoryId;
        this.studentId = studentId;
        this.instruction = instruction;
    }

    public Repair(Integer id, String dormitoryName, String studentNumber, String studentName, String state, String instruction, String createDate) {
        this.id = id;
        this.dormitoryName = dormitoryName;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.state = state;
        this.instruction = instruction;
        this.createDate = createDate;
    }

    public Repair(Integer id, String dormitoryName, String studentNumber, String studentName, String dormitoryAdminName, String state, String instruction, String createDate,Integer dormitoryId,Integer studentId) {
        this.id = id;
        this.dormitoryName = dormitoryName;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.dormitoryAdminName = dormitoryAdminName;
        this.state = state;
        this.instruction = instruction;
        this.createDate = createDate;
        this.dormitoryId = dormitoryId;
        this.studentId = studentId;
    }

    public Repair(Integer id, Integer dormitoryId, String dormitoryName, String studentNumber, String studentName, String state, String instruction, String createDate,Integer studentId,Integer dormitoryAdminId) {
        this.id = id;
        this.dormitoryId = dormitoryId;
        this.dormitoryName = dormitoryName;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.state = state;
        this.instruction = instruction;
        this.createDate = createDate;
        this.studentId = studentId;
        this.dormitoryAdminId = dormitoryAdminId;
    }

    public Repair(Integer id, String dormitoryName, String studentNumber, String studentName, String dormitoryAdminName, String state, String instruction, String createDate) {
        this.id = id;
        this.dormitoryName = dormitoryName;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.dormitoryAdminName = dormitoryAdminName;
        this.state = state;
        this.instruction = instruction;
        this.createDate = createDate;
    }
    public Repair(Integer dormitoryId, Integer studentId, Integer dormitoryAdminId, String instruction) {
        this.dormitoryId = dormitoryId;
        this.studentId = studentId;
        this.dormitoryAdminId = dormitoryAdminId;
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDormitoryAdminName() {
        return dormitoryAdminName;
    }

    public void setDormitoryAdminName(String dormitoryAdminName) {
        this.dormitoryAdminName = dormitoryAdminName;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDormitoryAdminId() {
        return dormitoryAdminId;
    }

    public void setDormitoryAdminId(Integer dormitoryAdminId) {
        this.dormitoryAdminId = dormitoryAdminId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String careateDate) {
        this.createDate = careateDate;
    }
}
