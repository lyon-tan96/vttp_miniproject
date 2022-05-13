package nus.iss.paf.miniproject.models;

import java.util.UUID;

import org.springframework.util.MultiValueMap;

public class ConvertUtils {

    public static User convert(MultiValueMap<String, String> form) {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString().substring(0, 8));
        user.setEmail(form.getFirst("email"));
        user.setName(form.getFirst("name"));
        user.setPassword(form.getFirst("password"));

        return user;
    }

    public static Card convertCard(MultiValueMap<String,String> form) {
        Card card = new Card();
        card.setCardName(form.getFirst("fname"));
        return card;
    }
    
}
