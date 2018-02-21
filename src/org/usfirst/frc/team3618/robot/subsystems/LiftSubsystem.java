package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.TeleOpLiftCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftSubsystem extends Subsystem {
	public final TalonSRX leftLift = new TalonSRX(RobotMap.LEFT_LIFT);
	public final TalonSRX rightLift = new TalonSRX(RobotMap.RIGHT_LIFT);
	public final AnalogInput liftHeight = new AnalogInput(0);
	final DoubleSolenoid lock = new DoubleSolenoid(1,3);
	final double VOLTS_PER_INCH = (4.2 - 0.253) / (77.0 - 4.25);
	final double LIFT_HEIGHT_OFFSET = 4.5; // Inches
	static final Value NOT_LOCKED = Value.kForward;
	static final Value LOCKED = Value.kReverse;
	static final double MINIMUM = 0.145;
	static final double MAXIMUM = 0.7;
	static final double SECOND_STAGE_MINIMUM = 0.25;
	public static final double SECOND_STAGE_HEIGHT = 37;
	public static final double LIFT_SWITCH_HEIGHT = 19;
	public static final double LIFT_SCALE_HEIGHT = 75;
	public static final double LIFT_BOTTOM_HEIGHT = 0;
	int liftValue = 0;
	boolean startedDown;
	int downTime;
	boolean externalUnlock = false;
	int counter = 0;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	lock.set(LOCKED);
    	rightLift.setInverted(true);
    	setDefaultCommand(new TeleOpLiftCommand());
    }
    public boolean isLiftDown() {
    	boolean down = leftLift.getSensorCollection().isRevLimitSwitchClosed();
    	if(down) {
    		encoderReset();
    	}
//    	if(getEncoderValue() < -200) {
//    		down = true;
//    	}
    	return down;
    }
    public double getCurrentLiftHeight() {
    	return liftHeight.getVoltage() / VOLTS_PER_INCH - LIFT_HEIGHT_OFFSET;
    }
    public void moveLift(double direction) {
    	double remain = 0;
    	double maxPower = 0;
    	if(direction < 0) {
    		if (!startedDown) {
    			startedDown = true;
    			downTime = 0;
    		}
    		remain = LIFT_BOTTOM_HEIGHT - getCurrentLiftHeight(); // 0 to -75" remain to go down
    		maxPower = remain / 11.0;
    		if (isLiftDown())
    			direction = 0;
    		else if (direction < maxPower)
    			direction = maxPower; // don't hit the end of travel hard
    	} else if (direction > 0) {
    		startedDown = false;
    		// trying to go up
    		remain = LIFT_SCALE_HEIGHT - getCurrentLiftHeight(); // 0 to 75" remain to go up
    		maxPower = remain / 7.0; // need more power going up than down
    		if (remain < 0) {
    			direction = 0; // all the way up, quit going up
    		}
    		else if (direction > maxPower)
    			direction = maxPower; // don't hit end of travel hard
        }
    	else {
    		startedDown = false;
    	}
    	if (direction == 0) {
       		if(externalUnlock) {
       			lock.set(NOT_LOCKED);
       			if (counter > 10) {
		    		leftLift.set(ControlMode.PercentOutput, -0.12);
		       		rightLift.set(ControlMode.PercentOutput, -0.12);
       			}
       			counter++;
       		} else {
       			lock.set(LOCKED);
       			counter= 0;
        		leftLift.set(ControlMode.PercentOutput, 0);
           		rightLift.set(ControlMode.PercentOutput, 0);
       		}
   		} else {
   			counter=0;
   			double minimum = MINIMUM;
   			if(getCurrentLiftHeight() > SECOND_STAGE_HEIGHT){
   				minimum = SECOND_STAGE_MINIMUM;
   			}
   			if(direction > 0 && direction < minimum) {
   				direction = minimum;
   			} else if (direction < 0 && direction > -minimum) {
   				direction = -minimum;
   			} else if (direction > MAXIMUM) {
   				direction = MAXIMUM;
   			} else if (direction < -MAXIMUM) {
   				direction = -MAXIMUM;
   			}
   			if ((direction < 0 && downTime > 20) || (direction > 0)) {
	    		leftLift.set(ControlMode.PercentOutput, direction);
	       		rightLift.set(ControlMode.PercentOutput, direction);
   			}
   			downTime++;
	       	lock.set(NOT_LOCKED);
    	}
    }
    public void unlock() {
    	externalUnlock = true;
    }
    public void lock() {
    	externalUnlock =  false;
    }
    public void encoderReset() {
    	liftValue = rightLift.getSensorCollection().getPulseWidthPosition();
    }
    public int getEncoderValue() {
    	return rightLift.getSensorCollection().getPulseWidthPosition() - liftValue;
    }
}
   

