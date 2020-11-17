package attributes.person;

public class PersonMain {
    public static void main(String[] args) {
        Person person = new Person("John","21021de");
        Address address = new Address("country","city", "streetAndNumber",  "zipcode");
        person.moveTo(address);

        System.out.println(person.personToString());

    }
}
