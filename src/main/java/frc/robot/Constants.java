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
   public static final class LauncherConstants
   {
      public static final int motorPort = 0;
   }

   public static final class OIConstants
   {
      public static final int driver1ControlPort = 0;
   }

   public enum GamePadAxis
   {
      leftStickX(1),
      leftStickY(2),
      shoulder(3),
      rightStickX(4),
      rightStickY(5),
      dpad(6);

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
      triggerL(5),
      triggerR(6),
      back(7),
      start(8),
      leftStick(9),
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
}
