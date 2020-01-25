package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.util.logging.Logger;

public class DriveTrainSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private VictorSP leftFrontDriveMotor = new VictorSP(MotorPorts.leftFrontDriveMotorPort);
   private VictorSP leftRearDriveMotor = new VictorSP(MotorPorts.leftRearDriveMotorPort);
   SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(leftFrontDriveMotor, leftRearDriveMotor);

   private VictorSP rightFrontDriveMotor = new VictorSP(MotorPorts.rightFrontDriveMotorPort);
   private VictorSP rightRearDriveMotor = new VictorSP(MotorPorts.rightRearDriveMotorPort);
   SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(rightFrontDriveMotor, rightRearDriveMotor);

   public final DifferentialDrive driveBase = new DifferentialDrive(leftDriveMotors, rightDriveMotors);

   public DriveTrainSubsystem()
   {
      // Constructor
      logger.fine("entered Drivetrain constructor");
      stopDriveMotors();
   }

   public void stopDriveMotors()
   {
      leftDriveMotors.set(0.0);
      rightDriveMotors.set(0.0);
   }


   /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
   public void driveByArcade(double fwd, double rot)
   {
      driveBase.arcadeDrive(fwd, rot);
      logger.fine("leftdrive actual: " + leftDriveMotors.get());
      logger.fine("rightdrive actual: " + rightDriveMotors.get());
   }

}