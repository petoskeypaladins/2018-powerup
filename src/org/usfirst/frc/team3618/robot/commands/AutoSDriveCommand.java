package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoSDriveCommand extends Command {
	double goal;
	final double MINIMUM = 0.41;
	double maxTime;
	static final double SCALER = 38000.0;
	double remaining;
	double curve;
	

    public AutoSDriveCommand(double inches, boolean rightSide) {
    	requires(Robot.kDriveSubsystem);
    	remaining = goal = inches * DriveSubsystem.ENCODER_COUNTS_PER_INCH;
    	maxTime = 10.0 / (220 * DriveSubsystem.ENCODER_COUNTS_PER_INCH) * goal;
    	curve = 40; // max degrees in s-curve
    	if (!rightSide)
    		curve *= -1; // left is opposite sign
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.kDriveSubsystem.encoderReset();
    	Robot.kDriveSubsystem.resetRobotAngle(); // need to always start with zero when doing S-Curve
    	Robot.kDriveSubsystem.shifToHighGear(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	remaining = goal - Robot.kDriveSubsystem.getAverageCounts();
    	double power = remaining / SCALER;
    	if(power < MINIMUM) {
    		power = MINIMUM;
    	}
    	SmartDashboard.putNumber("Power", power);
    	SmartDashboard.putNumber("Inches", Robot.kDriveSubsystem.getAverageCounts() / DriveSubsystem.ENCODER_COUNTS_PER_INCH);
   		Robot.kDriveSubsystem.driveSCurve(power,goal-remaining,goal,curve); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (remaining / DriveSubsystem.ENCODER_COUNTS_PER_INCH <= 0) || (maxTime < timeSinceInitialized());
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
