/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EnergyLaunchSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private VictorSP motor = new VictorSP(0);

   /**
    * Creates a new DriveSubsystem.
    */
   public EnergyLaunchSubsystem()
   {
      // Constructor
      stopMotor();
   }

   /**
    * Sets motor power for the ball launcher motor
    * 
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double targetPower)
   {
      motor.set(targetPower);
      logger.info("Launch motor power set to " + targetPower);
   }

   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotor()
   {
      motor.set(0.0);
      logger.info("Launch motor stopped");
   }
}
