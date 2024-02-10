package frc.robot.subsystems;

import java.util.List;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

  private IArmIO armIO;
  private ArmFeedforward ff;
  private TrapezoidProfile.Constraints profileConstraints;
  private ProfiledPIDController pid;
  private double targetAngle = Constants.HOME_DEG;
  private double motorPower = 0.0;
  private double pidOut = 0.0;
  private double ffOut = 0.0;

  public Arm(IArmIO armIO, List<IArmDisplay> displays) {
    this.armIO = armIO;
    // pid = new PIDController(.075, 1.2, .01);

    profileConstraints = new TrapezoidProfile.Constraints(
        130, // max velocity in deg/s
        650); // max accel in deg/s^2

    ff = new ArmFeedforward(
        0.5, // we would experiment? (minimum voltage to move at all)
        0.58, // from recalc
        1.95, // from recalc
        .06); // from recalc

    pid = new ProfiledPIDController(0.0001, 0, 0, profileConstraints);

    if (displays != null) {
      for (IArmDisplay display : displays) {
        display.initializeDisplay(this);
      }
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
    pidOut = pid.calculate(getMeasurement(), targetAngle);
    ffOut = ff.calculate(Units.degreesToRadians(getMeasurement()), Units.degreesToRadians(pid.getSetpoint().velocity))
        / 12;
    motorPower = pidOut + ffOut;
    armIO.setMotorOutput(motorPower);
    armIO.periodic();
  }

  public double getPIDOut() {
    return pidOut;
  }

  public double getFFOut() {
    return ffOut;
  }

  public double getMotorPower() {
    return motorPower;
  }

  public double getTarget() {
    return targetAngle;
  }

  public double currentSetpointVel() {
    return pid.getSetpoint().velocity;
  }

  public double currentSetpointPos() {
    return pid.getSetpoint().position;
  }

  public double getMeasurement() {
    return armIO.getMeasurement();
  }

  public ProfiledPIDController getPidController() {
    return pid;
  }
}
