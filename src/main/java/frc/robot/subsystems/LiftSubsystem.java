package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class LiftSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private VictorSP SlideliftMotor = new VictorSP(MotorPorts.liftSlideMotorPort);
   private VictorSP RaiseliftMotor = new VictorSP(MotorPorts.liftRaiseMotorPort);

   public LiftSubsystem()
   {
      logger.fine("entered Lift constructor");
      stopLiftMotors();

      RaiseliftMotor.setInverted(true);
   }

   public void stopLiftMotors()
   {
      SlideliftMotor.set(0.0);
      RaiseliftMotor.set(0.0);
   }

   public void runLiftMotors(double slide, double raise)
   {
      SlideliftMotor.set(slide);
      RaiseliftMotor.set(raise);
      logger.fine("lift slide motor: " + SlideliftMotor.get());
      logger.fine("lift raise motor:" + RaiseliftMotor.get());
   }

}