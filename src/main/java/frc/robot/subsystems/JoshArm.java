// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JoshArm extends SubsystemBase {

  private IJoshArmIO armIO;
  private PIDController pid;
  private double targetAngle = 57.5;
  private ShuffleboardTab sb;
  private double motorPower = 0.0;

  /** Creates a new JoshArm. */
  public JoshArm(IJoshArmIO armIO) {
    this.armIO = armIO;
    pid = new PIDController(.001, 0, 0);

    sb = Shuffleboard.getTab("JoshArm");
    sb.addDouble("motorPower", () -> motorPower );
    sb.addDouble("measurement", () -> armIO.getMeasurement());
  }

  @Override
  public void periodic() {
    motorPower = pid.calculate(armIO.getMeasurement(), targetAngle);
    armIO.setMotorOutput(motorPower);
    armIO.periodic();
  
  }

  public void setTarget(double angleDeg)
  {
    targetAngle = angleDeg;
  }
}
