package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallTurretSubsystem;
import frc.robot.Constants.*;


/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunTurret extends CommandBase
{
   private final BallTurretSubsystem turret;
   private final double maxMotorPower = 0.07;


   public RunTurret(BallTurretSubsystem subsystem)
   {
      turret = subsystem;
      addRequirements(subsystem);
   }

   
   @Override
   public void execute()
   {
      double motorPower = 0.0;
      boolean leftBumper = Robot.m_robotContainer.driver2Joystick.getRawButton(GamePadButtons.bumperLeft.value);
      boolean rightBumper = Robot.m_robotContainer.driver2Joystick.getRawButton(GamePadButtons.bumperRight.value);

      if (leftBumper)
      {
         motorPower = -maxMotorPower;
      }
      else if (rightBumper)
      {
         motorPower = maxMotorPower;
      }
      else
      {
         motorPower = 0.0;
      }
      
      turret.setMotorPower(motorPower);
      turret.displayTurretPosition();
   }  
}