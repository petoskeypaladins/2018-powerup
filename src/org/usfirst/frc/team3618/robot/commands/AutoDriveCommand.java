package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

/**
 *
 */
public class AutoDriveCommand extends Command {

    public AutoDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kDriveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveSubsystem.resetRobotAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kDriveSubsystem.driveStraightGyro(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveSubsystem.getLeftCounts() > 10000;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kDriveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
