package TreatmentArea;

public class TreatmentArea {

    private static TreatmentArea _uniqueArea;

    //static block
    static{
        _uniqueArea = new TreatmentArea();
    }

    // attributes
    String _name;
    int _capacity;
    int _available;

    // constructor
    private TreatmentArea(){

    }

    // methods
    public static TreatmentArea getInstance(){
        return _uniqueArea;
    }

}
