package frc.robot.subsystems;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

  private IArmIO armIO;
  private PIDController pid;
  private double targetAngle = 57.5;
  private ShuffleboardTab tab;
  private double motorPower = 0.0;

  public Arm(IArmIO armIO, List<IArmDisplay> displays) {
    this.armIO = armIO;
    pid = new PIDController(.01, 0, 0);

    for (IArmDisplay display : displays)
    {
      display.initializeDisplay(this);
    }
  }

  public void setTarget(double angleDeg) {
    targetAngle = angleDeg;
  }

  public boolean onTarget() {
    return Math.abs(getMeasurement() - targetAngle) < 0.5;
  }

  @Override
  public void periodic() {
    motorPower = pid.calculate(getMeasurement(), targetAngle);
    armIO.setMotorOutput(motorPower);
    armIO.periodic();
  }

  public double getMotorPower() {
    return motorPower;
  }

  public double getTarget() {
    return targetAngle;
  }

  public double getMeasurement() {
    return armIO.getMeasurement();
  }

  public PIDController getPidController() {
    return pid;
  }
}
