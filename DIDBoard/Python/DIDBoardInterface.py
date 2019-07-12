###########################################################
# Python3 program to get PyFRC PyNetworkTable values and  #
# pass to Arduino via PySerial                            #
#                                                         #
# Created by FRC Team 5830 - LIFE Engineering             #
# 2019 Build Season                                       #
#                                                         #
# Version 0.1 3-14-19 - Initial Program Base              #
###########################################################
# Pre-req
# pip3 install pyfrc --upgrade
# pip3 install pyserial --upgrade
# pip3 install pip --upgrade (this will complain follow complaint)
#

import serial
import time
from networktables import NetworkTables

# Establish the connection on a specific port
# COMX ports for Windows
# '/dev/tty.usbserial' or similar for Mac or Linux
serialDevice = 'Com5'
DIDBoard = serial.Serial(serialDevice, 9600)
time.sleep(2) # wait for serial port driver to initialize

# 
# # DIDBoard.write(bytes(var))
# # Check if Arduino is ready look for
# # <DIDBOARD Ready>
# DIDReady = False
# while (not DIDReady):
#     DIDBoard.write("#".encode())
#     DIDBoardBytes = DIDBoard.readline()[:-2] #the last bit gets rid of the new-line chars
#     if (DIDBoardBytes.decode == "<DIDBOARD Ready>"):
#         DIDReady = True
#     time.sleep(500)
#     print("Waiting for DIDBoard")
# print("DIDBOARD Ready")

# Initialize Network Tables
# As a client to connect to a robot
# NetworkTables.initialize(server='roborio-XXX-frc.local')
NetworkTables.initialize(server='10.58.30.2')
#NetworkTables.initialize(server='roborio-5830-frc.local')
#NetworkTables.initialize(server='127.0.0.1')
#NetworkTables.initialize(server='192.168.1.173')
sd = NetworkTables.getTable("SmartDashboard")

# Loop waiting for RoboRio Network Table Connection
while not(NetworkTables.isConnected()):
    time.sleep(1)
    print("Waiting for RoboRio")
print("Roborio Connected")
# Send "#" aka ascii 35 to DID to let it know we can talk to RoboRio
# DIDBoard.write("#".encode())
# DIDBoard.write("$".encode())
# DIDBoard.write("@".encode())

# Get Data from RoboRio Network Tables
# Send Data to DIDBoard
def getDataSet():
    return [sd.getBoolean("DIDVacOn", False), 
    sd.getBoolean("DIDPlungerOut", False),
    sd.getBoolean("DID12HabFirstOut", False),
    sd.getBoolean("DID12HabLastOut", False),
    #sd.getBoolean("DID23HabFirstOut", False),
    #sd.getBoolean("DID23HabLastOut", False),
    
    sd.getBoolean("DIDArmHasCommand", False),    
    sd.getBoolean("DIDWristHasCommand", False),
    sd.getNumber("DIDArmValue", 0),
    sd.getNumber("DIDArmPower", 0),
    sd.getNumber("DIDWristValue", 0),
    sd.getNumber("DIDWristPower", 0),
    sd.getNumber("DIDPressure", 0),
    sd.getNumber("DIDWheelFRPower", 0),
    sd.getNumber("DIDWheelFLPower", 0),
    sd.getNumber("DIDWheelBRPower", 0),
    sd.getNumber("DIDWheelBLPower", 0),
    sd.getNumber("DIDTotalPower", 0),
    sd.getNumber("Gyro Angle",0),
    sd.getString("Status", "Teleop")]
    
def DataHandle(dat, val):
    if isinstance(val, bool):
        if dat == "DIDVacOn":
            char = ord("e")
            if val:
                char -= 32
            sendDIDBoard(chr(char))
        elif dat == "DIDPlungerOut":
            char = ord("d")
            if val:
                char -= 32
            sendDIDBoard(chr(char))
        elif dat == "DID12HabFirstOut":
            char = ord("f")
            if val:
                char -= 32
            sendDIDBoard(chr(char))
        elif dat == "DID12HabLastOut":
            char = ord("g")
            if val:
                char -= 32
            sendDIDBoard(chr(char))
