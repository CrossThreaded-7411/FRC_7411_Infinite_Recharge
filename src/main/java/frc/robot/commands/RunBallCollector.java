package frc.robot.commands;

import java.util.logging.Logger;
import  edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
  public void execute(){
    


  }

  private boolean readPOVAxis() {
    int direction = GamePadPOVAxis(1);

   // VictorSP.set(POVYAxisValue);
    //VictorSP.set(POVXAxisValue);
   // double POVYAxisValue = Math.cos(Math.toRadians(0));
   // double POVXAxisValue = Math.sin(Math.toRadians(90));

    if (direction == 0) {
      m_ballCollector.setMotorPower(1.0);
      return true;
      // POV UP button is pressed
      // do something

    } else if (direction == 180) {
      m_ballCollector.setMotorPower(-1.0);
      return true;
      // POV DOWN button is pressed
      // do something else

    } else {
      stopBallCollectorMotor();
      m_ballCollector.setMotorPower(0.0); // when POV is not pressed, motor will not spin
    
    return false;
    }

  }

  private void stopBallCollectorMotor() {
  }

  private int GamePadPOVAxis(int i) {
    return 0;
  }

}
