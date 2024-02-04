package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;

public class RealJoshArmIO implements IJoshArmIO {

    private CANSparkMax motor;
    private Encoder encoder;

    public RealJoshArmIO() {
        motor = new CANSparkMax(Constants.ARM_MOTOR_ID, MotorType.kBrushless);
        encoder = new Encoder(Constants.ENCODER_CHANNEL_A, Constants.ENCODER_CHANNEL_B);
    }

    @Override
    public void setMotorOutput(double power) {
        motor.set(power * 12);
    }

    @Override
    public double getMeasurement() {
        return (encoder.get() / (double) Constants.ENCODER_COUNT) * 360.0;
    }

    @Override
    public void periodic() {
        //Do nothing
    }
    
}
