/*---------------------------------------------------------------------------
   FRC Team CrossThreaded #7411
   Valley Lutheran School, Cedar Falls, IA
   Open Source Software - may be modified and shared by all.
  ---------------------------------------------------------------------------*/
package frc.robot;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.lang.System;


// This class creates a class for logging data to the console and/or file
public class RobotLogger
{
   static private FileHandler logFile;

   public static void setup() throws IOException
   {
      // Get the global logger to configure it
      Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

      // The formatting can be customized by specifying the format string in the java.util.logging.SimpleFormatter.format property.
      // The given LogRecord will be formatted as if by calling:
      //    String.format(format, date, source, logger, level, message, thrown);
      System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT.%1$tL [%4$s]: %5$s%n");


      // Set the level of messages to log
      logger.setLevel(Level.INFO);

      // Specify the log file and the the file format
      String fileName = "RobotLog_" + LocalDateTime.now() + ".log";
      logFile = new FileHandler("/home/lvuser/logs/" + fileName);
      // logFile = new FileHandler("/u" + fileName);
 
      logFile.setFormatter(new SimpleFormatter());
      logger.addHandler(logFile);
   }


   // Place all calls to logging methods here. This will provide us a single point to enable
   // and disable the feature. It also organizes logging method calls so they are not sprinkled
   // throughout code and easier to manage.
   public static void logData()
   {
      Robot.m_robotContainer.driveTrainSubsystem.logDriveTrainData();
      Robot.m_robotContainer.pdpSubsystem.logPDPData();
      Robot.m_robotContainer.liftSubsystem.logLiftData();
   }
}