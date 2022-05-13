package nus.iss.paf.miniproject.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Card {

    private static String[] IMAGES = {

    };

    private String cardName;
    private List<String> images = new LinkedList<>();

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImages(String img) {
        this.images.add(img);
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "Pokemon [images=" + images + ", name=" + cardName + "]";

        }

    public static Card create(String json) throws IOException {
        Card c = new Card();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            c.setCardName(o.getString("name"));

            for (String i: IMAGES)
                o = o.getJsonObject(i);
            List<String> l = o.values().stream()
                            .filter(v -> v != null)
                            .map(v -> v.toString())
                            .toList();
            c.setImages(l);
        }
        return c;
    }

    
}
