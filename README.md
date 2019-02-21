# H12code

## Robot features
* Self Contained Subsystems
  - All subsystems are self contained and have a static instence that can be called anywhere in the code
  - All subsystems refernce a robotMap.java file to quickly change PWM and DIO ports
* Vision CoProsseser (TODO:)
  - Tracking Balls horizontal and vertical location
  - Tracking panel targets horizontal and vertical location
  - Network table interface with robot
  - Python Vision CoProsseser code found at: https://github.com/Herobotics/H12VisionCoprocessor
## Sub Systems
* Chassis
  - Drive direction inversion with display LED's to help driver control
  - We use pid in combination with encoders and a gyroscope to control autonomously set movement targets
  - Arcade drive with squared inputs for easy driver control of robot
  - Callable movement commands to quickly make new autonomous configerations
* Ball Intake
  - Wheels that can pull in field elements
  - Lowering Intake to get balls on button press
  - Automatic lowering of intake to avoid colliding with carriage (TODO)
* Ball Carriage
  - Ball Carriage with wheels on it to eject the ball into targets
* Duo Side Lift
  - Lift with two independently controled sides
  - Encoders with PID to set target heights
* Panel Intake/Output
  - Solinoid controlled Panel Intake/Output
  - Automatic actuation of intake to make reaction times faster (TODO)
  - Automatic placement of panels on targets (TODO)