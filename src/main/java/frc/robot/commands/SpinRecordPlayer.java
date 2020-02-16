/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RecordPlayerSubsystem;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class SpinRecordPlayer extends CommandBase
{
   private final RecordPlayerSubsystem powerRecordPlayer;

   public SpinRecordPlayer(RecordPlayerSubsystem subsystem)
   {
      powerRecordPlayer = subsystem;
      addRequirements(subsystem);
   }


   @Override
   public void initialize()
   {
      powerRecordPlayer.stopMotor();
   }


   @Override
   public void execute()
   {
      double leftTrigger = Robot.m_robotContainer.driver2Controller.getRawAxis(Constants.GamePadAxis.leftTrigger.value);
      double rightTrigger = Robot.m_robotContainer.driver2Controller.getRawAxis(Constants.GamePadAxis.rightTrigger.value);
      double deadband = 0.05;
      double power = 0.0;

      if (leftTrigger >= deadband)
      {
         power = -leftTrigger;
      }
      else if (rightTrigger >= deadband)
      {
         power = rightTrigger;
      }
      else
      {
         power = 0.0;
      }
      
      // Setting motor power proportional to joystick trigger position
      powerRecordPlayer.setRecordPlayerPower(power);
   }


   @Override
   public boolean isFinished()
   {
      return false;
   }
}
