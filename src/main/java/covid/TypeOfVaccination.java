package covid;

public enum TypeOfVaccination {
    PFIZERBIONTECH("Pfizer-BioNtech"), MODERNA("Moderna"), ASTRAZENECA("AstraZeneca"), SZPUTNYIK("Szputynik"), SINOPHARM("Sinopharm");

    private final String name;

    TypeOfVaccination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
