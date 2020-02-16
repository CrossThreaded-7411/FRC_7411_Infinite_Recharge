/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallShooterSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunBallShooter extends CommandBase
{
   private final BallShooterSubsystem ballShooter;
   private final BallFeederSubsystem ballFeeder;
   private final double m_motorPowerBottom;
   private final double m_motorPowerTop;
   private final double m_feederPower;

   public RunBallShooter(BallShooterSubsystem shooterSubsystem, BallFeederSubsystem feederSubsystem, double shooterBottomPower, double shooterTopPower, double feederPower)
   {
      ballShooter = shooterSubsystem;
      ballFeeder = feederSubsystem;
      m_motorPowerBottom = shooterBottomPower;
      m_motorPowerTop = shooterTopPower;
      m_feederPower = feederPower;
      addRequirements(shooterSubsystem, feederSubsystem);
   }

   @Override
   public void initialize()
   {
      ballShooter.setMotorPower(m_motorPowerBottom, m_motorPowerTop);
      ballFeeder.setMotorPower(m_feederPower);
      System.out.println("Power Top: " + m_motorPowerTop);
      System.out.println("Power Bot: " + m_motorPowerBottom);

   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
