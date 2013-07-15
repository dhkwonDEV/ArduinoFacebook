
char operation = 0;
 
void setup() {
 Serial.begin(9600);
 pinMode(13, OUTPUT);
}
void loop() {
 
 //Read from Serial if Data is Available
 if (Serial.available() > 0)
 {
   operation = Serial.read();
 }
//LED Off
 if(operation == '0')
   digitalWrite(13, LOW);
//LED On
 if(operation == '1')
   digitalWrite(13, HIGH); 

}
