package frc.robot.commands;

import java.util.logging.Logger;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCollectorSubsystem;

public class RunBallCollector extends CommandBase 
{
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final double m_CollectormotorPower;
    private final BallCollectorSubsystem m_ballCollector;
    

      
      public RunBallCollector(BallCollectorSubsystem subsystem, double CollectormotorPower)
      {
        m_ballCollector = subsystem;
        m_CollectormotorPower = CollectormotorPower;
        addRequirements(subsystem);

        logger.fine(" Collector command constructor complete");
        
      }
    
    @Override
    public void initialize()
    {

      logger.fine("Collector command requested motor power at " + m_CollectormotorPower);
      m_ballCollector.setMotorPower(m_CollectormotorPower);
      /*
        Operated by a single toggle button for on and off, feedback on SmartDashboard, fixed power, needs to reverse by a seperate toggle button
      */

    
    }
    
    @Override
    public boolean isFinished()
    {
      return true;
    }
    
}
