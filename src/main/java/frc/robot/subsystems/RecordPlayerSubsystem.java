/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class RecordPlayerSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private TalonSRX recordPlayerMotor = new TalonSRX(Constants.CANID.recordPlayer);

   /**
    * Creates a new DriveSubsystem.
    */
   public RecordPlayerSubsystem()
   {
      // Constructor
      stopMotor();
      logger.finer("Launch subsystem constructor complete");
   }

   /**
    * Sets motor power for the Record Player Spinner motor
    * 
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double power)
   {
      recordPlayerMotor.set(ControlMode.PercentOutput, power);
   }

   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotor()
   {
      double stopMotorPower = 0.0;
      recordPlayerMotor.set(ControlMode.PercentOutput, stopMotorPower);
   }
}