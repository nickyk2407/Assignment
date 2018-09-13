package com.example.nicky.assignment.network;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NICKY on 13-09-2018.
 */
public class Planets implements Parcelable {
    private int id;
    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private String created;
    private String edited;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public void setRotation_period(String rotation_period) {
        this.rotation_period = rotation_period;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected Planets(Parcel in) {
        id = in.readInt();
        name = in.readString();
        rotation_period = in.readString();
        orbital_period = in.readString();
        diameter = in.readString();
        climate = in.readString();
        gravity = in.readString();
        terrain = in.readString();
        surface_water = in.readString();
        population = in.readString();
        created = in.readString();
        edited = in.readString();
        url = in.readString();
    }

    public static final Creator<Planets> CREATOR = new Creator<Planets>() {
        @Override
        public Planets createFromParcel(Parcel in) {
            return new Planets(in);
        }

        @Override
        public Planets[] newArray(int size) {
            return new Planets[size];
        }
    };

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(rotation_period);
        parcel.writeString(orbital_period);
        parcel.writeString(diameter);
        parcel.writeString(climate);
        parcel.writeString(gravity);
        parcel.writeString(terrain);
        parcel.writeString(surface_water);
        parcel.writeString(population);
        parcel.writeString(created);
        parcel.writeString(edited);
        parcel.writeString(url);

    }
}
