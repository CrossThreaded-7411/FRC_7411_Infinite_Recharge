/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class EnergyLaunchSubsystem extends SubsystemBase
{

   private VictorSP launchMotor1 = new VictorSP(MotorPorts.launchMotor1Port);
   private VictorSP launchMotor2 = new VictorSP(MotorPorts.launchMotor2Port);

   /**
    * Creates a new DriveSubsystem.
    */
   public EnergyLaunchSubsystem()
   {
      // Constructor
      stopMotors();
      launchMotor2.setInverted(true);
   }

   /**
    * Sets motor power for the ball launcher motor
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double targetPowerLower, double targetPowerUpper)
   {
      launchMotor1.set(targetPowerLower);
      launchMotor2.set(targetPowerUpper);
   }

  /**
   * Stops the motor by setting power to 0.0
   */
  public void stopMotors()
  {
    launchMotor1.set(0.0);
    launchMotor2.set(0.0);
  }
}
