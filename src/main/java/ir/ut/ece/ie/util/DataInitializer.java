package ir.ut.ece.ie.util;

import com.google.gson.GsonBuilder;
import ir.ut.ece.ie.api.model.comment.CommentDTO;
import ir.ut.ece.ie.api.model.commodity.CommodityDTO;
import ir.ut.ece.ie.api.mapper.CommentMapper;
import ir.ut.ece.ie.api.mapper.CommodityMapper;
import ir.ut.ece.ie.domain.comment.Comment;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.Discount;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.repository.comment.CommentRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.user.DiscountRepository;
import ir.ut.ece.ie.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final CommodityRepository commodityRepository;
    private final ProviderRepository providerRepository;
    private final CommentRepository commentRepository;
    private final DiscountRepository discountRepository;
    private final CommodityMapper commodityMapper;
    private final CommentMapper commentMapper;
    private static final String USERS_ENDPOINT = "/api/users";
    private static final String COMMODITIES_ENDPOINT = "/api/v2/commodities";
    private static final String PROVIDERS_ENDPOINT = "/api/v2/providers";
    private static final String COMMENTS_ENDPOINT = "/api/comments";
    private static final String DISCOUNTS_ENDPOINT = "/api/discount";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            loadData("http://5.253.25.110:5000");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData(String url) throws IOException, InterruptedException {
        readUsersData(url + USERS_ENDPOINT);
        readProvidersData(url + PROVIDERS_ENDPOINT);
        readCommoditiesData(url + COMMODITIES_ENDPOINT);
//        readCommentsData(url + COMMENTS_ENDPOINT);
        readDiscountsData(url + DISCOUNTS_ENDPOINT);
    }

    private void readUsersData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), User[].class)).toList();
        userRepository.saveAll(users);
    }

    private void readCommoditiesData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Commodity> commodities = Arrays.stream(
                        new GsonBuilder().create().fromJson(response.body(), CommodityDTO[].class))
                .map(commodityMapper::toModel)
                .toList();
        commodityRepository.saveAll(commodities);
    }

    private void readProvidersData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Provider> providers = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), Provider[].class)).toList();
        providerRepository.saveAll(providers);
    }

    private void readCommentsData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        List<Comment> comments = Arrays.stream(
                        new GsonBuilder().create().fromJson(response.body(), CommentDTO[].class))
                .map(commentMapper::toModel)
                .toList();
        commentRepository.saveAll(comments);
    }

    private void readDiscountsData(String uri) throws IOException, InterruptedException {
        HttpRequest request = createGetRequest(uri);
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        List<Discount> discounts = Arrays.stream(new GsonBuilder().create().fromJson(response.body(), Discount[].class)).toList();
        discountRepository.saveAll(discounts);
    }

    private HttpRequest createGetRequest(String uri) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
    }
}
