package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.TeleOpPivotCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PivotSubsystem extends Subsystem {
	static final TalonSRX pivotMotor = new TalonSRX(RobotMap.PIVOT_MOTOR);
	int pivotValue;
	public final double BOTTOM_ENCODER_SPOT = 2110.0;
	public boolean holdUp = true;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	pivotReset();
    	setDefaultCommand(new TeleOpPivotCommand());
    }
    public int getPivotPosition() {
    	return pivotValue - pivotMotor.getSensorCollection().getPulseWidthPosition();
    }
    public void pivotReset() {
    	pivotValue = pivotMotor.getSensorCollection().getPulseWidthPosition();
    	holdUp = true;
    /* top = 0;
     * middle = 1810
     * bottom = 2110
     */ 
    }
    public void move(double direction) {
//    	if(getPivotPosition() < 0) {
//    		pivotReset();
//    	}
    	if(direction > 0 && (getPivotPosition() < 0)) {
    		direction = 0;
    	} else if ((direction < 0) && (getPivotPosition() >= BOTTOM_ENCODER_SPOT)) {
     		direction = 0;
    	}		
		pivotMotor.set(ControlMode.PercentOutput, direction);
		SmartDashboard.putNumber("Pivot direction", direction);
    	SmartDashboard.putBoolean("HoldUp", holdUp);
    }
    public void setHoldUp(boolean hold) {
    	holdUp = hold;
    }
}

