package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.entity.UserStats;
import ru.tigran.cardcollector.database.repository.UserRepository;
import ru.tigran.cardcollector.database.repository.UserStatsRepository;
import ru.tigran.cardcollector.models.TopDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/top")
@SessionAttributes(names = {"user"})
public class TopController {
    @Autowired
    private UserStatsRepository userStatsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showTop(Model model) {
        Calendar cal = Calendar.getInstance(new Locale("RU"));
        cal.setTime(new Date());
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.minusDays(-dateTime.getDayOfWeek().ordinal() +1);
        dateTime = dateTime.plusDays(14 - week % 2 * 7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        model.addAttribute("endDate", formatter.format(dateTime));

        model.addAttribute("exp", userStatsRepository
                .findTopByExp(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getEarnedExp()))
                .collect(Collectors.toList()));
        model.addAttribute("tier4", userStatsRepository
                .findTopByTier4Stickers(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getEarned4TierStickers()))
                .collect(Collectors.toList()));
        model.addAttribute("roulette", userStatsRepository
                .findTopByRouletteGames(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getRouletteGames()))
                .collect(Collectors.toList()));
        model.addAttribute("ladder", userStatsRepository
                .findTopByLadderGames(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getLadderGames()))
                .collect(Collectors.toList()));
        model.addAttribute("puzzle", userStatsRepository
                .findTopByPuzzleGames(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getPuzzleGames()))
                .collect(Collectors.toList()));
        model.addAttribute("gift", userStatsRepository
                .findTopByGiftsReceived(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getGiftsReceived()))
                .collect(Collectors.toList()));
        model.addAttribute("invite", userStatsRepository
                .findTopByFriendsInvited(PageRequest.of(0, 5))
                .stream()
                .map(item -> new TopDTO(item.getUser().getUsername(), item.getFriendsInvited()))
                .collect(Collectors.toList()));

        if (model.containsAttribute("user")) {
            User oldUser = (User) model.getAttribute("user");
            Optional<User> optionalUser = userRepository.findById(oldUser.getId());
            User user = optionalUser.get();
            if (user.getUserStats() == null) {
                user.setUserStats(new UserStats(user));
                userRepository.save(user);
            }
            model.addAttribute("user", user);
            UserStats stats = user.getUserStats();

            model.addAttribute("expPosition", new TopDTO(user.getUsername(), stats.getEarnedExp(),
                    userStatsRepository.getExpPosition(stats.getEarnedExp(), stats.getLastExpAccrual())));
            model.addAttribute("tier4Position", new TopDTO(user.getUsername(), stats.getEarned4TierStickers(),
                    userStatsRepository.getTier4Position(stats.getEarned4TierStickers(), stats.getLast4TierStickerAccrual())));
            model.addAttribute("roulettePosition", new TopDTO(user.getUsername(), stats.getRouletteGames(),
                    userStatsRepository.getRoulettePosition(stats.getRouletteGames(), stats.getLastRouletteGame())));
            model.addAttribute("ladderPosition", new TopDTO(user.getUsername(), stats.getLadderGames(),
                    userStatsRepository.getLadderPosition(stats.getLadderGames(), stats.getLastLadderGame())));
            model.addAttribute("puzzlePosition", new TopDTO(user.getUsername(), stats.getPuzzleGames(),
                    userStatsRepository.getPuzzlePosition(stats.getPuzzleGames(), stats.getLastPuzzleGame())));
            model.addAttribute("giftPosition", new TopDTO(user.getUsername(), stats.getGiftsReceived(),
                    userStatsRepository.getGiftPosition(stats.getGiftsReceived(), stats.getLastReceivedGift())));
            model.addAttribute("invitePosition", new TopDTO(user.getUsername(), stats.getFriendsInvited(),
                    userStatsRepository.getInvitePosition(stats.getFriendsInvited(), stats.getLastInvitedFriend())));
        }

        return "top/index";
    }
}
