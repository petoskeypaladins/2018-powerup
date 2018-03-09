package org.usfirst.frc.team3618.robot.commands;


import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutonomousCommand extends CommandGroup {
	public enum CenterChoices{
		CenterLeft,CenterRight
	}
    public CenterAutonomousCommand(CenterChoices choice) {

    	if(choice == CenterChoices.CenterLeft) {
    		addParallel(new AutoSDriveCommand(173,false));
    	} else {
    		addParallel(new AutoSDriveCommand(173,true));
    	}
//		addParallel(new AutoLiftCommand(LiftSubsystem.LIFT_SWITCH_HEIGHT));
//		addSequential(new AutoPivotCommand(),1.75);
//		addSequential(new WaitCommand(), 0.1);
//		addSequential(new AutoDropCommand());
        
       
    }
}
