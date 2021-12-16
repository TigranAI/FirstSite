package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tigran.cardcollector.database.entity.LogsItem;
import ru.tigran.cardcollector.database.repository.LogsItemRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(value = "/database")
public class DataBaseController {
    @Autowired
    private LogsItemRepository logsItemRepository;

    @PostMapping(value = "/logs", produces = "application/json")
    public @ResponseBody LogsItem getLogsItem(@RequestBody String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(dateStr.split("=")[1]);
            Optional<LogsItem> result = logsItemRepository.findById(date);
            LogsItem entity = result.orElseGet(()->{
                LogsItem newItem = new LogsItem();
                newItem.setDate(date);
                return logsItemRepository.save(newItem);
            });
            return entity;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
