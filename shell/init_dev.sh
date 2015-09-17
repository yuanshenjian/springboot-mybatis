#!/usr/bin/env bash

gradle build
gradle setupUtDb
cd publish
java -jar xgsdk2-flyway-1.0-SNAPSHOT.jar ut


