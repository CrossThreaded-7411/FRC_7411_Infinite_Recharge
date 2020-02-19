/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DribbleSubsystem;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunServo extends CommandBase
{
   private final DribbleSubsystem dribbleMode;
   private double servoPos;

   public RunServo(DribbleSubsystem subsystem, double dribbleServoPosition)
   {
      servoPos = dribbleServoPosition;
      dribbleMode = subsystem;
      addRequirements(subsystem);
   }

   @Override
   public void execute()
   {
      dribbleMode.runDribbleServo(servoPos);
      SmartDashboard.putNumber("ServoPosition: ", dribbleMode.dribbleServo.get());
   }

   @Override
   public boolean isFinished()
   {
      return false;
   }
}
