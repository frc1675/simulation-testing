package frc.robot;

import java.util.ArrayList;
import java.util.List;

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
import frc.robot.subsystems.IArmDisplay;
import frc.robot.subsystems.RealArmIO;
import frc.robot.subsystems.ShuffleboardArmDisplay;
import frc.robot.subsystems.SimArmIO;

public class RobotContainer {

  private final Arm arm;
  private final CommandXboxController m_driverController = new CommandXboxController(Constants.DRIVER_CONTROLLER);

  public RobotContainer() {
    IArmIO armIO;
    List<IArmDisplay> armDisplays = new ArrayList<IArmDisplay>();
    if (Robot.isSimulation()) {
      armIO = new SimArmIO();
      armDisplays.add(new ShuffleboardArmDisplay("ArmDebug"));
    } else {
      armIO = new RealArmIO();
      armDisplays.add(new ShuffleboardArmDisplay("ArmDebug"));
      // one might add a concrete IArmDisplay for a "competition" dashboard here.
    }

    arm = new Arm(armIO, armDisplays);

    configureBindings();
  }

  private void configureBindings() {
    ShuffleboardTab tab = Shuffleboard.getTab("JoshArm");
    GenericEntry e = tab.add("Variable target", 0).getEntry();

    m_driverController.a().onTrue(new SetArmAngle(arm, Constants.HOME_DEG));
    m_driverController.b().onTrue(new SetArmAngle(arm, 0));
    m_driverController.x().onTrue(new SetShuffleboardAngle(arm, e, 135));
  }

  public Command getAutonomousCommand() {
    return new WaitCommand(1);
  }
}
