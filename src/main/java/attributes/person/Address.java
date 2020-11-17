package attributes.person;

public class Address {
    private String country;
    private String city;
    private String streetAndNumber;
    private String zipcode;

    public Address(String country, String city, String streetAndNumber, String zipcode) {
        this.country = country;
        this.city = city;
        this.streetAndNumber = streetAndNumber;
        this.zipcode = zipcode;
    }

    public String addressToString(){
        return " country: " + country + "\n" + " city: " + city + "\n" + " street and number: " + streetAndNumber + "\n" + " zipcode: " + zipcode;
    }

    public void correctData(String country, String city, String streetAndNumber, String zipcode) {
        this.country = country;
        this.city = city;
        this.streetAndNumber = streetAndNumber;
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getZipcode() {
        return zipcode;
    }
}
