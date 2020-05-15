# Headspace Code Challenge #
 A small Android app to display Hi-Resolution images from [Lorem Picsum](https://picsum.photos)

----------
## Usage ##

App launches and fetches 30 images from Lorum Picsum.

![Home Screen](/assets/home_screen.png "Home Screen")

Use the Refresh icon to pull an additional 30 images

#### You may tap on any image to get the author and the dimentions of the photo ####

![Sort Pane](/assets/add_info.png "Additional Info")

## Dependencies / Patterns ##
*Requires Android Nougat 6.0 (Min API 24) or later
- MVVM
- Retrofit2 / OkHttp / Moshi
- [Glide](https://bumptech.github.io/glide/)
- LiveData
- Kotlin Coroutines


## Possible Expansion Plans ##
1. Longpress to launch a fullsize or link to image's marketplace
2. Prerender Images for faster loading
3. Cache images locally
4. Infinite Scrolling
5. Swipe down to refresh