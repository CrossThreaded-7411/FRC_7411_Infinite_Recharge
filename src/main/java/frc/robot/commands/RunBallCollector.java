package frc.robot.commands;

import java.util.logging.Logger;
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

  // @//Override
  // public boolean isFinished() {

  // System.out.println("Toggled motor on is Finished");
  // return false;
  // }

  @Override
  public void execute() {
    int direction = Robot.m_robotContainer.driver1Joystick.getPOV(0);
    Robot.m_robotContainer.driver1Joystick.getPOV();
    this.setBallCollectorMotorPower(direction);
  }

  private void setBallCollectorMotorPower(int direction) {

    if (direction == 0) {
      m_ballCollector.setMotorPower(-0.35);
      // POV UP button is pressed
      // do something

    } else if (direction == 180) {
      m_ballCollector.setMotorPower(0.35);
      // POV DOWN button is pressed
      // do something else

    } else if (direction == 270) {
      m_ballCollector.setMotorPower(0.0); // when POV is not pressed will not spin

    }

  }

}
