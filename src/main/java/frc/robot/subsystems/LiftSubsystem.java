package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class LiftSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private VictorSP liftSlideMotor = new VictorSP(MotorPorts.liftSlideMotorPort);
   private VictorSP liftRaiseMotor = new VictorSP(MotorPorts.liftRaiseMotorPort);

   public LiftSubsystem()
   {
      logger.fine("entered Lift constructor");
      stopLiftMotors();

      liftRaiseMotor.setInverted(true);
   }

   public void stopLiftMotors()
   {
      liftSlideMotor.set(0.0);
      liftRaiseMotor.set(0.0);
   }

   public void runLiftMotors(double slide, double raise)
   {
      liftSlideMotor.set(slide);
      liftRaiseMotor.set(raise);
      logger.fine("lift slide motor: " + liftSlideMotor.get());
      logger.fine("lift raise motor:" + liftRaiseMotor.get());
   }
   
}