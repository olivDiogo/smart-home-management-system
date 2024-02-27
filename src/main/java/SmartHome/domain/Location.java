package SmartHome.domain;

public class Location {
    private AddressFactory _addressFactory;
    private GpsFactory _gpsFactory;
    private Address _address;
    private Gps _gpsLocation;
    protected Location(String street, String zipCode, int doorNumber, double latitude, double longitude, AddressFactory addressFactory, GpsFactory gpsFactory) throws IllegalArgumentException {
        _addressFactory = addressFactory;
        _gpsFactory = gpsFactory;
        this._address = _addressFactory.createAddress(street, zipCode, doorNumber);
        this._gpsLocation = _gpsFactory.createGps(latitude, longitude);
    }
    public Address getAddress() {
        return this._address;
    }
    public Gps getGpsLocation() {
        return this._gpsLocation;
    }

    @Override
    public String toString() {
        return "Location{" +
                 _address.toString() +
                 _gpsLocation.toString() +
                '}';
    }
}