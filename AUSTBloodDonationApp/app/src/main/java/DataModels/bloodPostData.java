package DataModels;

public class bloodPostData {

    private String bloodGroup, divison, district, address;

    public bloodPostData(String bloodGroup, String divison, String district, String address) {
        this.bloodGroup = bloodGroup;
        this.divison = divison;
        this.district = district;
        this.address = address;
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
