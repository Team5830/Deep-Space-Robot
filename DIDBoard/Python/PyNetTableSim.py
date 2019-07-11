#!/usr/bin/env python3
#
# This is a NetworkTables server (eg, the robot or simulator side).
#
# On a real robot, you probably would create an instance of the
# wpilib.SmartDashboard object and use that instead -- but it's really
# just a passthru to the underlying NetworkTable object.
#
# When running, this will continue incrementing the value 'robotTime',
# and the value should be visible to networktables clients such as
# SmartDashboard. To view using the SmartDashboard, you can launch it
# like so:
#
#     SmartDashboard.jar ip 127.0.0.1
#

import time
from networktables import NetworkTables

# To see messages from networktables, you must setup logging
import logging

logging.basicConfig(level=logging.DEBUG)

NetworkTables.initialize()
sd = NetworkTables.getTable("SmartDashboard")

i = 0
while True:
    print("robotTime:", sd.getNumber("robotTime", "N/A"))

    sd.putNumber("robotTime", i)
    sd.putBoolean("DIDVacOn", True)
    sd.putBoolean("DIDPlungerOut", True)
    sd.putBoolean("DID12HabFirstOut", True)
    sd.putBoolean("DID12HabLastOut", True)
##    #sd.putBoolean("DID23HabFirstOut", True)
##    #sd.putBoolean("DID23HabLastOut", True)
    sd.putBoolean("DIDArmHasCommand", True)    
    sd.putBoolean("DIDWristHasCommand", True)
    sd.putNumber("DIDArmValue", 0)
    sd.putNumber("DIDArmPower", 0)
    sd.putNumber("DIDWristValue", 0)
    sd.putNumber("DIDWristPower", 0)
    sd.putNumber("DIDPressure", 0)
    sd.putNumber("DIDWheelFRPower", 0)
    sd.putNumber("DIDWheelFLPower", 0)
    sd.putNumber("DIDWheelBRPower", 25)
    sd.putNumber("DIDWheelBLPower", 0)
    sd.putNumber("DIDTotalPower", 0)
    sd.putNumber("Gyro Angle",0)
    sd.putString("Status", "Teleop")

    if (i == 5):
        sd.putNumber("DIDWheelBRPower", 100)
        sd.putNumber("DIDTotalPower", 120)
        sd.putBoolean("DIDArmHasCommand", False)    
        sd.putBoolean("DIDWristHasCommand", False)
        time.sleep(5)
    elif (i == 12):
        sd.putNumber("DIDArmPower", 100)
        sd.putBoolean("DIDVacOn", False)
        sd.putBoolean("DIDPlungerOut", False)
        sd.putBoolean("DIDArmHasCommand", True)    
        sd.putBoolean("DIDWristHasCommand", True)
        time.sleep(5)
        sd.putBoolean("DIDVacOn", True)
        sd.putBoolean("DIDPlungerOut", True)
        sd.putNumber("DIDWristPower", 50)
        sd.putBoolean("DID12FirstOut", True)
        sd.putBoolean("DID12LastOut", True)
 ##       sd.putBoolean("DID23FirstOut", True)
 ##       sd.putBoolean("DID23LastOut", True)
        
        
        time.sleep(3)
    time.sleep(1)
    i += 1
