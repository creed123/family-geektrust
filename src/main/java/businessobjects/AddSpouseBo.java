package businessobjects;

public class AddSpouseBo {

    private String name;

    private String gender;

    private String spouseName;

    public AddSpouseBo(String name, String spouseName, String gender) {
        this.name = name;
        this.spouseName = spouseName;
        this.gender = gender;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
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
