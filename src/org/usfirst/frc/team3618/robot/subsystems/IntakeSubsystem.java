package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.TeleOpIntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	static final TalonSRX leftIntake = new TalonSRX(RobotMap.LEFT_INTAKE);
	static final TalonSRX rightIntake = new TalonSRX(RobotMap.RIGHT_INTAKE);
	static final DoubleSolenoid clampSolenoid = new DoubleSolenoid(RobotMap.CLAMP_SOLENOID_1,RobotMap.CLAMP_SOLENOID_2);
	public final AnalogInput photoSwitch = new AnalogInput(1);
	public final double INTAKE_SPEED = 0.7;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleOpIntakeCommand());
    	leftIntake.setInverted(true);
    	clampSolenoid.set(Value.kReverse);
    }
    public void move(double speed) {
    	IntakeSubsystem.leftIntake.set(ControlMode.PercentOutput, speed);
		IntakeSubsystem.rightIntake.set(ControlMode.PercentOutput, speed);
    }
    public void toggleClamp() {
    	if(clampSolenoid.get() == Value.kReverse) {
    		clampSolenoid.set(Value.kForward);
    	} else {
    		clampSolenoid.set(Value.kReverse);
    	}
    }
    
    
   
}

