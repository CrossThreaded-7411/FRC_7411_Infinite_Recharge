/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.util.logging.Logger;
import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.RobotLogger;

/**
 * Do NOT add any static variables to this class, or any initialization at all.
 * Unless you know what you are doing, do not modify this file except to change
 * the parameter class to the startRobot call.
 */
public final class Main
{
   // use the classname for the logger, this way you can refactor
   private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   
   private Main()
   {

   }

   /**
    * Main initialization function. Do not perform any initialization here.
    *
    * <p>
    * If you change your main robot class, change the parameter type.
    */
   public static void main(String... args)
   {
      try
      {
         RobotLogger.setup();
         logger.info("Main has started");
      }
      catch (IOException e)
      {
         e.printStackTrace();
         throw new RuntimeException("Problems with creating the log files");
      }

      RobotBase.startRobot(Robot::new);
   }
}
