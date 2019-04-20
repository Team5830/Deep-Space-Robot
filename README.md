# Deep-Space-Robot
Team 5830's robot code for FIRST Destination: Deep Space (2019). Our robot's name was Primus Plunger.

## Our Robot's Features

#### Cargo
This year, our robot had a toilet plunger connected to a shop vac to suck cargo from the floor, with a laundry basket around it to help with aiming and keep the cargo attached. We utilized a parallel arm system to lift the manipulator, and a "wrist" pivot to allow for flexible cargo placement. The plunger was attached to a piston to push the cargo up into high rocket.

#### Climbing
We utilized a five-piston climbing system with the intent of climbing to hab three. Unfortunately, hab three was not functional, so instead Primus Plunger climbs to hab 2, with the unused pistons serving as backups.

#### Swerve
Using swerve again this year allowed us to easily align with the rockets and cargo ship to place cargo, utilizing automatic Pixy aligning using the Pixy2, a self-learning vision processing camera.

## Notable Software Features

#### Choose controller input via Shuffleboard
This is a rather uncommon feature developed by 5830 last year. At the beginning of the match, the driver can choose which preset controller layout to use, whether it be an Xbox controller, dual flightsticks, and/or a custom-built Driver Interface Device we built this year. Instead of using OI.java, we utilize two commands: JoystickMappingInit and JoystickMappingPeriodic, called in teleopInit and teleopPeriodic, respectively. In each is a switch that polls Shuffleboard to see which preset the driver selected and calls the code within as needed.

##### Advantages:
- Allows every driver to have a separate controller configuration tailored to them without the need for commenting
- Test modes can be added to the list. For example, we had a configuration that tested every piston manually.

##### Disadvantages:
- Switching while enabled causes code crash (null pointer exception)
- whileHeld doesn't work (must use `if(button.get) InstantCommand.start`)

#### Pixy Auto-Align
We mounted a Pixy2 camera at the front of our manipulator, pointed downwards, to detect and align to the white lines. The Pixy2 was connected via USB to an onboard Raspberry Pi that converted the sent vision coordinates into NetworkTables values via a Python script set to run on startup (not in this repo). The robot the received this data and attempted to match with known-good values by strafing and rotating (see commands PixyLineRotation and PixyLineStrafe). PixyAlign is a CommandGroup listing a particular arrangement of Rotate and Strafe commands that seemed to work well for most align scenarios.

##### Advantages:
- Arguably quicker than driver alignment, especially in hard-to-see places
- One less thing for the driver to do

##### Disadvantages:
- Somewhat slow and does not perform real-time correction
- If Pixy2 loses the line mid-correction it will continue rotating forever until disabled (attempted to fix with StopAllCommands button)

#### PID-controlled arm/wrist with slow setpoint ramping and absolute joystick control
PID is nothing new to us, however we realized that the cargo was being flung out when the arm and wrist snapped into the preset setpoints. Instead of dealing with Velocity PIDs we opted for a simple setpoint ramp control found in teleopPeriodic. All this does is check to see if the setpoint is less than or greater than the current "protected" setpoint. If less, it adds a constant amount per tick (changeable in Constants.java), and vice-versa if greater.

##### Advantages:
- Smooth movement, even if the driver takes over in manual mode and flings the controls around

##### Disadvantages:
- If the setpoint is in between the constant amount it adds every tick, the PID controlled element will jump above and below the setpoint every tick, looking like it is stuttering. To prevent, make sure the PID preset setpoints are set to a multiple of the constant amount.
- Movement speed is proportional to the RoboRIO's tick speed. If there are tasks that weigh the RIO down the arm will physically slow.
