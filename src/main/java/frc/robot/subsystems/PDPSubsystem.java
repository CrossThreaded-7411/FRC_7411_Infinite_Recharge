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
         logger.finer("Battery_volts: " + pdp.getVoltage());
         logger.finer("Raise_amps: " + pdp.getCurrent(pdpPorts.liftRaisePort));
         logger.finer("Slide_amps: " + pdp.getCurrent(pdpPorts.liftSlideMotorPort));
         logger.finer("Shoot_Top_amps: " + pdp.getCurrent(pdpPorts.ballShooterTopPort));
         logger.finer("Shoot_Bot_amps: " + pdp.getCurrent(pdpPorts.ballShooterBottomPort));
         logger.finer("Turret_amps: " + pdp.getCurrent(pdpPorts.ballTurretPort));
         logger.finer("Record_Play_amps: " + pdp.getCurrent(pdpPorts.recordPlayerPort));
         logger.finer("Collector_amps: " + pdp.getCurrent(pdpPorts.ballCollectorPort));
         logger.finer("Feeder_amps: " + pdp.getCurrent(pdpPorts.ballFeederPort));
         logger.finer("DT_RR_amps: " + pdp.getCurrent(pdpPorts.driveTrainRRPort));
         logger.finer("DT_RF_amps: " + pdp.getCurrent(pdpPorts.driveTrainRFPort));
         logger.finer("DT_LF_amps: " + pdp.getCurrent(pdpPorts.driveTrainLFPort));
         logger.finer("DT_LR_amps: " + pdp.getCurrent(pdpPorts.driveTrainLRPort));
      }
   }
}