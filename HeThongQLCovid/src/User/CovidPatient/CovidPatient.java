package User.CovidPatient;


import ClassAttributes.Address;
import ClassAttributes.Role;
import TreatmentArea.TreatmentArea;
import User.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CovidPatient extends User {



    // attributes
    private String _citizen_id;
    private String _name;
    private LocalDate _dob;
    private Address _address;
    private String _treatmentAreaID;
    private String _status;
    private String _patientRelavent;

    // constructor
    public CovidPatient(){
        super(Role.USER);
    }

    public CovidPatient(String citizen_id,String _name,
                        String _dob,
                        Address _address,
                        String _treatmentAreaID,
                        String _status,
                        String _patientRelavent) {
        super(Role.USER);
        this._citizen_id = citizen_id;
        this._name = _name;
        set_dob(_dob);
        this._address = _address;
        this._treatmentAreaID = _treatmentAreaID;
        this._status = _status;
        this._patientRelavent = _patientRelavent;
    }

    // method

    // setter and getter

    public String get_citizen_id() {
        return _citizen_id;
    }

    public void set_citizen_id(String _citizen_id) {
        this._citizen_id = _citizen_id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public LocalDate get_dob() {

        return _dob;
    }

    public void set_dob(String _dob) {

        this._dob = LocalDate.parse(_dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Address get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = new Address(_address);
    }

    public String get_treatmentArea() {
        return this._treatmentAreaID;
    }

    public void set_treatmentArea(String _ID) {

        this._treatmentAreaID = _ID;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {

        this._status = _status;
    }

    public String get_patientRelavent() {
        return _patientRelavent;
    }

    public void set_patientRelavent(String _patientRelavent) {
        this._patientRelavent = _patientRelavent;
    }

    //--

    public String getInfo() {
        return this.get_citizen_id()+" "+
                this.get_name()+" "+
                this.get_dob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+" "+
                this.get_address()+" "+
                this.get_status();
    }

}
