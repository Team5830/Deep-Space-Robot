/**
 * Displays text sent over the serial port (e.g. from the Serial Monitor) on
 * an attached LCD.
 */
#include <Wire.h> 
#include <LiquidCrystal_I2C.h>

// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd1(0x23, 16, 2);
LiquidCrystal_I2C lcd2(0x25, 16, 2);
LiquidCrystal_I2C lcd3(0x27, 16, 2);
LiquidCrystal_I2C lcd4(0x24, 16, 2);

void setup()
{
	lcd1.begin();
  lcd2.begin();
  lcd3.begin();
  lcd4.begin();

  lcd1.print("Gyro    PSI");
  lcd1.setCursor(0,1);
  lcd1.print("270    120");

  lcd2.print("Arm  Enc  Pwr | Wrist Enc Pwr");
  lcd2.setCursor(0,1);
  lcd2.print("Run  111  8   |  Run   55  5");

  lcd3.print("Status - OK");
  lcd3.setCursor(0,1);
  lcd3.print("Alert - ");

  lcd4.print("Total Pwr | FL=6  FR=5 ");
  lcd4.setCursor(0,1);
  lcd4.print("  55      | BL=5  BR=6 ");

	// Initialize the serial port at a speed of 9600 baud
	//Serial.begin(9600);
}

void loop()
{
	// If characters arrived over the serial port...
	//if (Serial.available()) {
		// Wait a bit for the entire message to arrive
		delay(100);
		// Clear the screen
		//lcd.clear();

		// Write all characters received with the serial port to the LCD.
		while (Serial.available() > 0) {
			lcd1.write(Serial.read());
		}
	}
}
