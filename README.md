# The Pizza Temple

A Team's API for the re-branding project of Engineering-Student-Pizza Company to their new company, The Pizza Temple.

### Overview  
This server was generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

### Technology Stack
- Swagger Version 3.0.13
- Springfox
- Heroku fully cloud deployable
- Database: MongoDB through mLab MongoDB
- Testing: JUnit, Mockito, MockMVC
- Java 8, Maven 3

### Design Document
[Design Doc](https://docs.google.com/document/d/1QJZbcmNr3z5pI0odDU3iSeZgCMg1KFlZzifdSRICHb0/edit?usp=sharing):
Includes links to all post-mortems, additional resources used, 
and any other additional relevant documents.

### Final Project Report
[Report](https://docs.google.com/document/d/1mnl9vtSs68ulHj1y7PeRuFpT7EnlGMQfT8o_MvtCJMQ/edit?usp=sharing)

### Final Presentation Materials

### Live API
[Version 1.0.0](https://pizza-project-cs5500.herokuapp.com/ThePizzaProject/1.0.0/swagger-ui.html#!/developers/getBreadsticksByName)
This is currently running on a free Heroku dyno which may take up to 90 seconds 
for the first request to be processed if the application has not been accessed 
within the past 30 minutes.

### Deployment Instructions (Heroku)
* Install all necessary tools (Heroku CLI, git, Maven, etc.)
* Navigate to the project directory on the command line.
* Create a new Heroku application
  * ```$ heroku create```
* Attach mLab MongoDB to the Heroku app
  * ```$ heroku addons:create mongolab```
  * Your heroku account must be verified in order to add this addon (will require entering credit card information)
* Push local branch to the Heroku app
  * If on a branch of your local repo other than master:
    * ```$ git push heroku 'yourLocalBranchName:master```
  * If on the master branch of your local repo:
    * ```$ git push heroku master```
* Open the deployed app
  * ```$ heroku open```
  * Will need to add '/ThePizzaProject/1.0.0' to the end of the URL provided by Heroku.
    * Example: your-heroku-app-name.herokuapp.com/ThePizzaProject/1.0.0
  
### Deployment Instructions (Local)
* Install MongoDB locally. 
  * [Instructions](https://docs.mongodb.com/manual/installation/)
* Run application with the command:
  * ```$ mvn spring-boot:run ```
* If the local port has not been changed, open in browser with this [link](http://localhost:8080/ThePizzaProject/1.0.0).
 
### Test Instructions
 * Run tests with Maven from the command line.
   * ```$ mvn verify```
________________________________

##### Group Members
* Aayush Shah
  * GitHub username: aayushms93
  * Email: shah.aayus@husky.neu.edu
* Andrew Curtright
  * GitHub username: acurt7
  * Email: curtright.a@husky.neu.edu
* Adamm Norgren
  * GitHub username: anorgren
  * Email: norgren.a@husky.neu.edu

