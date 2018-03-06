package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutonomousCommand extends CommandGroup {

    public CenterAutonomousCommand() {
    	Robot.kDriveSubsystem.resetRobotAngle();
    	Robot.kDriveSubsystem.encoderReset();
		addSequential(new AutoDriveCommand(120, 0));
	
//		addSequential(new AutoPivotCommand(), 1);
//		addSequential(new AutoOuttakeCommand(), 1);
        
       
    }
}
