// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.Constants;

/** Add your docs here. */
public class SimJoshArmIO implements IJoshArmIO {

    // 15kg 30in
    // start at 57.5 deg go to 185 deg
    private static final double TICK = 2.0 * Math.PI / 8192; // throughbore encoder
    SingleJointedArmSim armSim;
    
    double angleRads;

    double motorSpeed; // -1 to 1

    public SimJoshArmIO() {
        armSim = new SingleJointedArmSim(
          DCMotor.getNEO(1),
          100,
          SingleJointedArmSim.estimateMOI(Units.inchesToMeters(30), 15),
          Units.inchesToMeters(30),
          Units.degreesToRadians(57.5),
          Units.degreesToRadians(200),
          true,
          Units.degreesToRadians(57.5),
          VecBuilder.fill(TICK) // Add noise with a std-dev of 1 tick
          );
    }

    @Override
    public void setMotorOutput(double power) {
        motorSpeed = power;
    }

    @Override
    public double getMeasurement() {
        return Units.radiansToDegrees(angleRads);
    }

    @Override
    public void periodic() {
        armSim.setInput(motorSpeed * 12.0);

        armSim.update(0.020);

        angleRads = armSim.getAngleRads();
    }
}
