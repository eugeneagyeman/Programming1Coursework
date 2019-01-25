/**
 * CyclicFixed is a type of an appliance. This appliance is "Switched on" for a fixed number of hours each day and
 * uses a fixed amount of units each hour.
 */
public class CyclicFixed extends ApplianceBuilder {

    /**Units consumed in that hour*/
    private final double hourlyConsumption;

    /**Instance of the property that stores an appliance cycle length*/
    private final int activeTime;

    /**Current hour of the simulation*/
    private int currentHours = 0;


    /**
     * Constructor which initialises the properties which store its units and cycle length. It also checks that
     * the number of active hours is a value between 1 and 24.
     */
    public CyclicFixed(String name, double hourlyConsumption, int activeTime) throws Exception {
        super(name);
        this.hourlyConsumption = hourlyConsumption;
        if (activeTime >= 1 && activeTime <= 24) {
            this.activeTime = activeTime;
        } else {
            throw new Exception("This is an invalid amount of hours. Please choose between 1 and 24 hours.");
        }

    }

    /**
     * Overridden timePasses method that calculates how many units the appliance consumes in the active time period.
     */
    public void timePasses() {
        this.currentHours = (currentHours + 1) % 24;
        if (currentHours <= activeTime) {
            tellMeterToConsumeUnits(this.hourlyConsumption);
        }

    }
}
