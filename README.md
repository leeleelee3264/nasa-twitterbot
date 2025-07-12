# nasa-bot

<br> 


This is repository for nasa-bot project. The bot consists of `Twitter API`, `Nasa API` and `Spring framework`. 

 <br> 

## Bot
- [Twitter Link: 오늘의 지구](https://twitter.com/the_earth_today)
- Upcoming: APOD for Korea 


<br>

## Development stack
| stack      | info |
|-----------------|------------|
| Language       |   Java         |
| Framework       |   Spring boot        |
| Resource API | Nasa, Twitter |  
| Server | Ubuntu 20 |  
| Deploy | Docker/Docker-Compose | 
| Scheduler | Spring Scheduler |  


 <br>

# 오늘의 지구

| Start Date      | 2022-12-30                                          |
|-----------------|-----------------------------------------------------|
| End Date        | 2023-01-18                                          |

 <br> 



<img src="https://user-images.githubusercontent.com/35620531/214203015-65b3c0e3-462c-4687-9ec6-ed6f4400f1e9.png" alt="drawing" width="500"/>

<br> 

Earth today is a bot to show how the Earth looks like today. **Feature of the bot is that it's handling animated image instead of simple images.** With the feature, now we can observe the Earth dynamically.


 <img src="https://user-images.githubusercontent.com/35620531/212942085-af5de90b-8004-444d-a1c4-dc4cfe5f08cf.gif" alt="drawing" width="500"/>


Nasa EPIC API provides series of the Earth's images. The bot is collecting the images and makes it to gif image, and tweet it.



 <img src="https://user-images.githubusercontent.com/35620531/214202217-fcae67c4-7d48-4210-a65a-e017da8fd3b4.png" alt="drawing" width="500"/>



<br>

## Project TODO List
- [x] 1st Implement: Spring-boot
- [x] Use Spring scheduler
- [x] Apply Java Lint: sonarlint
- [x] Run command to generate gif using Java Runtime
- [x] Deploy with Docker/Docker-Compose
- [x] 2nd Refactoring: Domain package Structure
- [x] Implement retry logic
- [x] Documentation

<br> 

## Quick Start

### Step 1: Fill up Nasa API key in application.properties
```properties
nasa.api.key=
```

Please generate Nasa API key and copy to application.properties. 

<br> 

> How to generate Nasa API key 

1. Go to [[Nasa API]](https://api.nasa.gov/)
2. In Generate API key section, fill the form and signup. 
3. API key is now generated! 

<br>

 <img src="https://user-images.githubusercontent.com/35620531/214198930-0475ee08-7e68-411f-9963-038e61c33a38.png" alt="drawing" width="500"/>


 <br> 

### Step 2: Apply Twitter developer account
```properties
twitter.consumer.key=
twitter.consumer.secret=
twitter.access.token=
twitter.access.token.secret=
```

Please apply Twitter developer to get consumer key, consumer secret, access token and token secret. 
When finsihing key generation, copy it to application.properties. 

<br> 

> How to apply Twitter developer account 

Please check [[How to apply for a Twitter Developer account]](https://www.extly.com/docs/perfect_publisher/user_guide/tutorials/how-to-auto-post-from-joomla-to-twitter/apply-for-a-twitter-developer-account/#apply-for-a-developer-account) and follow the tutorial to get twitter developer account. After follow the tutorial, you will generate the keys and tokens. 

<br> 

 <img src="https://user-images.githubusercontent.com/35620531/214199877-27bd218c-ac97-4955-b673-ba00524702cf.png" alt="drawing" width="500"/>



 <br> 
 
### Step 3: Build Jar 

```shell
gradle build
```


Please check [[How to install gradle]](https://i5i5.tistory.com/264) to install gradle before running this command. 



<br> 

### Step 4: Run Docker
```shell
# make directory for resources
mkdir /home/dev/study/earth-resources

docker-compose build --pull --no-cache 
docker-compose up -d
```

<br> 

> The bot is scheduled every 13:00 PM. If you want to run bot right after starting jar application, please modify NasaBotApplication.java to code below.

#### NasaBotApplication.java

```java
@SpringBootApplication
@EnableScheduling
public class NasaBotApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(NasaBotApplication.class, args);
        LocalDate date = LocalDate.now().minusDays(2);
        EarthService service = context.getBean(EarthService.class);

        try {
            service.saveImages(date);
            service.tweetEarth(date);

            LoggingUtils.info("Successfully run EarthBot date: {}", date);
        } catch (BotException e) {
            LoggingUtils.error(e);
        }

        // for memory monitoring
        Runtime rT = Runtime.getRuntime();
        ...
    }

}
```


