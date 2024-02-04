package frc.robot.commands;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class SetShuffleboardAngle extends Command {
    
    private Arm arm;
    private GenericEntry entry;
    private double defaultValue;

    public SetShuffleboardAngle(Arm arm, GenericEntry entry, double defaultValue) {
        this.arm = arm;
        this.entry = entry;
        this.defaultValue = defaultValue;
    }

    @Override
    public void initialize() {
        arm.setTarget(entry.getDouble(defaultValue));
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
