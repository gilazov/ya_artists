## Я.Исполнители - android приложение показывает список исполнителей и информацию об исполнителе

## User interface
### The main screen

<img src="/artist_preview_screenshot.jpg" width="320">

###The second preview screen

<img src="/artists_screenshot.jpg" width="320">

###Состав:
+ package naming and layout naming by screens
+ RxJava
+ Retrofit 2
+ Gson
+ Shared element transition
+ MVP 
+ Glide image loading

### Model 
 + retrofit + rxjava + gson
 + парсится список исполнителей яндекс музыки с каверами

### Main Activity
  + выступает в роли контейнера для фрагментов, класс UINavigator - управляет фрагментами

### Cписок исполнителей
  + MVP 
  + данные подгружаются через rx 

### Shared Element Transition
 +  между фрагментами

### ImageLoader
+ обертка над glide 
 
####P.S.
Советы и предложения по улучшению кода с удовольствием принимаются
