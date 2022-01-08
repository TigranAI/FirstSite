package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tigran.cardcollector.database.entity.*;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.database.repository.UserLevelRepository;
import ru.tigran.cardcollector.database.repository.UserRepository;
import ru.tigran.cardcollector.database.repository.UserStickerRepository;
import ru.tigran.cardcollector.functions.DictionaryHelper;
import ru.tigran.cardcollector.functions.ListHelper;

import java.util.*;

@Controller
@RequestMapping(value = "/top")
public class TopController {
    @Autowired
    private UserLevelRepository userLevelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private UserStickerRepository userStickerRepository;

    @GetMapping
    public String showTop(Model model) {
        List<User> users = userRepository.findAll();
        Hashtable<Long, User> usersTable = ListHelper.ToHashTable(users, item -> item.Id, item -> item);

        List<String> tier4StickerIds = stickerRepository.findAllIdByTier(4);
        List<UserSticker> usersStickers = userStickerRepository.findAllByStickerIds(tier4StickerIds);
        Hashtable<Long, List<UserSticker>> groupedUserStickers = ListHelper.GroupBy(usersStickers, item -> item.UserId);
        DictionaryHelper.ToHashTable(groupedUserStickers, (k, v) -> k, (k, v) -> v).forEach((key, value) -> {
            if (usersTable.get(key).PrivilegeLevel >= 7) groupedUserStickers.remove(key);
        });
        List<Map.Entry<String, Integer>> tier4Top = DictionaryHelper.ToList(groupedUserStickers,
                (k, v) -> usersTable.get(k).Username, (k, v) -> v.size());
        tier4Top.sort(Comparator.comparingInt(Map.Entry::getValue));

        List<UserLevel> userLevels = userLevelRepository.findAll();
        ListHelper.ToList(userLevels, item -> item).forEach(item -> {
            if (usersTable.get(item.UserId).PrivilegeLevel >= 7 || item.TotalExp == 0) userLevels.remove(item);
        });
        userLevels.sort((o1, o2) -> {
            long result = o2.TotalExp - o1.TotalExp;
            if (result > 0) return 1;
            if (result < 0) return -1;
            return 0;
        });
        List<Map.Entry<String, Long>> expTop = ListHelper.ToList(ListHelper.GetRange(userLevels, 0, 5),
                userLevel -> new AbstractMap.SimpleEntry<>(usersTable.get(userLevel.UserId).Username, userLevel.TotalExp));

        model.addAttribute("expTop", expTop);
        model.addAttribute("tier4Top", ListHelper.GetRange(tier4Top, 0, 5));
        model.addAttribute("title", "WyrmSticker | Топ пользователей");
        return "top/index";
    }
}
