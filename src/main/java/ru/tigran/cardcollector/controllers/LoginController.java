package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ru.tigran.cardcollector.Config;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.UserRepository;
import ru.tigran.cardcollector.others.ExtendedEmitter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@SessionAttributes(names = {"user"})
public class LoginController {

    private final String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ1234567890";
    public Hashtable<String, ExtendedEmitter> emitters = new Hashtable<>();

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public String login(@RequestParam String key, Model model) {
        if (!emitters.containsKey(key)) return "redirect:/";

        ExtendedEmitter emitter = emitters.get(key);
        emitter.complete();

        Optional<User> userOptional = userRepository.findById(emitter.getUserId());
        if (userOptional.isEmpty()) return "redirect:/";

        model.addAttribute("user", userOptional.get());
        return "redirect:/collection";
    }

    @GetMapping()
    public String login(Model model) {
        if (model.containsAttribute("user")) return "redirect:/collection";

        model.addAttribute("botName", Config.get("telegram.bot.name"));
        return "login";
    }

    @CrossOrigin
    @GetMapping(value = "/event", consumes = MediaType.ALL_VALUE)
    public SseEmitter login() {
        String key = generateString(8);

        ExtendedEmitter emitter = new ExtendedEmitter(Long.MAX_VALUE);
        try {
            emitter.send(SseEmitter.event().name("INIT").data(key));
        } catch (IOException e) {
            emitters.remove(key);
        }

        emitter.onCompletion(() -> emitters.remove(key));
        if (emitters.containsKey(key)) emitters.get(key).complete();

        emitters.put(key, emitter);
        return emitter;
    }

    @PostMapping(value = "/event")
    @ResponseBody
    public HttpStatus dispatchLogin(@RequestParam String userId, @RequestParam String key, HttpServletRequest request) {
        if (!request.getRemoteAddr().equals(request.getLocalAddr())) return HttpStatus.BAD_REQUEST;
        if (!emitters.containsKey(key)) return HttpStatus.BAD_REQUEST;

        ExtendedEmitter emitter = emitters.get(key);
        try {
            emitter.setUserId(Long.parseLong(userId));
            emitter.send(SseEmitter.event().name("confirm").data(key));
            return HttpStatus.OK;
        } catch (IOException | NumberFormatException e) {
            emitter.complete();
        }
        return HttpStatus.OK;
    }

    private String generateString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            int symbolIndex = Utilities.rnd.nextInt(alphabet.length());
            char symbol = alphabet.charAt(symbolIndex);
            sb.append(symbol);
        }
        return sb.toString();
    }
}
