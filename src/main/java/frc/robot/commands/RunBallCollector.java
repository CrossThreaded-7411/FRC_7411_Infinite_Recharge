package frc.robot.commands;

import java.util.logging.Logger;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCollectorSubsystem;

public class RunBallCollector extends CommandBase 
{

    private final BallCollectorSubsystem m_ballCollectorSubsystem = new BallCollectorSubsystem();
    public RunBallCollector()
    {       
      
    }

    public void execute()
    {


    
    }

    public void RunBallCollectorMotor(double collectormotorspeed)
    {
      m_ballCollectorSubsystem.ballCollectorMotor.set(collectormotorspeed);
    }

    
}
