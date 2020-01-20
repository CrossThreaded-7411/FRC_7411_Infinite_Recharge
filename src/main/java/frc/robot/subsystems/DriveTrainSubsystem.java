package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class DriveTrainSubsystem extends SubsystemBase
{
   private VictorSP leftFrontDriveMotor = new VictorSP(MotorPorts.leftFrontDriveMotorPort);
   private VictorSP leftRearDriveMotor = new VictorSP(MotorPorts.leftRearDriveMotorPort);
   SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(leftFrontDriveMotor, leftRearDriveMotor);

   private VictorSP rightFrontDriveMotor = new VictorSP(MotorPorts.rightFrontDriveMotorPort);
   private VictorSP rightRearDriveMotor = new VictorSP(MotorPorts.rightRearDriveMotorPort);
   SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(rightFrontDriveMotor, rightRearDriveMotor);

   public DriveTrainSubsystem()
   {
      // Constructor
      stopDriveMotors();
      leftDriveMotors.setInverted(true);
   }

   public void stopDriveMotors()
   {
      leftDriveMotors.set(0.0);
      rightDriveMotors.set(0.0);
   }

   public void runDriveMotorsStraight(double driveSpeed)
   {
      leftDriveMotors.set(driveSpeed);
      rightDriveMotors.set(driveSpeed);
   }

   public void runDriveMotorsTurn(double turnSpeed)
   {
      leftDriveMotors.set(-turnSpeed);
      rightDriveMotors.set(turnSpeed);
   }
}