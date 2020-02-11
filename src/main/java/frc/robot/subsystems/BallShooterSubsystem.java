/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;

public class BallShooterSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   private Spark launchMotorBottom = new Spark(CANID.ballShooterBottom);
   private Spark launchMotorTop = new Spark(CANID.ballShooterTop);

   /**
    * Creates a new DriveSubsystem.
    */
   public BallShooterSubsystem()
   {
      // Constructor
      stopMotors();
      launchMotorTop.setInverted(true);
      logger.fine("Launch motor upper direction inverted = " + launchMotorTop.getInverted());

      logger.finer("Launch subsystem constructor complete");
   }

   /**
    * Sets motor power for the ball launcher motor
    * 
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double targetPowerLower, double targetPowerUpper)
   {
      launchMotorBottom.set(targetPowerLower);
      launchMotorBottom.set(targetPowerUpper);
      logger.info("Launch motor lower = " + launchMotorBottom.get());
      logger.info("Launch motor upper = " + launchMotorTop.get());
   }

   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotors()
   {
      double stopPower = 0.0;
      launchMotorBottom.set(stopPower);
      launchMotorTop.set(stopPower);
      logger.info("Launch motor lower = " + launchMotorBottom.get());
      logger.info("Launch motor upper = " + launchMotorTop.get());
   }
}
