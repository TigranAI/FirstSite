package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.SecretSessionKey;
import ru.tigran.cardcollector.database.repository.SecretSessionKeyRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/events")
public class EventController {
    private final ArrayList<String> symbols = new ArrayList<>(Arrays.asList(
            "a","A","b","B","c","C","d","D","e","E","f","F","g","G","h","H","i","I","j","J","k","K","l","L","m","M","n",
            "N","o","O","p","P","q","Q","r","R","s","S","t","T","u","U","v","V","w","W","x","X","y","Y","z","Z","1","2",
            "3","4","5","6","7","8","9","0"));
    public Hashtable<String, SseEmitter> emitters = new Hashtable<>();
    @Autowired
    private SecretSessionKeyRepository secretSessionKeyRepository;

    @CrossOrigin
    @GetMapping(value = "/login", consumes = MediaType.ALL_VALUE)
    public SseEmitter login() {
        SecretSessionKey secretKey = new SecretSessionKey(generateString(6));

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        try{
            emitter.send(SseEmitter.event().name("INIT").data(secretKey.getSecretKey()));
        } catch (IOException e){
            emitters.remove(secretKey.getSecretKey());
        }

        emitter.onCompletion(() -> emitters.remove(secretKey.getSecretKey()));

        if (emitters.containsKey(secretKey.getSecretKey()))
            emitters.get(secretKey.getSecretKey()).complete();
        emitters.put(secretKey.getSecretKey(), emitter);
        secretSessionKeyRepository.save(secretKey);
        return emitter;
    }

    @PostMapping(value = "/login")
    public HttpStatus dispatchLogin(@RequestParam String userId, @RequestParam String secretKey){
        SseEmitter emitter = emitters.get(secretKey);
        Optional<SecretSessionKey> result = secretSessionKeyRepository.findById(secretKey);
        AtomicReference<HttpStatus> status = new AtomicReference<>(HttpStatus.BAD_REQUEST);
        result.ifPresent((secretSessionKey) -> {
            try {
                emitter.send(SseEmitter.event().name("LoginData").data(new Object[]{userId, secretKey}));
                emitter.complete();
                status.set(HttpStatus.OK);
            } catch (IOException e) {
                secretSessionKeyRepository.deleteById(secretKey);
                emitter.complete();
            }
        });
        return status.get();
    }

    private String generateString(int size){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<size; ++i)
            sb.append(symbols.get(Utilities.rnd.nextInt(symbols.size())));
        return sb.toString();
    }
}
