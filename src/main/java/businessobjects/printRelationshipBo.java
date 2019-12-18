package businessobjects;

public class printRelationshipBo {

    private String name;

    private String relationShip;

    public printRelationshipBo(String name, String relationship) {
        this.name = name;
        this.relationShip = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }
}
