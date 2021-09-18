package DataModels;

public class bloodPostData {

    private String id, name, bloodGroup, divison, district, address, phone, message;



    public bloodPostData(String id, String name, String bloodGroup, String divison, String district, String address, String phone, String message) {
        this.id = id;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.divison = divison;
        this.district = district;
        this.address = address;
        this.phone = phone;
        this.message = message;
    }

    public bloodPostData(String name, String bloodGroup, String divison, String district, String address, String phone, String message) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.divison = divison;
        this.district = district;
        this.address = address;
        this.phone = phone;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public bloodPostData(){

    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDivison() {
        return divison;
    }

    public void setDivison(String divison) {
        this.divison = divison;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
