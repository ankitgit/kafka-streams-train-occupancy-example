#!/usr/bin/env bash

gradle clean shadowJar
docker build . -t train-occupancy-example
