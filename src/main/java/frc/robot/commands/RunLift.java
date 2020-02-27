/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.Constants;


public class RunLift extends CommandBase
{
   private final LiftSubsystem lift;
   private double slide;
   private double raise;
   private double deadband;
   private double maxPower;

   public RunLift(LiftSubsystem subsystem)
   {
      lift = subsystem;
      addRequirements(subsystem);
      setDeadband(0.05);
      setMaxPower(1.0);
   }

   @Override
   public void execute()
   {
      raise = Robot.m_robotContainer.driver2Controller.getRawAxis(Constants.GamePadAxis.rightStickY.value);
      slide = Robot.m_robotContainer.driver2Controller.getRawAxis(Constants.GamePadAxis.leftStickX.value);

      lift.runLift(raise);
      lift.runSlide(-slide);
   }


   /**
    * Limits the max power and applies deadband to the motor controllor input
    */
   private double rangeLimitedPower(double rawCommand)
   {
      double newCommand;

      // Apply simple deadband
      if (Math.abs(rawCommand) < deadband)
      {
         newCommand = 0.0;
      }
      else
      {
         // Apply max power limit
         newCommand = rawCommand * maxPower;
      }

      return (newCommand);
   }


   /**
    * Method sets deadband for joystick axis
    */
   private void setDeadband(double deadbandValue)
   {
      if (Math.abs(deadbandValue) <= 1.0)
      {
         deadband = deadbandValue;
      }
      else
      {
         deadband = 1.0;
      }
   }


   /**
    * Method limits max power sent to motor controller
    */
   private void setMaxPower(double maxPowerValue)
   {
      if (Math.abs(maxPowerValue) <= 1.0)
      {
         maxPower = maxPowerValue;
      }
      else
      {
         maxPower = 1.0;
      }
   }

}
