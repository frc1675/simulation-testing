package frc.robot.subsystems;

/**
 * Contract for an object that can perform the needed input and output for an Arm
 * 
 * The implementations of this interface do the actual "work" and only what is needed to accomplish it.
 * 
 * The arm IO does not have any internal feedback loop with which to control the arm; it only
 * takes motor output changes to apply to the motors and returns current measurements.
 * 
 * Examples:
 * the "sim" implementation uses the WPILib simulation class to simulate the physics of the arm and 
 *   report back what the measurement WOULD be for use in the simulator.
 * 
 * the "real" implementation uses "real" objects for motor controllers and sensors to control a 
 *   physical arm and provide feedback 
 * 
 * the "test" implementation provides hooks in its concrete class for use in unit testing to affirm
 *   that the logic of the Arm subsystem works as intended.
 */
public interface IArmIO {

    /**
     * Provides the arm IO with a power value that should be applied to the arm motor(s).
     * The implementation handles interpreting this power value into voltage or any other units.
     * @param power value to apply to the arm motor(s) between -1.0 (full reverse) and 1.0 (full forward)
     */
    public void setMotorOutput(double power);

    /**
     * @return the current measurement of the arm angle in degrees. 0 degrees is vertically down.
     */
    public double getMeasurement();

    /**
     * The arm IO implementation uses this method to perform any necessary updates or upkeep for the IO logic.
     * This should be called by the subsystem every control loop period.
     */
    public void periodic();
}
