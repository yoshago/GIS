# GIS
A system for analyzing geographic information of Wi-Fi routers.

# Authors
Yehonatan Shaag, Yishay Seroussi.

# General information
This is a user friendly GUI that can read csv with data about Wi-Fi spots and manipulating the data and exporting it into csv or kml file.

# Detailed explanation:
Import: The system can import csv files from the "Wigle wifi" app, import csv files of the "system special form", or import filter object that exported previoudly by the app.

*Export:*
The system can export a csv file from its database. The Database is of special format that is different from "Wigles wifi" one. The System can also export the Database to kml. The system can also export the current filter of the database for later use.

*Current Data:*
The system shows the user the live state of the Database and information about thr current filter, if any of the files is changed the system will update it and parse it to the screen.

*Filters:* The system enables the user to apply filters to the database. The user can apply as many filters as he want, and of different types ( Date , Location ,specific id). The system enables to combine filters (And/Or) and negate Filters (Not). The system can also "undo" filters to return to previous state.

*Algorithms:* Use algorithm 1 to get the estimated location of a router based on scan locations containing that router. Use algorithm 2 to get the estimated location of a scan based on other scans and a smart algorithm.

for more detailed information see our GIS report
