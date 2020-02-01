package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorPorts;

public class LiftSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private VictorSP liftSlideMotor = new VictorSP(MotorPorts.liftSlideMotorPort);
   private VictorSP liftRaiseMotor = new VictorSP(MotorPorts.liftRaiseMotorPort);

   private AnalogInput stringPotHorizontal = new AnalogInput(Constants.AIPorts.stringPotHorizontalPort);
   private AnalogInput stringPotVertical = new AnalogInput(Constants.AIPorts.stringPotVerticalPort);

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

   public void runLiftMotors(double raise)
   {

      liftRaiseMotor.set(raise);

      logger.fine("lift raise motor:" + liftRaiseMotor.get());
   }

   public void runStringPotHorizontal(double slide)
   {
      if ((stringPotHorizontal.getVoltage() >= 0.4) && (stringPotHorizontal.getVoltage() <= 2.0))
      {
         liftSlideMotor.set(slide);
         logger.fine("lift slide motor: " + liftSlideMotor.get());
      }

      else if ((stringPotHorizontal.getVoltage() >= 0.3) && (stringPotHorizontal.getVoltage() < 0.4))
      {
         //liftSlideMotor.set(10*(stringPotHorizontal.getVoltage()-0.3));
         
      }

      else if ((stringPotHorizontal.getVoltage() > 2.0) && (stringPotHorizontal.getVoltage() <= 2.1))
      {
         //liftSlideMotor.set(10*(2.1 - stringPotHorizontal.getVoltage()));
      }

      else if (stringPotHorizontal.getVoltage() < 0.3)
      {
         if (slide >= 0)
         {
            liftSlideMotor.set(slide);
         }

         else if (slide < 0)
         {
            liftSlideMotor.set(0);
         }
      }

      else if (stringPotHorizontal.getVoltage() > 2.1)
      {
         if (slide <= 0)
         {
            liftSlideMotor.set(slide);
         }

         else if (slide > 0)
         {
            liftSlideMotor.set(0);
         }
      }
      
      else 
      {
         liftSlideMotor.set(0);
      }

      SmartDashboard.putNumber("String Pot:", stringPotHorizontal.getVoltage());
   }

   // public void runStringPotVertical(double raise)
   // {
   //    if((stringPotVertical.getVoltage() >= 0.1) && (stringPotVertical.getVoltage() <= 4.0))
   //    {
   //       liftRaiseMotor.set(raise);
   //    }

   //    else
   //    {
   //       liftRaiseMotor.set(0);
   //    }
   // }
   
}