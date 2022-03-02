package Model;

public class Parent {

    private String parentFirstName;
    private String parentLastName;
    private String wrestlerName;
    private String parentEmail;
    private String parentPhone;
    private String parentAddress;
    private String parentZip;
    private String dbId;
    private String parentNotes;

    public Parent(String parentName, String lastName, String wrestlerName, String parentEmail, String parentPhone, String parentAddress, String parentZip, String dbId, String notes) {
        this.parentFirstName = parentName;
        this.parentLastName = lastName;
        this.wrestlerName = wrestlerName;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
        this.parentAddress = parentAddress;
        this.parentZip = parentZip;
        this.dbId = dbId;
        this.parentNotes = notes;
    }

    public String getParentFirstName() {
        return parentFirstName;
    }

    public String getParentLastName() {
        return parentLastName;
    }

    public String getWrestlerName() {
        return wrestlerName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public String getParentZip() {
        return parentZip;
    }

    public String getParentNotes() {
        return parentNotes;
    }

    public String getDbId() {
        return dbId;
    }

    @Override
    public String toString() {
        return "AddParentView{" +
                "parentName='" + parentFirstName + '\'' +
                ", lastName='" + parentLastName + '\'' +
                '}';
    }
}
