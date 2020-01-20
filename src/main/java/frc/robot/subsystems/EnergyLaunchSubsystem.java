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
import frc.robot.Constants.MotorPorts;

public class EnergyLaunchSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private VictorSP launchMotorLower = new VictorSP(MotorPorts.launchMotorLowerPort);
   private VictorSP launchMotorUpper = new VictorSP(MotorPorts.launchMotorUpperPort);

   /**
    * Creates a new DriveSubsystem.
    */
   public EnergyLaunchSubsystem()
   {
      // Constructor
      stopMotors();
      launchMotorUpper.setInverted(true);
      logger.fine("Launch motor upper direction inverted = " + launchMotorUpper.getInverted());

      logger.finer("Launch subsystem constructor complete");
   }

   /**
    * Sets motor power for the ball launcher motor
    * 
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double targetPowerLower, double targetPowerUpper)
   {
      launchMotorLower.set(targetPowerLower);
      launchMotorUpper.set(targetPowerUpper);
      logger.info("Launch motor lower = " + launchMotorLower.get());
      logger.info("Launch motor upper = " + launchMotorUpper.get());
   }

   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotors()
   {
      double stopPower = 0.0;
      launchMotorLower.set(stopPower);
      launchMotorUpper.set(stopPower);
      logger.info("Launch motor lower = " + launchMotorLower.get());
      logger.info("Launch motor upper = " + launchMotorUpper.get());
   }
}
