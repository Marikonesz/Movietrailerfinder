package com.example.movietrailerfinder.entities;


import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieTrailers implements Parcelable
{

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("results")
    @Expose
    private List<Trailer> results = null;
    public final static Parcelable.Creator<MovieTrailers> CREATOR = new Creator<MovieTrailers>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieTrailers createFromParcel(Parcel in) {
            return new MovieTrailers(in);
        }

        public MovieTrailers[] newArray(int size) {
            return (new MovieTrailers[size]);
        }

    }
            ;

    protected MovieTrailers(Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        in.readList(this.results, (MovieTrailers.class.getClassLoader()));
    }

    public MovieTrailers() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
