package frc.robot.subsystems;

import java.util.logging.Logger;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;

public class BallCollectorSubsystem extends SubsystemBase {
    
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public VictorSPX ballCollectorMotor = new VictorSPX(CANID.ballCollector);

    public BallCollectorSubsystem() {
        logger.fine("entered ball collector constructor ");
        stopBallCollectorMotor();
    }

    public void setMotorPower(double targetPowerCollector) {
        ballCollectorMotor.set(ControlMode.PercentOutput, targetPowerCollector);
        logger.info("Ball Collector motor = " + ballCollectorMotor.getMotorOutputPercent());
    }

    public void stopBallCollectorMotor() {
        ballCollectorMotor.set(ControlMode.PercentOutput, 0.0);
        System.out.println("Motor set to off");
    }

    public void runBallCollectorMotor(double collectorSpeed) {
        ballCollectorMotor.set(ControlMode.PercentOutput, collectorSpeed);
        logger.fine("ball collector motor:" + ballCollectorMotor.getMotorOutputPercent());
        System.out.println("Power set to " + ballCollectorMotor.getMotorOutputPercent());
    }
}
