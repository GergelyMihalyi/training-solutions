package exam03;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Cruise {
    private Boat boat;
    private LocalDate sailing;
    private double basicPrice;
    private List<Passenger> passengers = new ArrayList<>();

    public Cruise(Boat boat, LocalDate sailing, double basicPrice) {
        isNotNull(boat);
        isNotNull(sailing);
        isNotNull(basicPrice);
        this.boat = boat;
        this.sailing = sailing;
        this.basicPrice = basicPrice;
    }

    public Boat getBoat() {
        return boat;
    }

    public LocalDate getSailing() {
        return sailing;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void bookPassenger(Passenger passenger) {
        isNotNull(passenger);
        if (!isNotFull()) {
            throw new IllegalArgumentException("Boat is full");
        }
        passengers.add(passenger);
    }

    public double getPriceForPassenger(Passenger passenger) {
        isNotNull(passenger);
        double price = getBasicPrice() * passenger.getCruiseClass().getValue();
        return price;
    }

    public Passenger findPassengerByName(String name) {
        if (name.isEmpty()) {
            throw new IllegalStateException("Name cannot be empty!");
        }
        for (Passenger passenger : passengers) {
            if (name.equals(passenger.getName())) {
                return passenger;
            }
        }
        return null;

    }

    public List<String> getPassengerNamesOrdered() {
        isNotNull(passengers);
        List<String> orderedPassengerNames = passengers.stream().sorted(Comparator.comparing(Passenger::getName)).map(passenger -> passenger.getName()).collect(Collectors.toList());
        return orderedPassengerNames;
    }

    public double sumAllBookingsCharged() {
        isNotNull(passengers);
        double sumIncome = 0.0;
        for (Passenger passenger : passengers) {
            sumIncome += getPriceForPassenger(passenger);
        }
        return sumIncome;
    }

    public Map<CruiseClass, Long> countPassengerByClass() {
        isNotNull(passengers);
        Map<CruiseClass, Long> counts =
        passengers.stream().collect(Collectors.groupingBy(passenger -> passenger.getCruiseClass(), Collectors.counting()));

        return counts;
    }

    private boolean isNotFull() {
        return getBoat().getMaxPassengers() > passengers.size();
    }

    private boolean isNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("Cannot be null!");
        }
        return true;
    }
}
