/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.TeleOpDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
	public static final WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_1);
	public static final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_2);
	public static final WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_1);
	public static final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_2);
//	static final SpeedControllerGroup left = new SpeedControllerGroup(leftMotor1,leftMotor2); 
//	static final SpeedControllerGroup right = new SpeedControllerGroup(rightMotor1,rightMotor2);
	public static final DifferentialDrive driveTrain = new DifferentialDrive(leftMotor1,rightMotor1);
	static final DoubleSolenoid driveSolenoid = new DoubleSolenoid(0,2);
	static final ADIS16448_IMU gyro = new ADIS16448_IMU();
	static final Compressor compressor = new Compressor();
	public static final double ENCODER_COUNTS_PER_INCH = 217;
	int leftValue = 0;
	int rightValue = 0;
	public boolean IsSDriveDone = false;
	static final double TURN_CLIP = 0.1;

	double lastSpeed = 0;
	static final double ACCEL_RATE = 0.1; // amount of power change per cycle
	
	public DriveSubsystem() {
		gyro.calibrate();
		resetRobotAngle();
		leftMotor2.follow(leftMotor1);
		rightMotor2.follow(rightMotor1);
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
		rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
		leftMotor1.setSafetyEnabled(false);
		rightMotor1.setSafetyEnabled(false);
		leftMotor2.setSafetyEnabled(false);
		rightMotor2.setSafetyEnabled(false);
		driveTrain.setSafetyEnabled(false);
		encoderReset();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleOpDriveCommand());
	}
	
	public int getLeftCounts() {
		return leftValue - leftMotor1.getSensorCollection().getPulseWidthPosition();
	}
	
	public int getRightCounts() {
		return rightMotor1.getSensorCollection().getPulseWidthPosition() - rightValue;
	}
	
	public int getAverageCounts() {
		return (getLeftCounts() + getRightCounts()) / 2;
	}
	
	public double getRobotAngle() {
		return gyro.getAngleZ();
	}
	
	public void resetRobotAngle() {
		gyro.reset();
	}
	
    double accelRamp(double speed) {
    	int direction = 0;
    	double difference = speed - lastSpeed;
    	if (difference != 0) {
    		direction = (int) (difference / Math.abs(difference)); //1 or -1
    	}
    
    	double newSpeed = lastSpeed + (ACCEL_RATE * direction); // apply the limited amount of change this scan
    	if (Math.abs(difference) < ACCEL_RATE) {
    		newSpeed = speed; // don't overshoot the requested speed
    	}
    	lastSpeed = newSpeed;
    	return(newSpeed);
    }

    public void driveStraightGyro(double speed,double angle) {
		double Kp = 0.15;
		double turn = Kp*(angle-getRobotAngle()); 
		if (turn > TURN_CLIP)
			turn = TURN_CLIP;
		else if (turn < -TURN_CLIP)
			turn = -TURN_CLIP;
		driveTrain.arcadeDrive(accelRamp(speed),turn);
	}
	public void driveSCurve(double speed,double progress,double totalDistance,double curve) {
		double Kp = 0.12; // how hard do we work to follow the desired vs actual Gyro Angle?
		// we need our heading to start at zero (sin(0) = 0), increase to a maximum
		// value as we get half-way to the totalDistance we'll travel, then
		// go back to zero.
		// That is 1/2 of the period (so PI) of the sine function, so we'll pro-rate changing
		// between 0 and PI as we go from 0 to totalDistance
		// change the multiplier on totalDistance to be prorate the Sin to drive back towards center
		double angle = Math.sin((Math.PI / (totalDistance * 0.9)) * progress);
		angle = angle * curve; // now apply the curve "effort" (which also can be negative to be left vs right)
		double turn = Kp*(angle-getRobotAngle()); // error between angle we want and the angle of the robot
		driveTrain.arcadeDrive(accelRamp(speed), turn);
		IsSDriveDone = (progress >= totalDistance);
	}
	
	public void drive(double speed,double angle) {
		driveTrain.arcadeDrive(accelRamp(speed), angle);
	}


	public void stop() {
		driveTrain.arcadeDrive(0, 0);
		lastSpeed = 0;
	}
	public void turn(double rate) {
		driveTrain.arcadeDrive(0, rate);
	}
	public void encoderReset() {
		//CSA Alex is would like to know where, if ever this is used. 
		leftValue = leftMotor1.getSensorCollection().getPulseWidthPosition();
		rightValue = rightMotor1.getSensorCollection().getPulseWidthPosition();
	}

	public boolean getIsInLowGear() {
		return driveSolenoid.get() == Value.kReverse;
	}
	public void shifToHighGear(boolean highGear) {
		// kForward is High gear, kReverse is Low gear
		if(highGear) {
			driveSolenoid.set(Value.kForward);
		} else {
			driveSolenoid.set(Value.kReverse);
		}
		
	}
}


