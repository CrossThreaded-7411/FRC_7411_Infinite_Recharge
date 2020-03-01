package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StopTargeting extends CommandBase {
    public StopTargeting(){}

    @Override
    public void execute() {
        RunTurret.setTargeting(false);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}