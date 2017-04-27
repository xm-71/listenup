package edu.iupui.jamcanno.listenup;

import edu.iupui.jamcanno.listenup.model.Tag;
import edu.iupui.jamcanno.listenup.model.Podcast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by alexc on 4/3/2017.
 */

public interface GPodderAPI {

    String BASE_URL = "http://gpodder.net";

    @GET("api/2/tags/{quantity}.json")
    Call<Tag[]> getTopTags(@Path("quantity") String quantity);

    @GET("api/2/tag/{tag}/{quantity}.json")
    Call<Podcast[]> getTaggedPodcasts(@Path("tag") String tag, @Path("quantity") String quantity);

    @GET("/api/2/data/podcast.json")
    Call<Podcast[]> getPodcastInfo(@Path("url") String url);

    //mygpo_link

    @GET("api/2/devices/{username}.json")
    Call<String> getDevices(@Path("username") String username);

    @POST("api/2/auth/{username}.json")
    Call<String> getLogin(@Path("username") String username);

    @GET("/subscriptions/{username}.json")
    Call<String> getSubscriptions(@Path("username") String username);



}
