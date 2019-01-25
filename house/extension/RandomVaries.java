import java.util.Random;

/**
 * RandomVaries is a type of an appliance. This appliance is "Switched on" randomly during the day and
 * uses a random amount of units each hour.
 */
public class RandomVaries extends ApplianceBuilder {

    private final int probability;
    private final double minUnits;
    private final double maxUnits;

    /**
     * Constructor which initialises the properties which store the range of units it can consume and the probability
     * it will be switched on (1 in n).
     */
    public RandomVaries(String name, double minUnits, double maxUnits, int probability) {
        super(name);
        this.minUnits = minUnits;
        this.maxUnits = maxUnits;
        this.probability = probability;

    }

    /**
     * Overrides timePasses to calculate how many units (within an interval) the appliance consumes if switched on
     */
    public void timePasses() {
        Random rand = new Random();

        boolean chanceTime = rand.nextInt(probability) == 0;

        if (chanceTime) {
            this.tellMeterToConsumeUnits(this.minUnits + rand.nextDouble() * (this.maxUnits - this.minUnits));
        }
    }
}
