/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
   public static final class MotorPorts
   {
      public static final int launchMotorLowerPort = 0;
      public static final int launchMotorUpperPort = 1;
      public static final int leftFrontDriveMotorPort = 2;
      public static final int leftRearDriveMotorPort = 3;
      public static final int rightFrontDriveMotorPort = 4;
      public static final int rightRearDriveMotorPort = 5;
      public static final int liftSlideMotorPort = 6;
      public static final int liftRaiseMotorPort = 7;
      public static final int ballCollectorMotorPort = 9;
      // public static final int  = 9;
   
   }

   public static final class OIConstants
   {
      public static final int driver1ControlPort = 0;
      public static final int driver2ControlPort = 1;
   }

   public enum GamePadAxis
   {
      leftStickX(0),
      leftStickY(1),
      leftTrigger(2),
      rightTrigger(3),
      rightStickX(4),
      rightStickY(5);

      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;

      GamePadAxis(int value)
      {
         this.value = value;
      }
   }

   public enum GamePadButtons
   {
      buttonA(1),
      buttonB(2),
      buttonX(3),
      buttonY(4),
      bumperL(5),
      bumperR(6),
      back(7),
      start(8),
      leftStickPress(9),
      rightStickPress(10),
      rightStick(1),
      mode(-1),
      logitech(-1);

      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;

      GamePadButtons(int value)
      {
         this.value = value;
      }
   }

   public enum LogitechProAxis
   {
      XAxis(0),
      YAxis(1),
      ZAxis(2);

      @SuppressWarnings({"MemberName", "PMD.SingularField"})
      public final int value;

      LogitechProAxis(int value)
      {
         this.value = value;
      }
   }
}
