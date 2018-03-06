package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.PivotSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
@SuppressWarnings("unused")
public class TeleOpPivotCommand extends Command {
	final double PIVOT_MOTOR_SPEED = 0.75;
	
	
    public TeleOpPivotCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.kPivotSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.m_oi.functionController.getPOV(0) == 0) {
//    		Robot.kPivotSubsystem.move(PIVOT_MOTOR_SPEED);
//    		Robot.kPivotSubsystem.holdUp = true;
//    	} else if (Robot.m_oi.functionController.getPOV(0) == 180) {
//    		Robot.kPivotSubsystem.move(-PIVOT_MOTOR_SPEED);
//    		Robot.kPivotSubsystem.holdUp = false;
//    	} 
//    	
//    	if (Robot.kPivotSubsystem.holdUp) {
//    		Robot.kPivotSubsystem.move(Math.min(Robot.kPivotSubsystem.getPivotPosition()/Robot.kPivotSubsystem.BOTTOM_ENCODER_SPOT,0.7));
//    	}
//    	else {
//    		if (Robot.kPivotSubsystem.getPivotPosition() /Robot.kPivotSubsystem.BOTTOM_ENCODER_SPOT < 0.7)
//    			Robot.kPivotSubsystem.move((Robot.kPivotSubsystem.getPivotPosition()-Robot.kPivotSubsystem.BOTTOM_ENCODER_SPOT)/Robot.kPivotSubsystem.BOTTOM_ENCODER_SPOT*.3);
//    		else
//    			Robot.kPivotSubsystem.move(0);
//    	}
//    	
//    	SmartDashboard.putNumber("Pivot Encoder Value", Robot.kPivotSubsystem.getPivotPosition());
//    	
    	/*if (Robot.m_oi.driveController.getY(Hand.kRight) > 0.1 || Robot.m_oi.driveController.getY(Hand.kRight) < 0.1) {
    		Robot.kPivotSubsystem.pivotMotor.set(ControlMode.PercentOutput, -Robot.m_oi.driveController.getY(Hand.kRight));
    	}*/
    	if(Robot.m_oi.functionController.getPOV(0) == 0) {
    			PivotSubsystem.pivotMotor.set(ControlMode.PercentOutput, -PIVOT_MOTOR_SPEED);
    		
    	} else if (Robot.m_oi.functionController.getPOV(0) == 180) {
    		if(PivotSubsystem.isUp()) {
    			PivotSubsystem.pivotMotor.set(ControlMode.PercentOutput, 0);
    		} else {
    			PivotSubsystem.pivotMotor.set(ControlMode.PercentOutput, PIVOT_MOTOR_SPEED);
    		}
    	} else {
    		PivotSubsystem.pivotMotor.set(ControlMode.PercentOutput, 0);
    	}
    	SmartDashboard.putBoolean("is up", PivotSubsystem.isUp());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
