package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurnCommand extends Command {
	double goal;
	public double difference;
	static final double MINIMUM_POWER = 0.39;
	boolean done;
	boolean lastDone;
	double timeCompleted;
	
    public AutoTurnCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	goal = angle;
    	lastDone = false;
    	requires(Robot.kDriveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveSubsystem.encoderReset();
//		Robot.kDriveSubsystem.resetRobotAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	difference = goal - Robot.kDriveSubsystem.getRobotAngle();
    	difference = difference / 100;
    	if (difference > 0.6) {
    		difference = 0.6;
    	} else if (difference < -0.6) {
    		difference = -0.6;
    	}
    	if (difference > 0 && difference < MINIMUM_POWER) {
    		difference = MINIMUM_POWER;
    	} else if (difference < 0 && difference > -MINIMUM_POWER) {
    		difference = -MINIMUM_POWER;
    	}
    	Robot.kDriveSubsystem.turn(difference);
    	SmartDashboard.putNumber("difference value", difference);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	done = (Math.abs(Robot.kDriveSubsystem.getRobotAngle()- goal) < 6.00) || done;
    	if (done && done != lastDone) {
    		System.out.println(Robot.kDriveSubsystem.getRobotAngle());
    		timeCompleted = timeSinceInitialized();
    		lastDone = done;
    	}
    	return done && (timeSinceInitialized() - timeCompleted) > 1.0;
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
