package com.example.user.weather.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.weather.R;
import com.example.user.weather.models.forecastModels.DailyForecast;
import com.example.user.weather.utils.Constans;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterForDailyForecast extends ArrayAdapter {
    private Context context;
    private List<DailyForecast> list;

    private class ViewHolder {
        TextView date, maximum, minimum;
        ImageView image;
    }

    public AdapterForDailyForecast(@NonNull Context context, List<DailyForecast> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.daily_activity, parent, false);
            holder.date = convertView.findViewById(R.id.date);
            holder.maximum = convertView.findViewById(R.id.maximum);
            holder.minimum = convertView.findViewById(R.id.minimum);
            holder.image = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
            Log.d("getWeatherMore", holder.toString());
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DailyForecast model = (DailyForecast) getItem(position);

        if (model != null) {
            holder.date.setText(model.getDate() != null ? getFormattedDate(model.getDate()) : "");
            holder.maximum.setText(model.getTemperature().getMaximum().getValue().toString() + "°" + model.getTemperature().getMaximum().getUnit());
            holder.minimum.setText(model.getTemperature().getMinimum().getValue().toString() + "°" + model.getTemperature().getMinimum().getUnit());
            int icon = model.getDay().getIcon();

            String imageUrl;
            if (icon < 10) {
                imageUrl = String.format(Constans.ICONS_URL, icon);
            } else {
                imageUrl = String.format(Constans.ICONS_URLMORE, icon);
            }
            Picasso.get().load(imageUrl).into(holder.image);
        }
        return convertView;
    }

    private static Date getDate(String stringDate) {
        if (stringDate == null) {
            throw new IllegalArgumentException("Parsing error");
        }
        Locale locale = new Locale("en");
        stringDate = stringDate.substring(0, 20);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale);
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parsing error");
        }
    }

    public static String getFormattedDate(String resourceDate) throws IllegalArgumentException {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, EEEE", new Locale("en"));
        Date date = getDate(resourceDate);
        return formatter.format(date);
    }

}