package ClassAttributes;

public final class Address {
    // attributes
    private String _ward;
    private String _district;
    private String _city;

    // constructor
    public Address(){

    }

    public Address(String address){
        try {
            String[] add = address.split("|");
            _ward = add[0];
            _district = add[1];
            _city = add[2];
        }
        catch(IndexOutOfBoundsException e){

        }

    }

     public Address(String ward, String district, String city){

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
