package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallTurretSubsystem;
import frc.robot.Constants.GamePadButtons;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunTurret extends CommandBase
{
   private final BallTurretSubsystem turret;
   private final double maxMotorPower = 0.2;
   private final int countLimitCW = 10050;
   private final int countLimitCCW = -3520;
   private double Kp = 0.05;
   private double Kdamp = 0.005;
   private double xLast = 0.0;
   private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   private static NetworkTableEntry tx = table.getEntry("tx");
   private static NetworkTableEntry tv = table.getEntry("tv");

   // Rotational state of the turret
   private enum State
   {
      in_range,
      at_CCW_limit,
      at_CW_limit;
   }


   // Constructor
   public RunTurret(BallTurretSubsystem subsystem)
   {
      turret = subsystem;
      addRequirements(subsystem);
   }

   
   @Override
   public void execute()
   {
      double x = tx.getDouble(0.0);
      boolean targetFound = tv.getBoolean(false);
      double motorPower = 0.0;
      double dx = 0.0;
      boolean leftBumper = Robot.m_robotContainer.driver2Controller.getRawButton(GamePadButtons.bumperLeft.value);
      boolean rightBumper = Robot.m_robotContainer.driver2Controller.getRawButton(GamePadButtons.bumperRight.value);
      boolean driver1Trigger = Robot.m_robotContainer.driver1Controller.getRawButton(1);

      System.out.println("Target Found:" + targetFound);

      // Rotate the turret based on the bumper buttons. If turret is at the rotational limit, do not allow rotation further that direction
      if (leftBumper && (operatingState() != State.at_CCW_limit))
      {
         // Rotate CCW while held
         motorPower = -maxMotorPower;
      }
      else if (rightBumper && (operatingState() != State.at_CW_limit))
      {
         // Rotate CW while held
         motorPower = maxMotorPower;
      }
      else
      {
         // If not button held, do not rotate
         motorPower = 0.0;
      }

      if (driver1Trigger == true)
      {
         dx = xLast - x;

         if ((operatingState() != State.at_CCW_limit) && (operatingState() != State.at_CW_limit))
         {
            motorPower = (Kp * x) + (Kdamp * dx);
         }
         else
         {
            motorPower = 0.0;
         }

         xLast = x;
      }

      turret.setMotorPower(motorPower);
      turret.displayTurretPosition();
   }

   
   // Limit rotation of the turret based on the absolute encoder position of the subsystem.
   // The desired operation is to allow 180 degrees of rotation.
   // Encoder values need to be determined empirically.
   private State operatingState()
   {
      State state = State.in_range;
      int position = turret.getAbsPosition();

      if (position <= countLimitCCW)
      {
         state = State.at_CCW_limit;
      }
      else if (position >= countLimitCW)
      {
         state = State.at_CW_limit;
      }
      else
      {
         state = State.in_range;
      }

      return state;
   }
}