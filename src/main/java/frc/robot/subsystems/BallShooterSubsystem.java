/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.Spark;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.CANID;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallShooterSubsystem extends SubsystemBase
{
   // private Spark launchMotorBottom = new Spark(CANID.ballShooterBottom);
   // private Spark launchMotorTop = new Spark(CANID.ballShooterTop);
   private CANSparkMax launchMotorBottom = new CANSparkMax(CANID.ballShooterBottom, MotorType.kBrushless);
   private CANSparkMax launchMotorTop = new CANSparkMax(CANID.ballShooterTop, MotorType.kBrushless);

   private Servo dribbleServo = new Servo(Constants.MotorPorts.dribbleServoPort);

   /**
    * Creates a new DriveSubsystem.
    */
   public BallShooterSubsystem()
   {
      // Constructor
      stopMotors();
      launchMotorTop.setInverted(true);
   }

   /**
    * Sets motor power for the ball launcher motor
    * 
    * @param targetPower Accepts a power level between -1 to 1
    */
   public void setMotorPower(double targetPowerLower, double targetPowerUpper)
   {
      launchMotorBottom.set(targetPowerLower);
      launchMotorTop.set(targetPowerUpper);
   }

   /**
    * Stops the motor by setting power to 0.0
    */
   public void stopMotors()
   {
      double stopPower = 0.0;
      launchMotorBottom.set(stopPower);
      launchMotorTop.set(stopPower);
   }

   public void runServo(double dribblePosition)
   {
      dribbleServo.set(dribblePosition);
      SmartDashboard.putNumber("dribbleServoPosition: ", dribbleServo.get());
   }
}
