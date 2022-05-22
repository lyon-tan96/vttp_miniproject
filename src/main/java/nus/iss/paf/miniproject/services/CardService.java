package nus.iss.paf.miniproject.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.paf.miniproject.repositories.SearchRepositories;

@Service
public class CardService {

    public static final String CARD_SEARCH = "https://api.pokemontcg.io/v2/cards";

    @Autowired
    private SearchRepositories searchRepo;

    @Transactional
    public List<String> getCards(String cardName, String email) {

        List<String> result = new LinkedList<>();

        String url = UriComponentsBuilder.fromUriString(CARD_SEARCH)
                    .queryParam("q", "name:%s*".formatted(cardName))
                    .queryParam("pageSize", 20)
                    .toUriString();
        
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.printf(">>>>> status: %s", resp.getStatusCode());

        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject cards = reader.readObject();
        JsonArray data = cards.getJsonArray("data");
        for (int i = 0; i < data.size(); i++) {
            JsonObject card = data.getJsonObject(i);
            String image = card.getJsonObject("images").getString("large");
            result.add(image);
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Results not found for %s.".formatted(cardName));
        }

        searchRepo.insertSearchHistory(cardName, email);


        
    return result;

}
}