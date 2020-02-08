package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.CANID;

public class LiftSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private VictorSPX liftSlideMotor = new VictorSPX(CANID.liftSlide);
   private VictorSPX liftRaiseMotor = new VictorSPX(CANID.liftRaise);

   private AnalogInput stringPotHorizontal = new AnalogInput(Constants.AIPorts.stringPotHorizontalPort);
   private AnalogInput stringPotVertical = new AnalogInput(Constants.AIPorts.stringPotVerticalPort);

   //Limits for the string pot to stop the slide before it breaks something
   //Travells 0.0591V V/cm, meaning there are 0.148 Volts (2.5 cm) of decreased speed between the hard and soft stops.
   private final double SPHHardStopRight = 0.09887;
   private final double SPHSoftStopRight = 0.24687;
   private final double SPHSoftStopLeft = 2.167;
   private final double SPHHardStopLeft = 2.315;

   //Same as above, but for the lift
   private final double SPVHardStopDown = 0.295;
   private final double SPVSoftStopDown = 0.443;
   private final double SPVSoftStopUp = 2.302;
   private final double SPVHardStopUp = 2.45;


   public LiftSubsystem()
   {
      logger.fine("entered Lift constructor");
      stopLiftMotors();

      liftRaiseMotor.setInverted(true);
   }

   public void stopLiftMotors()
   {
      liftSlideMotor.set(ControlMode.PercentOutput, 0.0);
      liftRaiseMotor.set(ControlMode.PercentOutput, 0.0);
   }

   public void runSlide(double slide)
   {
      if ((stringPotHorizontal.getVoltage() >= SPHSoftStopRight) && (stringPotHorizontal.getVoltage() <= SPHSoftStopLeft))
      {
         liftSlideMotor.set(ControlMode.PercentOutput, slide);
         logger.fine("lift slide motor percent: " + liftSlideMotor.getMotorOutputPercent());
      }

      else if ((stringPotHorizontal.getVoltage() >= SPHHardStopRight) && (stringPotHorizontal.getVoltage() < SPHSoftStopRight))
      {
         if (slide < 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide/3);
         }

         else if (slide > 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide);
         }
      }

      else if ((stringPotHorizontal.getVoltage() > SPHSoftStopLeft) && (stringPotHorizontal.getVoltage() <= SPHHardStopLeft))
      {
         if (slide > 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide/3);
         }

         else if (slide < 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide);
         }
      }

      else if (stringPotHorizontal.getVoltage() < SPHHardStopRight)
      {
         if (slide >= 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide);
         }

         else if (slide < 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, 0);
         }
      }

      else if (stringPotHorizontal.getVoltage() > SPHHardStopLeft)
      {
         if (slide <= 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide);
         }

         else if (slide > 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, 0);
         }
      }
      
      else 
      {
         liftSlideMotor.set(ControlMode.PercentOutput, 0);
      }

      SmartDashboard.putNumber("String Pot Horizontal:", stringPotHorizontal.getVoltage());
   }

   public void runLift(double raise)
   {
      if ((stringPotVertical.getVoltage() >= SPVSoftStopDown) && (stringPotVertical.getVoltage() <= SPVSoftStopUp))
      {
         liftRaiseMotor.set(ControlMode.PercentOutput, raise);
         logger.fine("lift slide motor percent: " + liftSlideMotor.getMotorOutputPercent());
      }

      else if ((stringPotVertical.getVoltage() >= SPVHardStopDown) && (stringPotVertical.getVoltage() < SPVSoftStopDown))
      {
         if (raise > 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise/2);
         }

         else if (raise < 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise);
         }
      }

      else if ((stringPotVertical.getVoltage() > SPVSoftStopUp) && (stringPotVertical.getVoltage() <= SPVHardStopUp))
      {
         if (raise < 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise/2);
         }

         else if (raise > 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise);
         }
      }

      else if (stringPotVertical.getVoltage() < SPVHardStopDown)
      {
         if (raise <= 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise);
         }

         else if (raise > 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, 0);
         }
      }

      else if (stringPotVertical.getVoltage() > SPVHardStopUp)
      {
         if (raise > 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise);
         }

         else if (raise < 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, 0);
         }
      }
      
      else 
      {
         liftRaiseMotor.set(ControlMode.PercentOutput, 0);
      }

      SmartDashboard.putNumber("String Pot Vertical", stringPotVertical.getVoltage());
   }
}