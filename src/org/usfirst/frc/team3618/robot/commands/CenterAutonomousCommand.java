package org.usfirst.frc.team3618.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterAutonomousCommand extends CommandGroup {

    public CenterAutonomousCommand() {
        
    	addSequential(new AutoDriveCommand(120,0));
       
    }
}
