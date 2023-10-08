
# ITUnes Search API

The Search API allows you to place search fields in your website to search for content within the iTunes Store and Apple Books Store. You can search for a variety of content; including books, movies, podcasts, music, music videos, audiobooks, and TV shows. You can also call an ID-based lookup request to create mappings between your content library and the digital catalog. Developers may use promotional content in the API, including previews of songs, music videos, album art and App icons only to promote store content and not for entertainment purposes. Use of sound samples and other assets from the API must be proximate to a store badge. See below for terms and conditions.


## Project Set up
- Android Studio Giraffe | 2022.3.1 Patch 2
- Programming Language: Kotlin
- Third Party API tool: Postman v9.1.1
- Gradle Plug in Version: 7.3.1 - > Go to File>Project Structure
- Gradle Version: 8.0
- JDK version: 17 -> Go to File>Settings>Build, Execution and Deployment>Build Tools>Gradle

## Software Design Pattern
MVVM (Model-View-ViewModel)+Clean Architecture

Why MVVM+Clean Architecture(Use Case)? Because it's very good pattern for developing app with bigger and wide functionalities and makes your code easily testable, you can check it on my Test Folder of the project on how i performed unit testing using mockwebservers with local json file. On top of that i also used Dagger Hilt that automatically generates related modules and components which is very at easy to pass values from Data Layer to Domain Layer up to the Presentation Layer.







## Additional Tech Stack Used

    Live Data & ViewModel
    Coroutines/Flow
    Room
    Retrofit & OkHttp
    DaggerHilt
    JUnit4(Unit Testing)
    Github(Version Control)

 
## ITunes API Reference

 [Visit ITunes Search API documentation for more information](https://performance-partners.apple.com/search-api#searching)


```http
GET > https://itunes.apple.com/search?term=m&media=movie&limit=200

```
| Query Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `term` | `String` | The URL-encoded text string you want to search for. For example: jack+johnson. |
| `media` | `String` | The media type you want to search for. For example: movie. The default is all.|
| `limit` | `String` | The number of search results you want the iTunes Store to return. For example: 25. The default is 50 |



## APK file 

 - [Google Drive Link for APK](https://drive.google.com/file/d/13DXDzdXvt9vrBNlXst7iSpXXR6tuTNgX/view?usp=drive_link)

