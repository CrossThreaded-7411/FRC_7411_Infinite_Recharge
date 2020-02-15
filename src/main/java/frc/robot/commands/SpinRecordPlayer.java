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
import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class SpinRecordPlayer extends CommandBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private final RecordPlayerSubsystem powerRecordPlayer;
   private final RobotContainer m_robotContainer;
   private final double m_motorPower;

   public SpinRecordPlayer(RecordPlayerSubsystem subsystem)
   {
      powerRecordPlayer = subsystem;
      addRequirements(subsystem);

      logger.finer("Launcher command constructor complete");

      powerRecordPlayer.setRecordPlayerPower(m_motorPower);

      if ((Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.leftTrigger.value) > 0.05) && (Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.leftTrigger.value) <= 1.0))
      {
        m_motorPower = Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.leftTrigger.value);
      }
  
      else if ((Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.rightTrigger.value) > 0.05) && (Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.rightTrigger.value) <= 1.0) && (Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.leftTrigger.value) < 0.05))
      {
        m_motorPower = -Robot.m_robotContainer.driver1Joystick.getRawAxis(Constants.GamePadAxis.rightTrigger.value);
      }
  
      else
      {
        m_motorPower = 0.0;
      }
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