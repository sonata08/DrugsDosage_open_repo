# Firebase Datasource Branch

## Overview

The `firebase_datasource` branch leverages Firebase as a backend service to store and retrieve medication data. This approach offers several advantages, including real-time updates, scalability, and the ability to manage data centrally.

## Prerequisites

To effectively utilize this branch, you'll need to:

- Have a Firebase account.
- Be familiar with Firebase Cloud Firestore 

## Setup

1. [Add Firebase](https://firebase.google.com/docs/android/setup) to your project
	- Create a Firebase project
	- Register your app with [Firebase](https://console.firebase.google.com/)
	- Add a Firebase configuration file (`google-services.json`) 
	- Add Firebase SDKs for the Cloud Firestore, Crashlytics and Analytics libraries

1. **[Set Up](https://firebase.google.com/docs/firestore/quickstart#android) Firebase Cloud Firestore:**
    
    - Create a Cloud Firestore database within your Firebase project
    - Create a new collections and a documents for the medicines

## Data Structure

The Firestore database is structured to store medicine data in a hierarchical manner.

### Top Level

- **Collections** to store medicine data in different languages (e.g., `drugs_en`, `drugs_ru`, `drugs_uk`)

### Document Level

Within a collection, each document represents a specific medicine. For example:

- **Document:** `Ibuprofen_100mg_5ml`

### Field Level

Each document contains the following fields:

- **activeIngredient:** The name of the active ingredient (e.g., "Ibuprofen")
- **activeIngredientAmountMg:** The amount of active ingredient in milligrams per unit of medicine (e.g., 100)
- **info:** Additional information about the medication
- **maxDailyDose:** The maximum daily dose of the active ingredient (e.g., 30)
- **maxDosesPerDay:** The maximum number of doses per day (e.g., 3).
- **medicineAmountMl:** The amount of medicine in milliliters (e.g., 5).
- **minDosingInterval:** The minimum dosing interval in hours (e.g., 6).
- **name:** The name of the medicine (e.g., "Ibuprofen").
