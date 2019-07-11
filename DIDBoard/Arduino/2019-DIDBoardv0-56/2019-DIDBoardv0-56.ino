#include <Joystick.h>
#include <Wire.h> 
#include <LiquidCrystal_I2C.h>


// DID VERSION
// increment for naterial changes, even small ones

int DIDVersion = 56;

// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd1(0x23, 16, 2);
LiquidCrystal_I2C lcd2(0x25, 16, 2);
LiquidCrystal_I2C lcd3(0x27, 16, 2);
LiquidCrystal_I2C lcd4(0x24, 16, 2);

int dataPin = 4;
int latchPin = 5;
int clockPin = 6;

 
byte leds1 = 0;
byte leds2 = 0;
int bitToSend;
int bitCheck;
int sData;

int j = 0;
int test=5;
String tests= "Hello";
int DID=0;
int DIDpwr;
int DIDlidr;
int DIDpsi;
int DIDgyro ;
char DIDarmcmd[3] ;
int DIDarmenc ;
int DIDarmpwr ;
char DIDwristcmd[3] ;
int DIDwristenc ;
int DIDwristpwr ;
char DIDstatus[9] = "Rio Wait";
char DIDalert[8] ;
int DIDfl ;
int DIDfr ;
int DIDbl ;
int DIDbr ;

// Map of pins to buttons
int pinToButton[13] = {2,3,7,8,9,10,11,12,13,14,15,16,17};

// Last state of the button
int lastButtonState[13] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
 
void setup() 
{
  // Initialize Button Pins
  pinMode(2, INPUT_PULLUP);
  pinMode(3, INPUT_PULLUP);
  pinMode(7, INPUT_PULLUP);
  pinMode(8, INPUT_PULLUP);
  pinMode(9, INPUT_PULLUP);
  pinMode(10, INPUT_PULLUP);
  pinMode(11, INPUT_PULLUP);
  pinMode(12, INPUT_PULLUP);
  pinMode(13, INPUT_PULLUP);
  pinMode(14, INPUT_PULLUP);
  //digitalWrite(14, HIGH );
  pinMode(15, INPUT_PULLUP);
  pinMode(16, INPUT_PULLUP);
  pinMode(17, INPUT_PULLUP);
  
  // Initialize Joystick Library
  Joystick.begin();
  
    // initialize the LCD
  lcd1.begin();
  lcd2.begin();
  lcd3.begin();
  lcd4.begin();

  // Turn on the blacklight and print a message.
  lcd1.backlight();
  lcd2.backlight();
  lcd3.backlight();
  lcd4.backlight();

  //lcd.print("Entering SETUP...");
  lcd1.print("FRC Team #5830");
  lcd2.print("LIFE Engineering");  
  lcd3.print("Driver Interface");
  lcd3.setCursor(0,1);  
  lcd3.print("Device - DID");
  lcd4.print("DID Board v0.");
  lcd4.print(DIDVersion);
  lcd4.setCursor(0,1);
  lcd4.print("Initializing");
  Serial.begin(9600);
  delay(2000);
  lcd4.print(".");
  pinMode(latchPin, OUTPUT);
  pinMode(dataPin, OUTPUT);  
  pinMode(clockPin, OUTPUT);
  lcd4.print(".");
  delay (1000);
  lcd4.print("...");
  //Serial.println("reset");
  leds1 = 0;
  leds2 = 0;
  updateShiftRegister();
  Serial.println("<DIDBoard Ready>");
  //Waiting for connection to DID python
   
  lcd1.clear();
  lcd1.print("Connecting to");
  lcd1.setCursor(0,1);
  lcd1.print("RoboRio");
  delay(1500);
  lcd2.clear();
  lcd3.clear();
  lcd4.clear();
  lcd1.clear();

// Load template fields
  lcd1.print("Pwr ___ Lidr ___");
  lcd1.setCursor(0,1);
  lcd1.print("PSI ___ Gyro ___");
  lcd2.print(" Arm ___ ___ ___");
  lcd2.setCursor(0,1);
  lcd2.print("Wrst ___ ___ ___");
  lcd3.print("Stat - Rio Wait");
  lcd3.setCursor(0,1);
  lcd3.print("Alert - ");
  lcd4.print("FL=__     FR=__");
  lcd4.setCursor(0,1);
  lcd4.print("BL=__     BR=__");

/* This section lists the var holding bins between
 *  pyNetworkTables on the DS and the Arduino DID Board
 *  
 *  DIDpwr    33    !
 *  DIDlidr   34    
 *  DIDpsi    35    
 *  DIDgyro   36    
 *  DIDarmcmd 37    
 *  DIDarmenc 38    
 *  DIDarmpwr 39    
 *  DIDwristcmd 40  
 *  DIDwristenc 41  
 *  DIDwristpwr 42  
 *  DIDstatus 43    
 *  DIDalert  44    
 *  DIDfl   45
 *  DIDfr   46
 *  DIDbl   47
 *  DIDbr   48
 *  
 */

}
 
