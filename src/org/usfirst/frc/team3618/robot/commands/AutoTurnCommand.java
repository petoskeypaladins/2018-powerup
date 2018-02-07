package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurnCommand extends Command {
	double goal = 90;
    public AutoTurnCommand() {
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
    	double difference = goal - Robot.kDriveSubsystem.getRobotAngle();
    	difference = difference / 100;
    	if (difference > 0.667) {
    		difference = 0.667;
    	} else if (difference < -0.667) {
    		difference = -0.667;
    	}
    	
    	Robot.kDriveSubsystem.turn(difference);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean done = Math.abs(Robot.kDriveSubsystem.getRobotAngle()- goal) < 5.00;
    	if (done == true) {
    		System.out.println(Robot.kDriveSubsystem.getRobotAngle());
    	}
    	return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
