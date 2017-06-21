package com.pooja.weatherappusingjson.Adapter;

import android.content.Context;
import android.icu.text.IDNA;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pooja.weatherappusingjson.R;
import com.pooja.weatherappusingjson.model.InfoWeather;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pooja on 2/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {
    Context context;
    List<InfoWeather>weatherListList;

    public MyAdapter(Context context,List<InfoWeather>weatherList) {
        this.context = context;
        this.weatherListList=weatherList;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.eachrow,parent,false);
        Viewholder viewholder=new Viewholder(view,context);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        InfoWeather infoWeather=weatherListList.get(position);
        holder.lat.setText(infoWeather.getLat());
        holder.lon.setText(infoWeather.getLon());

        holder.id.setText(String.valueOf(infoWeather.getId()));
        holder.main.setText(infoWeather.getMain());
        holder.description.setText(infoWeather.getDescription());
        holder.icon.setText(infoWeather.getIcon());
        holder.base.setText(infoWeather.getBase());

        holder.temp.setText(String.valueOf(infoWeather.getTemp()));
        holder.pressure.setText(String.valueOf(infoWeather.getPressure()));
        holder.humidity.setText(String.valueOf(infoWeather.getHumidity()));
        holder.temp_min.setText(String.valueOf(infoWeather.getTemp_min()));
        holder.temp_max.setText(String.valueOf(infoWeather.getTemp_max()));
        holder.sea_level.setText(String.valueOf(infoWeather.getSea_level()));
        holder.grnd_level.setText(String.valueOf(infoWeather.getGrnd_level()));

        holder.speed.setText(String.valueOf(infoWeather.getSpeed()));
        holder.deg.setText(String.valueOf(infoWeather.getDeg()));

        holder.rain.setText(String.valueOf(infoWeather.getRainvalue3h()));

        holder.all.setText(String.valueOf(infoWeather.getAll()));

        holder.dt.setText(String.valueOf(infoWeather.getDt()));

        holder.message.setText(String.valueOf(infoWeather.getMessage()));
        holder.country.setText(infoWeather.getCountry());
        holder.sunrise.setText(String.valueOf(infoWeather.getSunrise()));
        holder.sunset.setText(String.valueOf(infoWeather.getSunset()));

        holder.obj_id.setText(String.valueOf(infoWeather.getObj_id()));
        holder.name.setText(infoWeather.getName());
        holder.cod.setText(String.valueOf(infoWeather.getCod()));
    }

    @Override
    public int getItemCount() {
        return weatherListList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        Context ctx;
        TextView lat,lon;
        TextView id,main,description,icon;
        TextView base;
        TextView temp,pressure,humidity,temp_min,temp_max,sea_level,grnd_level;
        TextView speed,deg;
        TextView rain;
        TextView all;
        TextView dt;
        TextView message,country,sunrise,sunset;
        TextView obj_id,name,cod;

        public Viewholder(View itemView,Context ctx) {
            super(itemView);
            this.ctx=ctx;
            lat= (TextView) itemView.findViewById(R.id.textView_latitude);
            lon=(TextView)itemView.findViewById(R.id.textView_longitude);
            id=(TextView)itemView.findViewById(R.id.textView_id);
            main=(TextView)itemView.findViewById(R.id.textView_main);
            description=(TextView)itemView.findViewById(R.id.textView_description);
            icon=(TextView)itemView.findViewById(R.id.textView_icon);
            base=(TextView)itemView.findViewById(R.id.textview_base);
            temp=(TextView)itemView.findViewById(R.id.textview_temperature);
            pressure=(TextView)itemView.findViewById(R.id.pressure);
            humidity=(TextView)itemView.findViewById(R.id.humidity);
            temp_min=(TextView)itemView.findViewById(R.id.temp_min);
            temp_max=(TextView)itemView.findViewById(R.id.temp_max);
            sea_level=(TextView)itemView.findViewById(R.id.sea_level);
            grnd_level=(TextView)itemView.findViewById(R.id.grnd_level);
            speed=(TextView)itemView.findViewById(R.id.speed);
            deg=(TextView)itemView.findViewById(R.id.degree);
            rain=(TextView)itemView.findViewById(R.id.rain_3h);
            all=(TextView)itemView.findViewById(R.id.all);
            dt=(TextView)itemView.findViewById(R.id.dt);
            message=(TextView)itemView.findViewById(R.id.message);
            country=(TextView)itemView.findViewById(R.id.country);
            sunrise=(TextView)itemView.findViewById(R.id.sunrise);
            sunset=(TextView)itemView.findViewById(R.id.sunset);
            obj_id=(TextView)itemView.findViewById(R.id.obj_id);
            name=(TextView)itemView.findViewById(R.id.obj_name);
            cod=(TextView)itemView.findViewById(R.id.obj_cod);

        }
    }
}
