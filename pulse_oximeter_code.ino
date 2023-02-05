#include <Arduino.h>
#include <Wire.h>
#include "MAX30100_PulseOximeter.h"
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include "ESP8266WebServer.h"
#include <FirebaseArduino.h>

#define REPORTING_PERIOD_MS 1000
#define FIREBASE_HOST "vitalpatientdata-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "yhFfhWnKD3p8YAHlzXn9GXflZWCRV71dGSWt2zjj"

#define WIFI_SSID "Unknown Person"
#define WIFI_PASSWORD "mohit@#1234"
// int outputpin= A0;
PulseOximeter pox;

unsigned long previousMillis = 0;
const long interval = 1000;
volatile boolean heartBeatDetected = false;

void onBeatDetected() {
  heartBeatDetected = true;
  Serial.println("Beat!");
}

void setup() {
  Serial.begin(115200);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

  if (Firebase.failed()) {
    Serial.print("setting /message failed:");
    Serial.println(Firebase.error());
  }

  if (!pox.begin()) {
    Serial.println("FAILED");
    for (;;)
      ;
  } else {
    Serial.println("SUCCESS");
  }

  pox.setOnBeatDetectedCallback(onBeatDetected);
}

void pulseoxi() {

  float BPM = pox.getHeartRate();
  float SpO2 = pox.getSpO2();

  if (heartBeatDetected && BPM != 0) {

    if (SpO2 > 0) {


      Firebase.setFloat("users/-NNT0VzdRqytGN4jOKMf/heartrate", bpm);
      Firebase.setFloat("users/-NNT0VzdRqytGN4jOKMf/spo2", SpO2);

      Serial.print("data on");
    }
  }
}

void loop() {
// int celsius = analogRead(outputpin)*0.48828125;
// Firebase.setFloat("/Temp", cel);
// delay(1000);
  pox.update();

  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    pox.shutdown();
    pulseoxi();
    pox.resume();
    previousMillis = currentMillis;
  }
}