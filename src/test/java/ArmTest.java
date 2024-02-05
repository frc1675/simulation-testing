import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.TestArmIO;

public class ArmTest {
    
    private static Arm arm;
    private static TestArmIO io;

    @BeforeAll
    public static void setup() {
        io = new TestArmIO();
        arm = new Arm(io, null);

        io.setCurrentMeasurement(0);
    }

    @Test
    public void testOnTarget() {
        arm.setTarget(180);        

        assertFalse(arm.onTarget());

        io.setCurrentMeasurement(180);
        assertTrue(arm.onTarget());

        io.setCurrentMeasurement(180.3);
        assertTrue(arm.onTarget());

        io.setCurrentMeasurement(179.8);
        assertTrue(arm.onTarget());
    }

    @Test
    public void testPeriodic() {
        arm.periodic();

        assertTrue(io.periodicCalled);
    }

}
