/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.BallFeederSubsystem;
import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.DribbleSubsystem;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunServo extends CommandBase
{
   private final double dribbleServoPosition;

   public RunServo(DribbleSubsystem dribbleMode)
   {
      dribbleMode.runDribbleServo(dribbleServoPosition);
      dribbleServoPosition = Robot.m_robotContainer.driver1Controller.getRawAxis(Constants.LogitechProAxis.YAxis.value);
   }

   @Override
   public void initialize()
   {
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
