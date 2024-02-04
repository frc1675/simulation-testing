package frc.robot;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.SetJoshAngle;
import frc.robot.commands.SetShuffleboardAngle;
import frc.robot.subsystems.IJoshArmIO;
import frc.robot.subsystems.JoshArm;
import frc.robot.subsystems.SimJoshArmIO;

public class RobotContainer {

  private final JoshArm arm;
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    IJoshArmIO armIO;

    armIO = new SimJoshArmIO();
    arm = new JoshArm(armIO);

    configureBindings();
  }

  private void configureBindings() {
    ShuffleboardTab tab = Shuffleboard.getTab("JoshArm");
    GenericEntry e = tab.add("Variable target", 0).getEntry();

    m_driverController.a().onTrue(new SetJoshAngle(arm, 57.5));
    m_driverController.b().onTrue(new SetJoshAngle(arm, 90));
    m_driverController.x().onTrue(new SetShuffleboardAngle(arm, e, 90));
  }

  public Command getAutonomousCommand() {
    return new WaitCommand(1);
  }
}
