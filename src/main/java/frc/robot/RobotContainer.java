package frc.robot;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.SetArmAngle;
import frc.robot.commands.SetShuffleboardAngle;
import frc.robot.subsystems.IArmIO;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.RealArmIO;
import frc.robot.subsystems.SimArmIO;

public class RobotContainer {

  private final Arm arm;
  private final CommandXboxController m_driverController = new CommandXboxController(Constants.DRIVER_CONTROLLER);

  public RobotContainer() {
    IArmIO armIO;

    if (Robot.isSimulation()) {
      armIO = new SimArmIO();
    } else {
      armIO = new RealArmIO();
    }

    arm = new Arm(armIO);

    configureBindings();
  }

  private void configureBindings() {
    ShuffleboardTab tab = Shuffleboard.getTab("JoshArm");
    GenericEntry e = tab.add("Variable target", 0).getEntry();

    m_driverController.a().onTrue(new SetArmAngle(arm, 57.5));
    m_driverController.b().onTrue(new SetArmAngle(arm, 90));
    m_driverController.x().onTrue(new SetShuffleboardAngle(arm, e, 90));
  }

  public Command getAutonomousCommand() {
    return new WaitCommand(1);
  }
}
