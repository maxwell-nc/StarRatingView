# StarRatingView
An Android custom star rating view which support setting

# Usage

-  Just copy in layout
```java
<com.github.maxwell.nc.library.StarRatingView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="17dp"
        android:orientation="vertical"
        app:rating_count="2"
        app:star_count="5"
        app:star_size="28dp" />
```

# Attribute

- `app:rating_count` 	set init rate count
- `app:star_count` 		set hole start count
- `app:star_size` 		set a star size
- `android:orientation`	set star orientation

# Code

- rating
```java
starRatingView.rate(7);
```

- get current rating count
```java
starRatingView.getRatingCount();
```

- get total star count
```java
starRatingView.getStarCount();
```

- set rating listener
```java
starRatingView.setOnRateChangeListener(
		new StarRatingView.OnRateChangeListener() {
            @Override
            public void onRated(int rateCount) {
               //...
            }
        });
```
- see more in sample project

# Preview
![screenshot](https://github.com/maxwell-nc/StarRatingView/blob/master/screenshot.jpg?raw=true)