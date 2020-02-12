/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RecordPlayerSubsystem;
import frc.robot.Robot;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class SpinRecordPlayer extends CommandBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private final RecordPlayerSubsystem powerRecordPlayer;
   private final double m_motorPower;
   public SpinRecordPlayer(RecordPlayerSubsystem subsystem, double motorPower)
   {
      powerRecordPlayer = subsystem;
      m_motorPower = motorPower;
      addRequirements(subsystem);

      logger.finer("Launcher command constructor complete");

      powerRecordPlayer.setRecordPlayerPower(m_motorPower);
   }

   @Override
   public void initialize()
   {
      logger.fine("Launcher command requested motor power lower at " + m_motorPower);
      powerRecordPlayer.setRecordPlayerPower(m_motorPower);
   }

   @Override
   public boolean isFinished()
   {
      return true;
   }
}  