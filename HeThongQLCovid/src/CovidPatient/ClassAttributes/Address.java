package CovidPatient.ClassAttributes;

public final class Address {
    // attributes
    private String _ward;
    private String _district;
    private String _city;

    // constructor
    Address(){

    }
    Address(String ward, String district, String city){
        this.setAddress(ward, district, city);
    }

    //methods
    public void setAddress(String ward, String district, String city){
        _ward = ward;
        _district = district;
        _city = city;
    }

    public Address getAddress(){
        return this;
    }

}
