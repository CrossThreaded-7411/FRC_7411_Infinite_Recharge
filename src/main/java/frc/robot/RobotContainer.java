/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RecordPlayerSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.BallCollectorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.commands.SpinRecordPlayer;

// Import Subsystems
import frc.robot.subsystems.BallShooterSubsystem;
// import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.BallFeederSubsystem;
// import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.BallTurretSubsystem;
// import frc.robot.subsystems.BallCollectorSubsystem;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.BallCollectorManual;
// Import Commands
// import frc.robot.commands.RunBallShooter;
import frc.robot.commands.RunLift;
import frc.robot.commands.RunTurret;
import frc.robot.commands.RunBallCollector;
import frc.robot.commands.RunDriveTrain;
import frc.robot.subsystems.BallFeederSubsystem;
import frc.robot.subsystems.BallTurretSubsystem;
import frc.robot.subsystems.PDPSubsystem;

// Import Commands
import frc.robot.commands.RunLift;
import frc.robot.commands.RunTurret;
import frc.robot.commands.RunBallCollector;
import frc.robot.Constants.*;
import frc.robot.commands.StartShooterCommandGroup;
import frc.robot.commands.StopShooterCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
   // The robot's subsystems and commands are defined here...
   protected final BallShooterSubsystem ballShooterSubsystem = new BallShooterSubsystem();
   protected final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
   protected final LiftSubsystem liftSubsystem = new LiftSubsystem();
   protected final BallFeederSubsystem ballFeederSubsystem = new BallFeederSubsystem();
   protected final BallTurretSubsystem turretSubsystem = new BallTurretSubsystem();
   protected final BallCollectorSubsystem ballCollectorSubsystem = new BallCollectorSubsystem();
   protected final RecordPlayerSubsystem recordPlayerSubsystem = new RecordPlayerSubsystem();
   protected final PDPSubsystem pdpSubsystem = new PDPSubsystem();
   protected final StartShooterCommandGroup autoCommand = new StartShooterCommandGroup(ballShooterSubsystem, ballFeederSubsystem, ballCollectorSubsystem, driveTrainSubsystem);

   // Create driver controller
   public final Joystick driver1Controller = new Joystick(OIConstants.driver1ControlPort);
   public final Joystick driver2Controller = new Joystick(OIConstants.driver2ControlPort);


   /**
    * The container for the robot. Contains subsystems, OI devices, and commands.
    */
   public RobotContainer()
   {
      // Configure the button bindings
      configureButtonBindings();

      // driveTrainSubsystem.setDefaultCommand(new DriveByJoystick(driveTrainSubsystem));
      liftSubsystem.setDefaultCommand(new RunLift(liftSubsystem));
      ballCollectorSubsystem.setDefaultCommand(new RunBallCollector(ballCollectorSubsystem));
      turretSubsystem.setDefaultCommand(new RunTurret(turretSubsystem));
      recordPlayerSubsystem.setDefaultCommand(new SpinRecordPlayer(recordPlayerSubsystem));
  }

   /**
    * Use this method to define your button->command mappings. Buttons can be created by
    * instantiating a {@link GenericHID} or one of its subclasses
    * ({@linkw edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
    * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
    */
   private void configureButtonBindings()
   {
      // Bind controller buttons to commands
      new JoystickButton(driver2Controller, GamePadButtons.buttonB.value).whenPressed(new StartShooterCommandGroup(ballShooterSubsystem, ballFeederSubsystem, ballCollectorSubsystem, driveTrainSubsystem));
      new JoystickButton(driver2Controller, GamePadButtons.buttonB.value).whenReleased(new StopShooterCommandGroup(ballShooterSubsystem, ballFeederSubsystem, ballCollectorSubsystem));
      new JoystickButton(driver2Controller, GamePadButtons.buttonA.value).whenPressed(new BallCollectorManual(ballCollectorSubsystem, 0.0));

   }
   

   /**
    * Use this to pass the autonomous command to the main {@link Robot} class.
    *
    * @return the command to run in autonomous
    */
   public Command getAutonomousCommand()
   {
      // An ExampleCommand will run in autonomous
      return autoCommand;
   }
}
