package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class AutoDriveCommand extends Command {
	double goal;
	double minimum;
	double inchGoal;
	double goalAngle;
	double maxTime;
	double maximum;
	double SCALER;
	
	
    public AutoDriveCommand(double inches, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.kDriveSubsystem);
    	inchGoal = inches;
    	goalAngle = angle;
    	goal = inches * DriveSubsystem.ENCODER_COUNTS_PER_INCH;
    	minimum = 0.40;
    	maximum = 0.81;
//    	SCALER = 48000; // 180"
//    	SCALER = 30000; // 80"
    	SCALER = 180 * inches + 15600;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveSubsystem.encoderReset();
    	Robot.kDriveSubsystem.shifToHighGear(true);
    	maxTime = (5.0 / (220 * DriveSubsystem.ENCODER_COUNTS_PER_INCH) * goal) * 2.0 + timeSinceInitialized();
    	SmartDashboard.putNumber("maxTime", maxTime);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double remaining;
    	remaining = goal - Robot.kDriveSubsystem.getLeftCounts();
    	double power = remaining / SCALER;
    	if(power < minimum) {
    		power = minimum;
    	}
    	if(power > maximum) {
    		power = maximum;
    	}
    	SmartDashboard.putNumber("Inches", Robot.kDriveSubsystem.getLeftCounts() / DriveSubsystem.ENCODER_COUNTS_PER_INCH);
   		Robot.kDriveSubsystem.driveStraightGyro(power, goalAngle);
   		SmartDashboard.putNumber("Time since initialized", timeSinceInitialized());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putString("Times Up", maxTime < timeSinceInitialized()?"Yes":"No");
        return (Robot.kDriveSubsystem.getLeftCounts() / DriveSubsystem.ENCODER_COUNTS_PER_INCH > inchGoal) || (maxTime < timeSinceInitialized());
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
