# -*- coding: utf-8 -*-

import RPi.GPIO as GPIO
import time
import Adafruit_DHT
import pyrebase

config = {
  "apiKey": "AIzaSyCQwXuY_eI_79uyPdrPxIc36IK-cTYiFNAI",
  "authDomain": "rpi-dht-monitor-fe845.firebaseapp.com",
  "databaseURL": "https://rpi-dht-monitor-fe845-default-rtdb.firebaseio.com/",
  "storageBucket": "rpi-dht-monitor-fe845.appspot.com"
}

firebase = pyrebase.initialize_app(config)
db = firebase.database()

sensor = Adafruit_DHT.DHT11
GPIO.setmode(GPIO.BCM)
pin = 4

while True:
    humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)
#    GPIO.setwarnings(False)
    if humidity is not None and temperature is not None:
        print('Temp={0:0.1f}*C Humidity={1:0.1f}%'.format(temperature, humidity))
        data = {"temperature": temperature, "humidity": humidity}
        db.child("dht11").push(data)
        db.update(data)
    else:
        print('Failed to get reading. Try again!')
    time.sleep(5) # envoie les données toutes les 5 secondes