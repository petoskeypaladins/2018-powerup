package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftScaleCommand extends CommandGroup {

    public AutoLeftScaleCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new AutoDriveCommand(300,0));
		addSequential(new WaitCommand(), 0.5);
		addSequential(new AutoLiftCommand(LiftSubsystem.LIFT_SCALE_HEIGHT));
		addSequential(new WaitCommand(), 0.5);
		addSequential(new AutoTurnCommand(90));
		addSequential(new WaitCommand(), 0.5);
		addSequential(new AutoPivotCommand(), 0.5);
		addSequential(new WaitCommand(), 0.5);
		addSequential(new AutoOuttakeCommand(), 1);
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
