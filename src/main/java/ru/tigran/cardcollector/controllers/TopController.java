package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/top")
public class TopController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showTop(Model model) {
        List<User> tier4Top = userRepository.findTopByTier4Stickers(PageRequest.of(0, 10));
        Map<Long, Integer> tier4TopCount = new HashMap<>();
        for(User user : tier4Top){
            long count = user.getStickers().stream().filter(item -> item.getSticker().getTier() == 4).count();
            tier4TopCount.put(user.getId(), (int) count);
        }

        model.addAttribute("expTop", userRepository.findTopByExp(PageRequest.of(0, 10)));
        model.addAttribute("tier4Top", tier4Top);
        model.addAttribute("tier4TopCount", tier4TopCount);
        return "top/index";
    }
}
