/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import java.util.logging.Logger;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrainSubsystem extends SubsystemBase
{
   private CANSparkMax leftFrontDriveMotor = new CANSparkMax(CANID.driveTrainLF,MotorType.kBrushless);
   private CANSparkMax leftRearDriveMotor = new CANSparkMax(CANID.driveTrainLR,MotorType.kBrushless);
   SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(leftFrontDriveMotor, leftRearDriveMotor);

   private CANSparkMax rightFrontDriveMotor = new CANSparkMax(CANID.driveTrainRF,MotorType.kBrushless);
   private CANSparkMax rightRearDriveMotor = new CANSparkMax(CANID.driveTrainRR,MotorType.kBrushless);
   SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(rightFrontDriveMotor, rightRearDriveMotor);

   public final DifferentialDrive driveBase = new DifferentialDrive(leftDriveMotors, rightDriveMotors);
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


   // Constructor
   public DriveTrainSubsystem()
   {
      stopDriveMotors();
      rightDriveMotors.setInverted(true);
      leftDriveMotors.setInverted(true);
   }


   public void stopDriveMotors()
   {
      leftDriveMotors.set(0.0);
      rightDriveMotors.set(0.0);
   }


   /**
   * Drives the robot using arcade controls.
   */
   public void driveByArcade(double fwd, double rot)
   {
      driveBase.arcadeDrive(fwd, rot);
   }


   public void logDriveTrainData()
   {
      logger.finer("Drivetrain_Left_Power: " + leftDriveMotors.get());
      logger.finer("Drivetrain_Right_Power: " + rightDriveMotors.get());
   }

}