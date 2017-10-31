package com.blogspot.ramannada.movieapp;

import android.os.Parcel;
import android.os.Parcelable;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


/**
 * Created by ramannada on 10/23/2017.
 */

@Table(name = "user", id = "_id")
public class User extends Model implements Parcelable {
    @Column(name = "username")
    public String username;
    @Column(name = "email", index = true)
    public String email;
    @Column(name = "password")
    public String password;
    @Column(name = "gender")
    public String gender;
    @Column(name = "photo")
    public int photo;

    public User(String username, String email, String password, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", photo=" + photo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.gender);
        dest.writeInt(this.photo);
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.gender = in.readString();
        this.photo = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public static User selectUserByEmail(String email) {
        return new Select()
                .from(User.class)
                .where("email = ?", email)
                .executeSingle();
    }

    public static Boolean processUserLogin(String email, String password) {
        User user = selectUserByEmail(email);
        if (user == null) {
           return false;
       } else {
           if (password.equals(user.getPassword())) {
               return true;
           } else {
               return false;
           }
       }
    }
}
