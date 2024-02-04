package frc.robot.subsystems;

public interface IJoshArmIO {

    public void setMotorOutput(double power);

    public double getMeasurement();

    public void periodic();
}
