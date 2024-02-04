package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class SetArmAngle extends Command {
  private Arm arm;
  private double target;
  
  public SetArmAngle(Arm arm, double angleDeg) {
    
    this.arm = arm;
    this.target = angleDeg;
    
    addRequirements(arm);
  }

  @Override
  public void initialize() {
    arm.setTarget(target);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return arm.onTarget();
  }
}
