package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAutonomousCommand extends CommandGroup {

    public RightAutonomousCommand() {
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
           if(gameData.length() > 0) {
               	if(gameData.charAt(0) == 'L') {
               		addSequential(new AutoDriveCommand(236,0));
        			addSequential(new WaitCommand(), 1);
        			addSequential(new AutoTurnCommand(-90));
        			addSequential(new WaitCommand(), 1);
        			addSequential(new AutoDriveCommand(120, -90));
        			addSequential(new WaitCommand(), 1);
        			addSequential(new AutoLiftCommand (LiftSubsystem.LIFT_SWITCH_HEIGHT));
        			addSequential(new AutoTurnCommand(-180));
        			addSequential(new WaitCommand(), 1);
        			addSequential(new AutoPivotCommand(),0.5);
        			addSequential(new WaitCommand(), 1);
        			addSequential(new AutoOuttakeCommand(), 1);	
               } else if (gameData.charAt(0) == 'R') {
            	   addSequential(new AutoDriveCommand(170,0));
       				addSequential(new WaitCommand(), 1);
       				addSequential(new AutoLiftCommand (LiftSubsystem.LIFT_SWITCH_HEIGHT));
       				addSequential(new WaitCommand(), 0.5);
       				addSequential(new AutoTurnCommand(-90));
       				addSequential(new WaitCommand(), 1);
       				addSequential(new AutoDriveCommand(15,-90));
       				addSequential(new AutoPivotCommand(), 0.5);
       				addSequential(new WaitCommand(), 1);
       				addSequential(new AutoOuttakeCommand(), 1);

               }
           }	  
    	}
	}