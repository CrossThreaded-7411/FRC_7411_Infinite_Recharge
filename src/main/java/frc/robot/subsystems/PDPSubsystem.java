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
      double ch1 = pdp.getCurrent(pdpPorts.ballShooterTopPort);
      double ch2 = pdp.getCurrent(pdpPorts.ballShooterBottomPort);
      double ch3 = pdp.getCurrent(pdpPorts.ballShooterTurretPort);
      double ch4 = pdp.getCurrent(pdpPorts.recordPlayerPort);
      double ch5 = pdp.getCurrent(pdpPorts.liftSlideMotorPort);
      double ch6 = pdp.getCurrent(pdpPorts.liftRaiseMotorPort);
      double ch7 = pdp.getCurrent(pdpPorts.pdpPort1);
      double ch8 = pdp.getCurrent(pdpPorts.pdpPort2);
      double ch9 = pdp.getCurrent(pdpPorts.pdpPort3);
      double ch10 = pdp.getCurrent(pdpPorts.pdpPort4);
      double ch11 = pdp.getCurrent(pdpPorts.ballCollectorPort);
      double ch12 = pdp.getCurrent(pdpPorts.driveTrainRRPort);
      double ch13 = pdp.getCurrent(pdpPorts.driveTrainRFPort);
      double ch14 = pdp.getCurrent(pdpPorts.driveTrainLFPort);
      double ch15 = pdp.getCurrent(pdpPorts.driveTrainLRPort);

      logger.info("Raise Current: " + liftRaiseCurrent);
   }
}