void loop() 
{
  //lcd.clear();
  //lcd.print("LED.");
  //UpdateLcdDidPwr(test);
  //UpdateLcdDidStatus(tests);
  if (Serial.available() > 0) {
      //Serial.println("Got it");
      int sData = Serial.read();
      //Serial.println(sData);
    
      if (sData > 64 && sData < 91 ){
          // capital letter turn on led
          int bitToSet = sData - 65;
          
          if (bitToSet < 8){
            //bitCheck = bitRead(leds1,bitToSet);
            bitWrite(leds1,bitToSet,1);
          }
          else if ( bitToSet > 7) {
            bitToSet = bitToSet - 8;
            //bitCheck = bitRead(leds2,bitToSet);
            bitWrite(leds2,bitToSet,1);
          }
      }
      else if (sData > 96 && sData < 123 ) {
          // lowercase letter, turn off led
          int bitToSet = sData - 97;
          
          if (bitToSet < 8){
              //bitCheck = bitRead(leds1,bitToSet);
              bitWrite(leds1,bitToSet,0);
          }
          else if ( bitToSet > 7) {
              bitToSet = bitToSet - 8;
              //bitCheck = bitRead(leds2,bitToSet);
              bitWrite(leds2,bitToSet,0);
          }
      }
      else if (sData < 65) {
          
              //bitWrite(leds1,1,0);
              //updateShiftRegister();
              
                if (sData == 33){
                  //DIDpwr
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 250);
                  UpdateLcdDidPwr(DID);
                }
                else if (sData == 34){
                  //DIDlidr
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 999);
                  UpdateLcdDidLidr(DID);
                }
                else if (sData == 35) {
                  //DIDpsi
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 250);
                  UpdateLcdDidPsi(DID);
                }
                else if (sData == 36){
                  //DIDgyro
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 400);
                  UpdateLcdDidGyro(DID);
                }
                else if (sData == 37) {
                  //DIDarmcmd string
                  String DIDarmcmd = Serial.readStringUntil('@');
                  DIDarmcmd = DIDarmcmd.substring(0,3);
                  UpdateLcdDidArmCmd(DIDarmcmd);
                }
                else if (sData == 38) {
                  //DIDarmenc
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 500);
                  UpdateLcdDidArmEnc(DID);
                }
                else if (sData == 39) {
                  //DIDarmpwr
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 100);
                  UpdateLcdDidArmPwr(DID);
                }
                else if (sData == 40){
                  //DIDwristcmd
                  String DIDwristcmd = Serial.readStringUntil('@');
                  UpdateLcdDidWristCmd(DIDwristcmd);
                }        
                else if (sData == 41) {
                  //DIDwristenc
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 999);
                  UpdateLcdDidWristEnc(DID);
                }
                else if (sData == 42) {
                  //DIDwristpwr
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 999);
                  UpdateLcdDidWristPwr(DID);
                }
                else if (sData == 43) {
                  //DIDstatus
                  String DIDstatus = Serial.readStringUntil('@');
                  UpdateLcdDidStatus(DIDstatus);
                }
                else if (sData == 44) {
                  //DIDalert
                  String DIDalert = Serial.readStringUntil('@');
                  UpdateLcdDidAlert(DIDalert);
                }
                else if (sData == 45) {
                  //DIDfl
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 250);
                  UpdateLcdDidFl(DID);
                }
                else if (sData == 46) {
                  //DIDfr
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 250);
                  UpdateLcdDidFr(DID);
                }
                else if (sData == 47 ) {
                  //DIDBL
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 250);
                  UpdateLcdDidBl(DID);
                }
                 else if (sData == 48) {
                  //DIDbr
                  DID = Serial.parseInt();
                  DID = constrain(DID, 0, 250);
                  UpdateLcdDidBr(DID);
                }     
             
      }
    updateShiftRegister();
    delay(100);
  }

  // Read pin values
  for (int index = 0; index < 13; index++)
  {
    //int currentButtonState = !digitalRead(index + 8);
    int currentButtonState = !digitalRead(pinToButton[index]);
    if (currentButtonState != lastButtonState[index])
    {
      Joystick.setButton(index, currentButtonState);
      lastButtonState[index] = currentButtonState;
    }
  }

  
  //leds = 1;
  //updateShiftRegister();
  //delay(500);
  //for (int i = 0; i < 16; i++)
  //{
   // bitSet(leds, i);
    ////lcd.print(i);
    //updateShiftRegister();
    //delay(500);
  //}
  //lcd.clear();
}
 
