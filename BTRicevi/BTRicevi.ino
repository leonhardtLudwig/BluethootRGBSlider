/* Riceve messaggi che vengono inviati via Bluetooth HC-05 da una app.
*/

#include <SoftwareSerial.h>
#include <string.h>

#define RXPIN 2


const int RXPin = 2;  // da collegare su TX di HC05
const int TXPin = 3;  // da collegare su RX di HC05

const int REDPin= 11;
const int GREENPin= 10;
const int BLUEPin= 9;


//creo una nuova porta seriale via software
SoftwareSerial myBT = SoftwareSerial(RXPin, TXPin);
//const char *empty = "12345\n";
//char *msgChar = "000000\n";
char msgChar[7];
//msgChar[6] = '\n';
char hexString[3];
//hexString[2] = '\n';

int r = 0;
int g = 0;
int b = 0;

int i = 0;

void setup()
{
  msgChar[6] = '\n';
  hexString[2] = '\n';
  pinMode(RXPin, INPUT);
  pinMode(TXPin, OUTPUT);
  pinMode(REDPin,OUTPUT);
  pinMode(GREENPin,OUTPUT);
  pinMode(BLUEPin,OUTPUT);

  myBT.begin(38400);

  Serial.begin(9600);
}

void loop()
{
  i = 0;
  while (myBT.available()) {
    
    msgChar[i++] = char(myBT.read());
  }
  delay(100); //delay
  //Serial.println(msgChar);

  extractRGB();
  printRGB(r,g,b);
  Serial.println();
  
}

void printRGB(int red, int green, int blue){
  analogWrite(REDPin,red);
  analogWrite(GREENPin,green);
  analogWrite(BLUEPin,blue);
}

void extractRGB(){
    onlyTwo(0);
    r = (int)strtol(hexString, NULL, 16);
    delay(50);
    Serial.print(r);
    Serial.print(", ");

    onlyTwo(2);
    g = (int)strtol(hexString, NULL, 16);
    delay(50);
    Serial.print(g);
    Serial.print(", ");

    onlyTwo(4);
    b = (int)strtol(hexString, NULL, 16);
    delay(50);
    Serial.println(b);
  }

void onlyTwo(int start) {
  hexString[0] = msgChar[start + 0];
  hexString[1] = msgChar[start + 1];
}



