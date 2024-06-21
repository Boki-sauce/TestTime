package all.entity;
public class Visitor {
    private Integer id;
    private String name;
    private String gender;
    private String instruction;
    private String telephone;
    private String state;
    private String createDate;

    public Visitor(Integer id, String name, String gender, String instruction, String telephone, String state, String createDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.instruction = instruction;
        this.telephone = telephone;
        this.state = state;
        this.createDate = createDate;
    }

    public Visitor(String name, String gender, String instruction, String telephone) {
        this.name = name;
        this.gender = gender;
        this.instruction = instruction;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
