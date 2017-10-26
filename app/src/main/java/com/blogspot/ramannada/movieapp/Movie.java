package com.blogspot.ramannada.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


/**
 * Created by ramannada on 10/23/2017.
 */

@Table(name = "movie", id = "_id")
public class Movie extends Model implements Parcelable {
    @Column(name = "title", index = true)
    public String title;
    @Column(name = "director")
    public String director;
    @Column(name = "duration")
    public String duration;
    @Column(name = "release_date")
    public String releaseDate;
    @Column(name = "poster")
    public int poster;
    Long id;

    public Movie(String title, String director, String duration, String releaseDate, int poster) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.poster = poster;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.director);
        dest.writeString(this.duration);
        dest.writeString(this.releaseDate);
        dest.writeInt(this.poster);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.director = in.readString();
        this.duration = in.readString();
        this.releaseDate = in.readString();
        this.poster = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public static List<Movie> getAllMovie() {
        return new Select()
                .from(Movie.class)
                .orderBy("title ASC")
                .execute();
    }
}
