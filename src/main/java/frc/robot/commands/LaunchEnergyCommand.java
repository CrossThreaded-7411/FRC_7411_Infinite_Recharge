/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.EnergyLaunchSubsystem;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class LaunchEnergyCommand extends CommandBase
{
   private final EnergyLaunchSubsystem m_energyLauncher;
   private final double m_motorPowerLower;
   private final double m_moterPowerUpper;

   public LaunchEnergyCommand(EnergyLaunchSubsystem subsystem, double motorPowerLower, double motorPowerUpper)
   {
      m_energyLauncher = subsystem;
      m_motorPowerLower = motorPowerLower;
      m_moterPowerUpper = motorPowerUpper;
      addRequirements(subsystem);
   }

   @Override
   public void initialize()
   {
      m_energyLauncher.setMotorPower(m_motorPowerLower, m_moterPowerUpper);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
