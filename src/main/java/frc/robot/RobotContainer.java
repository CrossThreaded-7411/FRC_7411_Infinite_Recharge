/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
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
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.BallCollectorSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.BallTurretSubsystem;

// Import Commands
import frc.robot.commands.DriveByJoystick;
import frc.robot.commands.RunBallShooter;
import frc.robot.commands.RunBallCollector;
import frc.robot.commands.RunLift;
import frc.robot.commands.RunTurret;
import frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
  // The robot's subsystems and commands are defined here...
  private final BallShooterSubsystem ballShooterSubsystem = new BallShooterSubsystem();
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final RecordPlayerSubsystem recordPlayerSubsystem = new RecordPlayerSubsystem();
  private final LiftSubsystem liftSubsystem = new LiftSubsystem();
  private final BallCollectorSubsystem ballCollectorSubsystem = new BallCollectorSubsystem();
  private final BallTurretSubsystem turretSubsystem = new BallTurretSubsystem();
  
  //   private final LaunchEnergyCommand m_launchEnergyCommand = new LaunchEnergyCommand(m_energyLaunchSubsystem, 0.0);

  // Create driver controller
  public Joystick driver1Joystick = new Joystick(OIConstants.driver1ControlPort);
  public Joystick driver2Joystick = new Joystick(OIConstants.driver2ControlPort);

  // Create joystic button objects
  //   JoystickButton launchButton = new JoystickButton(m_driver1Joystick, GamePadButtons.buttonA.value);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
      // Configure the button bindings
      configureButtonBindings();
      
      driveTrainSubsystem.setDefaultCommand(new DriveByJoystick(driveTrainSubsystem));
      liftSubsystem.setDefaultCommand(new RunLift(liftSubsystem));
      turretSubsystem.setDefaultCommand(new RunTurret(turretSubsystem));
      recordPlayerSubsystem.setDefaultCommand(new SpinRecordPlayer(recordPlayerSubsystem));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@linkw
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    // Bind controller buttons to commands
    new JoystickButton(driver1Joystick, GamePadButtons.back.value).whenPressed(new RunBallShooter(ballShooterSubsystem, 0.0, 0.0));
    new JoystickButton(driver1Joystick, GamePadButtons.buttonB.value).whenPressed(new RunBallShooter(ballShooterSubsystem, 0.6, 0.8));
    new JoystickButton(driver1Joystick, GamePadButtons.buttonA.value).whenPressed(new RunBallShooter(ballShooterSubsystem, 0.7, 0.9));
    new JoystickButton(driver1Joystick, GamePadButtons.buttonX.value).whenPressed(new RunBallShooter(ballShooterSubsystem, 0.8, 1.0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An ExampleCommand will run in autonomous
    return null; //m_autoCommand;
  }
}
