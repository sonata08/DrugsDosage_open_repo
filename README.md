
<h1 align="center">Medication Dosage Calculator</h1>

<p align="center" width="100%">
    <img width="20%" src="https://play-lh.googleusercontent.com/6qwq3_uz71TCp1aDMjJMnqYn5gI6lD53FAF5Jwc0j7cLwQjM52t4Pb7Sg4iFUjIOROI=w480-h960-rw">
</p>

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Download](#download)

## General info

This app calculates the appropriate dosage of fever reducers, syrups, and tablets for children and adults. The calculation is based on the user's weight and the medication's active ingredient concentration.

## Technologies

* Kotlin 
* Jetpack Compose
* Firebase (Firestore, Analytics, Crashlytics)
* Hilt
* Room

## Setup

The repository contains two branches:
####  local_datasource
This branch uses a local Kotlin file to store the list of medications. It can be built and run immediately without any additional setup.

#### firebase_datasource
This branch utilizes Firebase as a data source for medication information. It requires a `google-services.json` file to establish a connection to your Firebase project. To use this branch, you'll need to create your own Firebase project and obtain the corresponding `google-services.json` file.


## Download 

[![Get it on Google Play](https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg)](https://play.google.com/store/apps/details?id=org.testresult.drugsdosage)
