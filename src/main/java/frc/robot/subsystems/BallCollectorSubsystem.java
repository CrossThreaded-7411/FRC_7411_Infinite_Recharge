package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class BallCollectorSubsystem extends SubsystemBase

{
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public VictorSP ballCollectorMotor = new VictorSP(MotorPorts.ballCollectorMotorPort);

    public void BallCollectorSubsystem()
    {
        logger.fine("entered ball collector constructor ");
        stopballCollectorMotors();

    
    }  


    public void stopballCollectorMotors()
    {

        ballCollectorMotor.set(0.0);
    }

        public void runballCollectorMotor( double collectorSpeed)
        {
        
        ballCollectorMotor.set(collectorSpeed);
        logger.fine("ball collector motor:" + ballCollectorMotor.get());
        }


}




