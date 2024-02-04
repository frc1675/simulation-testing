// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

/** Add your docs here. */
public interface IJoshArmIO {

    public void setMotorOutput(double power);

    public double getMeasurement();

    public void periodic();
}
