package TreatmentArea;

public class TreatmentArea {

    // attributes
    String _id;
    String _name;
    int _capacity;
    int _available;

    // constructor
    private TreatmentArea(){

    }



    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_capacity() {
        return _capacity;
    }

    public void set_capacity(int _capacity) {
        this._capacity = _capacity;
    }

    public int get_available() {
        return _available;
    }

    public void set_available(int _available) {
        this._available = _available;
    }
}
