package frc.robot.commands;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.JoshArm;

public class SetShuffleboardAngle extends Command {
    
    private JoshArm arm;
    private GenericEntry entry;
    private double defaultValue;

    public SetShuffleboardAngle(JoshArm arm, GenericEntry entry, double defaultValue) {
        this.arm = arm;
        this.entry = entry;
        this.defaultValue = defaultValue;
    }

    @Override
    public void initialize() {
        arm.setTarget(entry.getDouble(defaultValue));
    }

    @Override
    public boolean isFinished() {
        return true;
    }



}
