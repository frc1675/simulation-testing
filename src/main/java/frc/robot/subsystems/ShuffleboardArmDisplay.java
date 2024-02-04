package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ShuffleboardArmDisplay implements IArmDisplay {

    private String tabName;

    public ShuffleboardArmDisplay(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public void initializeDisplay(Arm arm) {
        ShuffleboardTab tab = Shuffleboard.getTab(tabName);
        tab.addDouble("Motor Power", () -> arm.getMotorPower() );
        tab.addDouble("Target Angle Degrees", () -> arm.getTarget());
        tab.addDouble("Current Angle", () -> arm.getMeasurement());
        tab.addBoolean("Is On Target?", () -> arm.onTarget());
        tab.add("PID", arm.getPidController());
    }
}
