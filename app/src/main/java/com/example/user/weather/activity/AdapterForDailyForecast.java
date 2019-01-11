/*
package com.example.user.weather.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.weather.R;
import com.example.user.weather.models.forecastModels.DailyForecast;
import com.example.user.weather.models.forecastModels.Headline;
import com.example.user.weather.utils.Constans;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForDailyForecast extends ArrayAdapter {
    Context context;
    List<DailyForecast> list;
    Headline headline;


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

            holder.firstDay = convertView.findViewById(R.id.firstDay);
            holder.firstDayIcon = convertView.findViewById(R.id.firstDayIcon);
            holder.firstDayMax = convertView.findViewById(R.id.firstDayMax);
            holder.firstDayMin = convertView.findViewById(R.id.firstDayMin);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DailyForecast model = (DailyForecast) getItem(position);

        if (model != null) {
            holder.firstDay.setText(String.valueOf(headline.getEffectiveDate()));
            holder.firstDayMin.setText(String.valueOf(model.getTemperature().getMaximum().getValue().toString() + " " + model.getTemperature().getMaximum().getUnit()));
            holder.firstDayMin.setText(String.valueOf(model.getTemperature().getMinimum().getValue().toString() + " " + model.getTemperature().getMinimum().getUnit()));
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

    private class ViewHolder {
        TextView firstDay, firstDayIcon, firstDayMax, firstDayMin;
        ImageView image;

    }
}
*/
