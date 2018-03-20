package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.TeleOpClimbCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	public static final Servo latchServo = new Servo(RobotMap.SERVO_PORT);
	static final TalonSRX leftClimb = new TalonSRX(RobotMap.LEFT_CLIMB);
	static final TalonSRX rightClimb = new TalonSRX(RobotMap.RIGHT_CLIMB);
	final double LATCH_ANGLE = 64;
	final double UNLATCH_ANGLE = -8;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	unlatch();
    	setDefaultCommand(new TeleOpClimbCommand());
    }
    public void move(double speed) {
	    leftClimb.set(ControlMode.PercentOutput, speed);
	    rightClimb.set(ControlMode.PercentOutput, speed);
    }
//    public void latch() {
//    	latchServo.setAngle(LATCH_ANGLE);
//    }
//    public void unlatch() {
//    	latchServo.setAngle(UNLATCH_ANGLE);
//    }
    public void setLatch(double degrees) {
    	latchServo.setAngle(degrees);
    }
}

