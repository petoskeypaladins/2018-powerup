package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

/**
 *
 */
public class AutoDriveCommand extends Command {
	double goal;
	double minimum;
	double inchGoal;
	static final double ENCODER_COUNTS_PER_INCH = 217;
	static final double SCALER = 45000.0;
	
    public AutoDriveCommand(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kDriveSubsystem);
    	inchGoal = inches;
    	goal = inches * ENCODER_COUNTS_PER_INCH;
    	minimum = 0.35;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveSubsystem.resetRobotAngle();
    	Robot.kDriveSubsystem.encoderReset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double remaining;
    	remaining = goal - Robot.kDriveSubsystem.getLeftCounts();
    	double power = remaining / SCALER;
    	if(power < minimum) {
    		power = minimum;
    	}
   		Robot.kDriveSubsystem.driveStraightGyro(power); 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.kDriveSubsystem.getLeftCounts() / ENCODER_COUNTS_PER_INCH > inchGoal;
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
