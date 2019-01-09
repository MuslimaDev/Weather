
package com.example.user.weather.models.currentWeatherModels;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("Direction")
    
    private Direction direction;
    @SerializedName("Speed")
    
    private com.example.user.weather.models.сurrentWeatherModels.Speed speed;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public com.example.user.weather.models.сurrentWeatherModels.Speed getSpeed() {
        return speed;
    }

    public void setSpeed(com.example.user.weather.models.сurrentWeatherModels.Speed speed) {
        this.speed = speed;
    }

}
