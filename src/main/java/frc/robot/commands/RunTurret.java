/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallTurretSubsystem;
import frc.robot.Constants.GamePadButtons;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command to turn on the launch motor to a known desired power
 */
public class RunTurret extends CommandBase {
    private final BallTurretSubsystem turret;
    private final double maxMotorPower = 0.2;
    private final int countLimitCW = 10050;
    private final int countLimitCCW = -3520;
    private double Kp = 0.05;
    private double Kdamp = 0.005;
    private double xLast = 0.0;
    private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    // private static NetworkTables table
    private static NetworkTableEntry tx = table.getEntry("tx");
    private static NetworkTableEntry tv = table.getEntry("tv");
    private static NetworkTableEntry tshort = table.getEntry("tshort");
    private static NetworkTableEntry tlong = table.getEntry("tlong");
    private static boolean targetingOn = false;
    private static boolean turretReturnZero = false;
    private static boolean turretLedOn = false;

    public static void setTargeting(boolean state)
    {
        targetingOn = state;
    }

    public static void setReturnToZero(boolean state)
    {
        turretReturnZero = state;
    }

    public static void setTurretLedOn(boolean state) 
    {
        turretLedOn = state;
    }

    // Rotational state of the turret
    // private enum State
    // {
    // in_range,
    // at_CCW_limit,
    // at_CW_limit;
    // }

    // Constructor
    public RunTurret(BallTurretSubsystem subsystem) {
        turret = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        double x = tx.getDouble(0.0);
        double sideShort = tshort.getDouble(0.0);
        double sideLong = tlong.getDouble(0.0);
        double area = sideShort * sideLong;
        boolean targetFound = tv.getBoolean(false);
        double motorPower = 0.0;
        double dx = 0.0;
        boolean leftBumper = Robot.m_robotContainer.driver2Controller.getRawButton(GamePadButtons.bumperLeft.value);
        boolean rightBumper = Robot.m_robotContainer.driver2Controller.getRawButton(GamePadButtons.bumperRight.value);
        boolean driver1Trigger = Robot.m_robotContainer.driver1Controller.getRawButton(1);

        SmartDashboard.putNumber("LimelightArea", area);

        // System.out.println("Target Found:" + targetFound);

        // Rotate the turret based on the bumper buttons. If turret is at the rotational
        // limit, do not
        // allow rotation further that direction
        if (leftBumper) {
            // Rotate CCW while held
            motorPower = -maxMotorPower;
        } else if (rightBumper) {
            // Rotate CW while held
            motorPower = maxMotorPower;
        } else {
            // If not button held, do not rotate
            motorPower = 0.0;
        }

        // Closed-loop control of turret using LimeLight cammera feedback
        // Intent is to aquire and control to target only while holding a button
        if (driver1Trigger == true || targetingOn) 
        {
            setTurretLedOn(true);
            // Calculates rate of change for the purpose of adding damping
            dx = xLast - x;
            motorPower = (Kp * x) + (Kdamp * dx);
            xLast = x;
        }
        else
        {
            setTurretLedOn(false);
        }

        // returns turret to zero position
        if (turretReturnZero)
        {
            double x_home = turret.getRelativePosition();
            // Calculates rate of change for the purpose of adding damping
            dx = xLast - x_home;
            motorPower = (-Kp * 0.01 * x_home) + (Kdamp * 0.01 * dx);
            xLast = x_home;
            // System.out.println("motor output" + motorPower);
        }

        if (turretLedOn)
        {
            table.getEntry("ledMode").setNumber(3);
            // System.out.println("LED on");
        }
        else 
        {
            table.getEntry("ledMode").setNumber(1);
        }

        SmartDashboard.putNumber("TurretCount:", turret.getRelativePosition());
        turret.setMotorPower(motorPower);
    }

    // Limit rotation of the turret based on the absolute encoder position of the
    // subsystem.
    // The desired operation is to allow 180 degrees of rotation.
    // Encoder values need to be determined empirically.
    /*
     * private State operatingState() { State state = State.in_range; int position =
     * turret.getAbsPosition();
     * 
     * if (position <= countLimitCCW) { state = State.at_CCW_limit; } else if
     * (position >= countLimitCW) { state = State.at_CW_limit; } else { state =
     * State.in_range; }
     * 
     * return state; }
     */
}