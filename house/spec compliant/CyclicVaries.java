

import java.util.Random;

/**
 * CyclicVaries is a type of an appliance. This appliance is "Switched on" for a fixed number of hours each day and
 * uses a random amount of units each hour.
 */
public class CyclicVaries extends ApplianceBuilder {
    /**Instance of the property that stores an appliance cycle length*/
    private final int activeTime;

    /** Property storing the minimum units an appliance can use */
    private final double minUnits;

    /** Property storing the maxiumum units an appliance can use */
    private final double maxUnits;

    /**current hour of the simulation*/
    private int currentHours = 0;


    /**
     * Constructor which initialises the properties which store the range of units it can consume and its cycle length.
     * It also checks that the number of active hours is a value between 1 and 24.
     */
    public CyclicVaries(String name, double minUnits, double maxUnits, int activeTime) throws Exception {
        super(name);
        this.minUnits = minUnits;
        this.maxUnits = maxUnits;

        if (activeTime >= 1 && activeTime <= 24) {
            this.activeTime = activeTime;
        } else {
            throw new Exception("This is an incorrect amount of hours. Please enter a value between 1 and 24 hours (inclusive)");
        }
    }

    /**
     * Overridden timePasses method that calculates how many units (within an interval) the appliance consumes in the
     * active time period.
     */
    public void timePasses() {
        Random randomUnits = new Random();
        this.currentHours = (currentHours + 1) % 24;

        if (currentHours <= activeTime) {
            this.tellMeterToConsumeUnits(this.minUnits + randomUnits.nextDouble() * (this.maxUnits - this.minUnits));
        }


    }
}
