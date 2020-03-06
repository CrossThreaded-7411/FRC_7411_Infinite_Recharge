/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants.CANID;

public class LiftSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private TalonSRX liftSlideMotor = new TalonSRX(CANID.liftSlide);
   private VictorSPX liftRaiseMotor = new VictorSPX(CANID.liftRaise);

   private AnalogInput stringPotHorizontal =
         new AnalogInput(Constants.AIPorts.stringPotHorizontalPort);
   private AnalogInput stringPotVertical = new AnalogInput(Constants.AIPorts.stringPotVerticalPort);

   // Limits for the string pot to stop the slide before it breaks something
   // Travells 0.0591V V/cm, meaning there are 0.148 Volts (2.5 cm) of decreased speed between the
   // hard and soft stops.
   private final double SPHHardStopRight = 0.09887;
   private final double SPHHardStopLeft = 2.315;
   private final double SPHSoftStopRight = 0.24687;
   private final double SPHSoftStopLeft = 2.167;

   // Same as above, but for the lift
   private final double SPVHardStopDown = 0.235;
   private final double SPVHardStopUp = 2.45;
   private final double SPVSoftStopDown = 0.325;
   private final double SPVSoftStopUp = 2.302;


   public LiftSubsystem()
   {
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
      liftSlideMotor.set(ControlMode.PercentOutput, slide);
      if ((stringPotHorizontal.getVoltage() >= SPHSoftStopRight) && (stringPotHorizontal.getVoltage() <= SPHSoftStopLeft))
      {
         liftSlideMotor.set(ControlMode.PercentOutput, slide);
      }

      else if ((stringPotHorizontal.getVoltage() >= SPHHardStopRight) && (stringPotHorizontal.getVoltage() < SPHSoftStopRight))
      {
         if (slide < 0)
         {
            liftSlideMotor.set(ControlMode.PercentOutput, slide / 3);
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
            liftSlideMotor.set(ControlMode.PercentOutput, slide / 3);
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
      }

      else if ((stringPotVertical.getVoltage() >= SPVHardStopDown) && (stringPotVertical.getVoltage() < SPVSoftStopDown))
      {
         if (raise > 0)
         {
            liftRaiseMotor.set(ControlMode.PercentOutput, raise / 2);
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
            liftRaiseMotor.set(ControlMode.PercentOutput, raise / 2);
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


   // Log data to file
   public void logLiftData(boolean enabled)
   {
      if (enabled)
      {
         logger.finer("Raise_Sensor_volts: " + stringPotVertical.getVoltage());
         logger.finer("Slide_Sensor_volts: " + stringPotHorizontal.getVoltage());

         // Divide by 100 to convert percent to ratio to be consistant with non-CTRE motor libraries
         logger.finer("Raise_Power: " + liftRaiseMotor.getMotorOutputPercent() / 100.0);
         logger.finer("Slide_Power: " + liftSlideMotor.getMotorOutputPercent() / 100.0);
      }
   }
}
