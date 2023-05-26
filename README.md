# weather-prediction

Backend service to fetch weather prediction. This is a simple spring boot application build to serve the list of weather prediction data for next couple of days.
It connect to openweathermap internally to fetch data. Read more on openweathermap [here](https://openweathermap.org/)

---
## Documentation
The request from the UI will be received and validated against the different mandatory parameters. The application will call openweathermap and collect weather data and process the data to understand the climate and introduces different comments according to the weather. the collective response will be sent back to the requested end.
![flow chart](https://github.com/ShreedharBhat090/weather-prediction/blob/develop/prediction/flowchart.jpg?raw=true)


## Installation
* Clone the repository into your local. 
* Run mvn clean install using command prompt. 
* Right click on PredictionApplication.java and run the file.
* To deploy the api to docker, just run mvn package command and then docker compose up command
 

