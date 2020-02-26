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


   // Log data to file
   public void logPDPData(boolean enabled)
   {
      if (enabled)
      {
         logger.finer("Battery_Voltage: " + pdp.getVoltage());
         logger.finer("Lift_Raise_Current: " + pdp.getCurrent(pdpPorts.liftRaisePort));
         logger.finer("Lift_Slide_Current: " + pdp.getCurrent(pdpPorts.liftSlideMotorPort));
         logger.finer("Shooter_Top_Current: " + pdp.getCurrent(pdpPorts.ballShooterTopPort));
         logger.finer("Shooter_Bottom_Current: " + pdp.getCurrent(pdpPorts.ballShooterBottomPort));
         logger.finer("Shooter_Turret_Current: " + pdp.getCurrent(pdpPorts.ballShooterTurretPort));
         logger.finer("Record_Player_Current: " + pdp.getCurrent(pdpPorts.recordPlayerPort));
         logger.finer("Ball_Collector_Current: " + pdp.getCurrent(pdpPorts.ballCollectorPort));
         logger.finer("Ball_Feeder_Current: " + pdp.getCurrent(pdpPorts.ballFeederPort));
         logger.finer("Drivetrain_RR_Current: " + pdp.getCurrent(pdpPorts.driveTrainRRPort));
         logger.finer("Drivetrain_RF_Current: " + pdp.getCurrent(pdpPorts.driveTrainRFPort));
         logger.finer("Drivettrain_LF_Current: " + pdp.getCurrent(pdpPorts.driveTrainLFPort));
         logger.finer("Drivetrain_LR_Current: " + pdp.getCurrent(pdpPorts.driveTrainLRPort));
      }
   }
}