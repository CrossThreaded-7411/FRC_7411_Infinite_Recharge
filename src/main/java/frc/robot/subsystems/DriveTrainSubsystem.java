package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANID;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.util.logging.Logger;

public class DriveTrainSubsystem extends SubsystemBase
{
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

   private CANSparkMax leftFrontDriveMotor = new CANSparkMax(CANID.driveTrainLF,MotorType.kBrushless);
   private CANSparkMax leftRearDriveMotor = new CANSparkMax(CANID.driveTrainLR,MotorType.kBrushless);
   SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(leftFrontDriveMotor, leftRearDriveMotor);

   private CANSparkMax rightFrontDriveMotor = new CANSparkMax(CANID.driveTrainRF,MotorType.kBrushless);
   private CANSparkMax rightRearDriveMotor = new CANSparkMax(CANID.driveTrainRR,MotorType.kBrushless);
   SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(rightFrontDriveMotor, rightRearDriveMotor);

   public final DifferentialDrive driveBase = new DifferentialDrive(leftDriveMotors, rightDriveMotors);

   public DriveTrainSubsystem()
   {
      // Constructor
      logger.fine("entered Drivetrain constructor");
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