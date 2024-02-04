package frc.robot.subsystems;

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

  public Arm(IArmIO armIO) {
    this.armIO = armIO;
    pid = new PIDController(.01, 0, 0);

    tab = Shuffleboard.getTab("JoshArm");
    tab.addDouble("Motor Power", () -> motorPower );
    tab.addDouble("Target Angle Degrees", () -> targetAngle);
    tab.addDouble("Current Angle", () -> armIO.getMeasurement());
    tab.addBoolean("Is On Target?", () -> onTarget());
    tab.add("PID", pid);
  }

  public void setTarget(double angleDeg) {
    targetAngle = angleDeg;
  }

  public boolean onTarget() {
    return Math.abs(armIO.getMeasurement() - targetAngle) < 0.5;
  }

  @Override
  public void periodic() {
    motorPower = pid.calculate(armIO.getMeasurement(), targetAngle);
    armIO.setMotorOutput(motorPower);
    armIO.periodic();
  
  }
}
