/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.subsystems;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.robot.Constants.pdpPorts;

public class PDPSubsystem extends SubsystemBase
{
   private PowerDistributionPanel pdp = new PowerDistributionPanel();
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


   //constructor
   public PDPSubsystem()
   {
   
   }


   // Sets motor power for the Record Player Spinner motor
   public void readCurrentValues()
   {
      double liftRaiseCurrent= pdp.getCurrent(pdpPorts.ballFeederPort);
      double ballShooterTopCurrent = pdp.getCurrent(pdpPorts.ballShooterTopPort);
      double ballShooterBottomCurrent = pdp.getCurrent(pdpPorts.ballShooterBottomPort);
      double ballShooterTurretCurrent = pdp.getCurrent(pdpPorts.ballShooterTurretPort);
      double recordPlayerCurrent = pdp.getCurrent(pdpPorts.recordPlayerPort);
      double liftSlideMotorCurrent = pdp.getCurrent(pdpPorts.liftSlideMotorPort);
      double liftRaiseMotorCurrent = pdp.getCurrent(pdpPorts.liftRaiseMotorPort);
      double pdpCurrent1 = pdp.getCurrent(pdpPorts.pdpPort1);
      double pdpCurrent2 = pdp.getCurrent(pdpPorts.pdpPort2);
      double pdpCurrent3 = pdp.getCurrent(pdpPorts.pdpPort3);
      double pdpCurrent4 = pdp.getCurrent(pdpPorts.pdpPort4);
      double ballCollectorCurrent = pdp.getCurrent(pdpPorts.ballCollectorPort);
      double driveTrainRRCurrent = pdp.getCurrent(pdpPorts.driveTrainRRPort);
      double driveTrainRFCurrent = pdp.getCurrent(pdpPorts.driveTrainRFPort);
      double driveTrainLFCurrent = pdp.getCurrent(pdpPorts.driveTrainLFPort);
      double driveTrainLRCurrent = pdp.getCurrent(pdpPorts.driveTrainLRPort);

      logger.info("Battery_Voltage: " + pdp.getVoltage());
      logger.info("Raise_Current: " + liftRaiseCurrent);
      logger.info("Shooter_Top: " + ballShooterTopCurrent);
      logger.info("Shooter_Bottom: " + ballShooterBottomCurrent);
      logger.info("Turret: " + ballShooterTurretCurrent);
      logger.info("Record_Player: " + recordPlayerCurrent);
      logger.info("Lift_Slide: " + liftSlideMotorCurrent);
      logger.info("Lift_Raise: " + liftRaiseMotorCurrent);
      logger.info("Collector: " + ballCollectorCurrent);
      logger.info("Drivetrain_RR: " + driveTrainRRCurrent);
      logger.info("Drivetrain_RF: " + driveTrainRFCurrent);
      logger.info("Drivettrain_LF: " + driveTrainLFCurrent);
      logger.info("Drivetrain_LR: " + driveTrainLRCurrent);
   }
}