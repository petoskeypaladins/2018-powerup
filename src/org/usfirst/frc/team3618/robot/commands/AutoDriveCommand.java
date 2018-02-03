package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

/**
 *
 */
public class AutoDriveCommand extends Command {
	int goal;
	double minimum;
    public AutoDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kDriveSubsystem);
    	goal = 10000;
    	minimum = 0.30;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveSubsystem.resetRobotAngle();
    	Robot.kDriveSubsystem.encoderReset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int remaining;
    	remaining = goal - Robot.kDriveSubsystem.getLeftCounts();
    	if(remaining / 20000.0 < minimum) {
    		Robot.kDriveSubsystem.driveStraightGyro(minimum);
    	} else {
    		Robot.kDriveSubsystem.driveStraightGyro(remaining/20000.0); 
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveSubsystem.getLeftCounts() > goal;
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
