

/**
 * The 'Battery' class simulates a battery in which excess units generated by the house is stored in the battery up to the
 * battery capacity and is discharged if the house consumes more than it produces to reduce the cost of power drawn from the mains
 */
public class Battery {

    /** Charge Drawn from the battery over a course of a simulated period*/
    protected double chargeDrawn;

    /**Capacity of the battery*/
    double batteryCapacity;

    /**Current charge of the battery*/
    private double batteryCharge;


    /**
     * Constructor to initialise the initial charge and the battery capacity
     *
     * @param batteryCharge   Initial battery charge
     * @param batteryCapacity Battery Capacity
     */
    public Battery(double batteryCharge, double batteryCapacity) {
        this.batteryCharge = batteryCharge;
        this.batteryCapacity = batteryCapacity;
    }


    /**
     * 'chargeBattery' is a method which simulates charging of the battery up to the battery capacity in which the excess is lost
     *
     * @param meterUnits units to charge the battery with
     */
    public void chargeBattery(double meterUnits) {
        batteryCharge += meterUnits;

        if (batteryCharge >= batteryCapacity) {
            batteryCharge = batteryCapacity;
        }

    }


    /**
     * 'dischargeBattery' is a method to draw units from the battery if there is charge and calculate the difference
     * if there is less units than requested
     *
     * @param unitsToDischarge units that are to be taken from the battery
     * @return returns the battery charge of units so it can be deducted from the meter reading
     */
    public double dischargeBattery(double unitsToDischarge) {
        if (unitsToDischarge < batteryCharge) {
            setBatteryCharge(batteryCharge - unitsToDischarge);
            chargeDrawn += batteryCharge;
            return batteryCharge;
        } else {
            setBatteryCharge(unitsToDischarge - batteryCharge);
            chargeDrawn += batteryCharge;
            return batteryCharge;
        }
    }

    /*Getter and Setter Methods*/

    public double getBatteryCharge() {
        return batteryCharge;
    }

    private void setBatteryCharge(double batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getChargeDrawn() {
        return chargeDrawn;
    }
}