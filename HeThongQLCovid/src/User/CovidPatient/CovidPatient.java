package User.CovidPatient;


import ClassAttributes.Address;
import ClassAttributes.CurrentStatus;
import ClassAttributes.Role;
import TreatmentArea.TreatmentArea;
import User.User;

public class CovidPatient extends User {

    // attributes
    private String _name;
    private String _dob;
    private Address _address;
    private  TreatmentArea _treatmentArea;
    private CurrentStatus _status;
    private CovidPatient _patientRelavent;

    // constructor
    public CovidPatient(){
        super(Role.USER);
    }

    // method


}
