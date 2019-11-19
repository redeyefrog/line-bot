# line-bot
echo line bot or reply different message with keyword.

## Usage
the application use spring boot and add this to pom.xml

    <dependency>
        <groupId>com.linecorp.bot</groupId>
        <artifactId>line-bot-spring-boot</artifactId>
        <version>3.1.0</version>
    </dependency>

### Step1
Line Developer Account:
[Getting started with the Messaging API](https://developers.line.biz/en/docs/messaging-api/getting-started/, "Getting started with the Messaging API")
### Step2
Sign up Heroku:
[Heroku](https://www.heroku.com/, "Cloud Application Platform | Heroku")
### Step3
Deploy on Heroku:
* create new app
![image](https://raw.githubusercontent.com/redeyefrog/pics/master/linebot_1.gif)
* choose deploy method
![image](https://raw.githubusercontent.com/redeyefrog/pics/master/linebot_2.gif)
* set variables
![image](https://raw.githubusercontent.com/redeyefrog/pics/master/linebot_3.gif)
* for line bot and database
![image](https://raw.githubusercontent.com/redeyefrog/pics/master/linebot_4.gif)
* find channel secret and channel access token from your channel
* set webhook url:<br>https://${your_heroku_app_name}.herokuapp.com/callback
![image](https://raw.githubusercontent.com/redeyefrog/pics/master/linebot_5.gif)
![image](https://raw.githubusercontent.com/redeyefrog/pics/master/linebot_6.gif)

## Reference
[sample-spring-boot-echo](https://github.com/line/line-bot-sdk-java/tree/master/sample-spring-boot-echo, "sample-spring-boot-echo")
