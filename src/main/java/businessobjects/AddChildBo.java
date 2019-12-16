package businessobjects;

public class AddChildBo {

    private String motherName;

    private String name;

    private String gender;

    public AddChildBo(String motherName, String name, String gender) {
        this.motherName = motherName;
        this.name = name;
        this.gender = gender;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
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
}
