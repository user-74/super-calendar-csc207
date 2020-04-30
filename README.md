# Super Calendar 2.0

## Introduction

This project is an implementation of a Calendar program.
It allows multiple users to create and store calendars, events, series of events, and alerts and memos associated with those events.
The user has many ways to search events, such as by tags, date, name, and series.
The user can interact with the program by clicking various buttons on the GUI.
Since the events are stored in a storage file, the user can save them and access them even after closing the program.

## List of Features

### Phase 1
#### Events

Events are created by the user and stored in the calendar. Events have a name, start time and end time. 
For example, a birthday party or a yoga class can be made into an event.

#### Series

Series consist of repetitive events. They can also be created by linking events together.
For example, a daily swimming class at 5 pm.

#### Memos

Memos can be written by the user as a note associated with an event(s). 
For example, a memo such as "bring a towel" can be attached to a beach event.

#### Tags

Tags are short identifiers that can be attached to any events. Multiple events can have the same tag, and an event can have multiple tags. 
For example, the tag "sports" can be applied to a soccer event, a hockey event, and a badminton event. A soccer event can also have the "outdoor" tag.

#### Alerts

Alerts can be set by the user as a reminder. For example, to remind the user that an event is occurring. 
There are two types of alerts:

* Individual alerts, which are singular alerts that will alert the user once. For example, an alert for a doctor's appointment.
* Frequency alerts, which are a series of alerts that will repeatedly alert the user. For example, weekly reminders to take medication.

### Phase Two

#### Main Features

* Multiple Calendars can be created per user. The user can switch between their calendars. 
For example, a user might want to keep their work calendar separate from their book club calendar.
* Multiple users can have the same event. A user can take one of their events and share it to another user. 
For example, a few users may arrange a group meeting together.
* Deletion of events, alerts, memos, etc. For example, if an event is no longer happening, the user will be able to delete it.

#### Extra Feature

* A user can share an entire calendar to another user, such that they can both access the calendar. 
For example, a family could plan a vacation together and want to share family plans with each other.

#### Optional Features

* A graphic user interface (GUI).
* A user can postpone/reschedule an event and also duplicate it. It is also possible to postpone an event indefinitely.
  * Events can be postponed by a specified number of days or indefinitely.
  
## Instructions

### Getting Started

Logging into the Calendar

0. Make sure to mark the folder as sources root in Intellij
1. To start the calendar, build and run Main.java
2. Select create a new user, and input your new user information.
3. Log into the calendar using the user you just created.
4. After logging in, create a new calendar.
5. You will now have access to the SUPER CALENDAR.

Creating your first Event.

1. Click "Create Event"
2. Input name, start time, end time, using the dd/MM/yyyy HH:mm format
3. Click "Create"
4. Click "View Event and Series"
5. Click on your event and click "Select Event"
6. Now you will be able to edit your event, such as creating memos, alerts, etc.

For help with additional features, please refer to the video.

### Important Notes

Please pay attention when entering a correct date format, and enter them carefully, when creating Events, Alerts, etc. 
The correct date format will be shown on the screen whenever one is necessary. 
If an input is incorrect, the program will ask you to try again.

## Group 0267 - Authors

Group Repository: https://markus.teach.cs.toronto.edu/git/csc207-2020-01/group_0267

Name // git username(s)

* Hualong Li // Hualong Li
* YongQuan Lin // YongQuan Lin
* Long Ji // leoji
* Qingjun Lin // Qingjun Lin
* Shimiao Wang // ShimiaoWang
* Wilson Q Gao // gaowilso, user-74  (2 git usernames)
* ShiDong Xu // xushidon
