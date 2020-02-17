/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallFeederSubsystem;
import frc.robot.subsystems.BallShooterSubsystem;
/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunServo extends CommandBase
{
   private final BallShooterSubsystem ballShooter;
   private final double m_dribblePosition;

   public RunServo(BallShooterSubsystem shooterSubsystem, double dribbleServoPosition)
   {
      ballShooter = shooterSubsystem;
      m_dribblePosition = dribbleServoPosition;
      addRequirements(shooterSubsystem);
   }

   @Override
   public void initialize()
   {
      ballShooter.runServo(m_dribblePosition);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
