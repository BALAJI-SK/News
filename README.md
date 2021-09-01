# News App, Stage 1

*Created as part of Udacity's Android Basics by Google Nanodegree Program*
____________

The goal is to create a News Feed app which gives a user regularly-updated news from the internet related to a particular topic, person, or location. The presentation of the information as well as the topic is up to you.
____________

# Project Specifications

## Layout

1. **Main Screen**. App contains a main screen which displays multiple news stories.
2. **List Item Contents**.
	* Each list item on the main screen displays relevant text and information about the story.
	* The title of the article and the name of the section that it belongs to are required field.
	* If available, author name and date published should be included. Please note not all responses will contain 			these pieces of data, but it is required to include them if they are present.

3. **Best practices**. The code adheres to all of the following best practices:
   * Text sizes are defined in sp.
   * Lengths are defined in dp.
   * Padding and margin is used appropriately, such that the views are not crammed up against each other.
   
## Functionnality

1. **Main Screen Updates**. Stories shown on the main screen update properly whenever new news data is fetched from the API.
2. **Errors**. The code runs without errors.
3. **Story Intents**. Clicking on a story uses an intent to open the story in the user’s browser.
4. **API Query**. App queries the content.guardianapis.com api to fetch news stories related to the topic chosen by the student, using either the ‘test’ api key or the student’s key.
5. **JSON Parsing**. The JSON response is parsed correctly, and relevant information is stored in the app.
6. **No Data Message**. When there is no data to display, the app shows a default TextView that informs the user how to populate the list.
7. **Response Validation**. The app checks whether the device is connected to the internet and responds appropriately. The result of the request is validated to account for a bad server response or lack of server response.
8. **Use of Loaders**. Networking operations are done using a Loader rather than an AsyncTask.
9. **External Libraries and Packages**. The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for the core functionality will not be permitted to complete this project.

____________

NewsApp stage1 - NoData


<img src="https://user-images.githubusercontent.com/60575418/131625433-ca8c9c89-a7b0-49ba-aa2c-c385beefd784.png" width=200 heigth=300/>  <img src ="https://user-images.githubusercontent.com/60575418/131625441-b908ce5d-a34d-41ed-b687-bd6b3f77bdef.png" width=200 heigth=300 />   <img src="https://user-images.githubusercontent.com/60575418/131625436-785ba0cf-837c-43d6-be6b-7306f73dc909.png" width=200 heigth=300 />  
<img src="https://user-images.githubusercontent.com/60575418/131625445-badc5824-42af-4aea-a90f-88798d9d5f1d.png" width=200 heigth=300 /><img src="https://user-images.githubusercontent.com/60575418/131625416-c69b65fa-c59b-46b8-bad5-5d2652314fa2.png" width=200 heigth=300 />  <img src="https://user-images.githubusercontent.com/60575418/131625429-374bf7dc-8ef2-4ef5-8293-c5a02ab03a2e.png" width=200 heigth=300 />
