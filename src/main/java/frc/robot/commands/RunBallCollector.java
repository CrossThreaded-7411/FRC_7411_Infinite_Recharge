package frc.robot.commands;

import java.util.logging.Logger;
import  edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCollectorSubsystem;

public class RunBallCollector extends CommandBase {
  private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private final BallCollectorSubsystem m_ballCollector;

  public RunBallCollector(BallCollectorSubsystem subsystem) {
    m_ballCollector = subsystem;
    addRequirements(subsystem);

    logger.fine(" Collector command constructor complete");

  }

  @Override
  public void initialize() {

  }

  //@//Override
  //public boolean isFinished() {

    // System.out.println("Toggled motor on is Finished");
    //return false;
  //}




  @Override
  public void execute()
    {
      int direction = Robot.m_robotContainer.driver1Joystick.getPOV(0);
      this.setBallCollectorMotorPower(direction);
    }


  private void setBallCollectorMotorPower(int direction)
  {
    // int direction = GamePadPOVAxis(1);

   // VictorSP.set(POVYAxisValue);
    //VictorSP.set(POVXAxisValue);
   // double POVYAxisValue = Math.cos(Math.toRadians(0));
   // double POVXAxisValue = Math.sin(Math.toRadians(90));

    if (direction == 0) {
      m_ballCollector.setMotorPower(0.5);
      // POV UP button is pressed
      // do something

    } else if (direction == 180) {
      m_ballCollector.setMotorPower(-0.5);
      // POV DOWN button is pressed
      // do something else

    


    } else if (direction == 90)  {
      m_ballCollector.setMotorPower(0.0); // when POVr will not spin
    
    }

    }

  private void stopBallCollectorMotor() {
  }

  private int GamePadPOVAxis(int i) {
    return 0;
  }

}
