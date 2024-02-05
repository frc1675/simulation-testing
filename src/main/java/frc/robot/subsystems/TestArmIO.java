package frc.robot.subsystems;

public class TestArmIO implements IArmIO {

    public boolean periodicCalled = false;
    public double currentMotorOutput = 0.0;
    public double currentMeasurement = 0.0;

    @Override
    public void setMotorOutput(double power) {
        currentMotorOutput = power;
    }

    @Override
    public double getMeasurement() {
        return currentMeasurement;
    }

    @Override
    public void periodic() {
        periodicCalled = true;
    }

    public double getCurrentMotorOutput() {
        return currentMotorOutput;
    }

    public void setCurrentMeasurement(double currentMeasurement) {
        this.currentMeasurement = currentMeasurement;
    }
}
