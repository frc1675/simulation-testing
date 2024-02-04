package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JoshArm extends SubsystemBase {

  private IJoshArmIO armIO;
  private PIDController pid;
  private double targetAngle = 57.5;
  private ShuffleboardTab tab;
  private double motorPower = 0.0;

  public JoshArm(IJoshArmIO armIO) {
    this.armIO = armIO;
    pid = new PIDController(.01, 0, 0);

    tab = Shuffleboard.getTab("JoshArm");
    tab.addDouble("motorPower", () -> motorPower );
    tab.addDouble("target", () -> targetAngle);
    tab.addDouble("measurement", () -> armIO.getMeasurement());
    tab.addBoolean("onTarget", () -> onTarget());
    tab.add("PID", pid);
  }

  @Override
  public void periodic() {
    motorPower = pid.calculate(armIO.getMeasurement(), targetAngle);
    armIO.setMotorOutput(motorPower);
    armIO.periodic();
  
  }

  public void setTarget(double angleDeg) {
    targetAngle = angleDeg;
  }

  public boolean onTarget() {
    return Math.abs(armIO.getMeasurement() - targetAngle) < 0.5;
  }
}