void updateShiftRegister()
{
   digitalWrite(latchPin, LOW);
   //shiftOut(dataPin, clockPin, LSBFIRST, leds);
   shiftOut(dataPin, clockPin, MSBFIRST, leds2);
   shiftOut(dataPin, clockPin, MSBFIRST, leds1);   
   digitalWrite(latchPin, HIGH);
}

String prepLCD(String prepstr, int max){
    for(int i = 0; i < (max - prepstr.length()+1); i++) {
        prepstr += ' '; 
    }
    return prepstr;
}

void UpdateLcdDidPwr(int DIDpwr)
{
  
  lcd1.setCursor(4,0);
  lcd1.print(prepLCD(String(DIDpwr),3));

}

void UpdateLcdDidLidr(int DIDlidr)
{
  
  lcd1.setCursor(13,0);
  lcd1.print(prepLCD(String(DIDlidr),3));

}

void UpdateLcdDidPsi(int DIDpsi)
{
  
  lcd1.setCursor(4,1);
  lcd1.print(prepLCD(String(DIDpsi),3));

}
void UpdateLcdDidGyro(int DIDgyro)
{
  
  lcd1.setCursor(13,1);
  lcd1.print(prepLCD(String(DIDgyro),3));

}
void UpdateLcdDidArmCmd(String DIDarmcmd)
{
  
  lcd2.setCursor(5,0);
  lcd2.print(prepLCD(String(DIDarmcmd),3));

}
void UpdateLcdDidArmEnc(int DIDarmenc)
{
  
  lcd2.setCursor(9,0);
  lcd2.print(prepLCD(String(DIDarmenc),3));

}
void UpdateLcdDidArmPwr(int DIDarmpwr)
{
  
  lcd2.setCursor(13,0);
  lcd2.print(prepLCD(String(DIDarmpwr),3));

}
void UpdateLcdDidWristCmd(String DIDwristcmd)
{
  
  lcd2.setCursor(5,1);
  lcd2.print(prepLCD(String(DIDwristcmd),3));

}
void UpdateLcdDidWristEnc(int DIDwristenc)
{
  
  lcd2.setCursor(9,1);
  lcd2.print(prepLCD(String(DIDwristenc),3));

}

void UpdateLcdDidWristPwr(int DIDwristpwr)
{
  
  lcd2.setCursor(13,1);
  lcd2.print(prepLCD(String(DIDwristpwr),3));

}

void UpdateLcdDidStatus(String DIDstatus)
{
  lcd3.setCursor(7,0);
  lcd3.print(prepLCD(DIDstatus,10));
}

void UpdateLcdDidAlert(String DIDalert)
{
  lcd3.setCursor(8,1);
  lcd3.print(prepLCD(DIDalert,9));
}

void UpdateLcdDidFl(int DIDfl)
{
  
  lcd4.setCursor(3,0);
  lcd4.print(prepLCD(String(DIDfl),3));

}
void UpdateLcdDidFr(int DIDfr)
{
  
  lcd4.setCursor(13,0);
  lcd4.print(prepLCD(String(DIDfr),3));

}
void UpdateLcdDidBl(int DIDbl)
{
  
  lcd4.setCursor(3,1);
  lcd4.print(prepLCD(String(DIDbl),3));

}
void UpdateLcdDidBr(int DIDbr)
{
  
  lcd4.setCursor(13,1);
  lcd4.print(prepLCD(String(DIDbr),3));

}
