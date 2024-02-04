// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/** Add your docs here. */
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