##        elif dat == "DID23HabFirstOut":
##            char = ord("h")
##            if val:
##                char -= 32
##            sendDIDBoard(chr(char))
##        elif dat == "DID23HabLastOut":
##            char = ord("i")
##            if val:
##                char -= 32
##            sendDIDBoard(chr(char))
        elif dat == "DIDArmHasCommand":
            #pre = "!37"
            pre = "%"
            end = "@"
            if val:
                sendDIDBoard(pre + "RUN"+ end)
            else:
                sendDIDBoard(pre + "STP" + end)
        elif dat == "DIDWristHasCommand":
            #pre = "!40"
            pre = "("
            end = "@"
            if val:
                sendDIDBoard(pre + "RUN" + end)
            else:
                sendDIDBoard(pre + "STP" + end)
    elif isinstance(val, int) or isinstance(val, float):
        val = int(val)
        end = "@"
        if dat == "DIDArmValue":
            #pre = "!38@"
            pre = "&"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDArmPower":
            #pre = "!39@"
            pre = "'"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDWristValue":
            #pre = "!41@"
            pre = ")"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDWristPower":
            #pre = "!42@"
            pre = "*" 
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDPressure":
            #pre = "!35@"
            pre = "#"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDWheelFRPower":
            #pre = "!46@"
            pre = "."
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDWheelFLPower":
            #pre = "!45@"
            pre = "-"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDWheelBRPower":
            #pre = "!48@"
            pre = "0" 
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDWheelBLPower":
            #pre = "!47@"
            pre = "/"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "DIDTotalPower":
            #pre = "!33@"
            pre = "!"
            sendDIDBoard(pre + str(val) + end)
        elif dat == "Gyro Angle":
            #pre = "!36@"
            pre = "$"
            sendDIDBoard(pre + str(val) + end)
    elif isinstance(val, str):
        if dat == "Status":
            #pre = "!43"
            pre = "+"
            end = "@"
            sendDIDBoard(pre + val + end)
            
    
def sendDIDBoard(var):
    DIDBoard.write(var.encode())
    
def interateDataCheck(old, new):
    check = 0
    dataList = ["DIDVacOn", "DIDPlungerOut", "DID12HabFirstOut",
    "DID12HabLastOut", "DID23HabFirstOut", "DID23HabLastOut", 
    "DIDArmHasCommand", "DIDWristHasCommand", "DIDArmValue", "DIDArmPower",
    "DIDWristValue", "DIDWristPower", "DIDPressure", "DIDWheelFRPower",
    "DIDWheelFLPower", "DIDWheelBRPower", "DIDWheelBLPower", "DIDTotalPower",
    "Gyro Angle", "Status"]
    try:
        assert(len(old) == len(new))
    except:
        return "compaired lists are not the same length"
    for i in range(len(old)):
        if old[i] == new[i]:
            continue
        else:
            check += 1
            print(dataList[i] + " -----> " + str(new[i]))
            DataHandle(dataList[i], new[i])
    time.sleep(.3)
    #print(str(check) + " things changed")
    return check != 0
    
oldData = getDataSet()

# Send OK Status

pre = "+"
end = "@"
val = "READY"
sendDIDBoard(pre + val + end)

state = True
while state:
    try:
        newData = getDataSet()
    except:
        print("rewind time")
        continue
    if interateDataCheck(oldData, newData):
        oldData = newData
        time.sleep(1)
    
print(sd.getNumber("DIDWheelBRPower"))

# A - Z turn on LED
# a - z turn off LED
# Start connection with special Char embded value in middle end with @
# @ send variable data aka ascii 64
# 36 $ GYA = "Gyro Angle" Int
# 43 + STA = "Status" String - Ready, Arm High, Driving, Arm Low, AutoDriveDriving, Idle, Drop,Vacuum, VacSlow, Default
# 44 , ALM = Alarm  / Alert
# 37 % ACM = "DIDArmHasCommand" Boolean / String
# 38 & AVA = "DIDArmValue" Int
# 39 ' APW = "DIDArmPower" Int
# 40 ( WCM = "DIDWristHasCommand" Boolean
# 41 ) WVA = "DIDWristValue" Int
# 42 * WPW = "DIDWristPower" Int
# 35 # PSI = "DIDPressure" Int
# 45 - FLP = "DIDWheelFLPower" Int
# 46 . FRP = "DIDWheelFRPower" Int
# 47 / BLP = "DIDWheelBLPower" Int
# 48 0 BRP = "DIDWheelBRPower" Int
# 33 ! TLP = "DIDTotalPower" Int
# 34 " DID LIDAR NOT USED
# LED 4 = D "DIDVacOn" Boolean
# LED 5 = E "DIDPlungerOut" Boolean
# LED 6 = F "DID12HabFirstOut" Boolean
# LED 7 = G "DID12HabLastOut" Boolean
# LED 8 = H "DID23HabFirstOut" Boolean
# LED 9 = I "DID23HabLastOut" Boolean


    


