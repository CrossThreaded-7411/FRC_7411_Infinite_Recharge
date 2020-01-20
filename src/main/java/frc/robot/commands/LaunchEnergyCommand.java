/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.EnergyLaunchSubsystem;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class LaunchEnergyCommand extends CommandBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private final EnergyLaunchSubsystem m_energyLauncher;
   private final double m_motorPowerLower;
   private final double m_motorPowerUpper;

   public LaunchEnergyCommand(EnergyLaunchSubsystem subsystem, double motorPowerLower, double motorPowerUpper)
   {
      m_energyLauncher = subsystem;
      m_motorPowerLower = motorPowerLower;
      m_motorPowerUpper = motorPowerUpper;
      addRequirements(subsystem);

      logger.finer("Launcher command constructor complete");
   }

   @Override
   public void initialize()
   {
      logger.fine("Launcher command requested motor power lower at " + m_motorPowerLower);
      logger.fine("Launcher command requested motor power upper at " + m_motorPowerUpper);
      m_energyLauncher.setMotorPower(m_motorPowerLower, m_motorPowerUpper);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}
