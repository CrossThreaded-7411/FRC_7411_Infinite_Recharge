/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallShooterSubsystem;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunBallShooter extends CommandBase
{
   private final BallShooterSubsystem ballShooter;
   private final double m_motorPowerBottom;
   private final double m_motorPowerTop;

   public RunBallShooter(BallShooterSubsystem shooterSubsystem, double shooterBottomPower, double shooterTopPower)
   {
      ballShooter = shooterSubsystem;
      m_motorPowerBottom = shooterBottomPower;
      m_motorPowerTop = shooterTopPower;
      addRequirements(shooterSubsystem);
   }

   @Override
   public void initialize()
   {
      ballShooter.setMotorPower(m_motorPowerBottom, m_motorPowerTop);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
