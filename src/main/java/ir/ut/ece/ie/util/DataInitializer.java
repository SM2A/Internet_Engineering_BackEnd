package ir.ut.ece.ie.util;

import com.google.gson.GsonBuilder;
import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.repository.commodity.CommentRepository;
import ir.ut.ece.ie.repository.commodity.CommentRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {
    private static final String USERS_ENDPOINT = "/api/users";
    private static final String COMMODITIES_ENDPOINT = "/api/commodities";
    private static final String PROVIDERS_ENDPOINT = "/api/providers";
    private static final String COMMENTS_ENDPOINT = "/api/comments";
    private static final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static final CommodityRepository commodityRepository = CommodityRepositoryImpl.getInstance();
    private static final ProviderRepository providerRepository = ProviderRepositoryImpl.getInstance();
    private static final CommentRepository commentRepository = CommentRepositoryImpl.getInstance();

    public static void loadData(String url) throws IOException, InterruptedException {
        readUsersData(url + USERS_ENDPOINT);
        readCommoditiesData(url + COMMODITIES_ENDPOINT);
        readProvidersData(url + PROVIDERS_ENDPOINT);
        readCommentsData(url + COMMENTS_ENDPOINT);
    }

    private static void readUsersData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), User[].class)).toList();
        userRepository.saveAll(users);
    }

    private static void readCommoditiesData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Commodity> commodities = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), Commodity[].class)).toList();
        commodityRepository.saveAll(commodities);
    }

    private static void readProvidersData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Provider> providers = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), Provider[].class)).toList();
        providerRepository.saveAll(providers);
    }

    private static void readCommentsData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Comment> comments = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), Comment[].class)).toList();
        commentRepository.saveAll(comments);
    }

    private static HttpRequest createGetRequest(String uri) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
    }
}
