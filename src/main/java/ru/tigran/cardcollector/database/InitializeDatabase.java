package ru.tigran.cardcollector.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.SpecialOrder;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.SpecialOrderRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;

import java.util.List;

@Component
public class InitializeDatabase {
    @Autowired
    private PackRepository packRepository;
    @Autowired
    private StickerRepository stickerRepository;
    @Autowired
    private SpecialOrderRepository specialOrderRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        cachePackPreviews();
        cacheStickerPreviews();
        cacheStickerForSalePreviews();
        cacheSpecialOrderPreviews();
    }

    private void cacheSpecialOrderPreviews() {
        List<SpecialOrder> orders = specialOrderRepository.findAllUncached();
        for (SpecialOrder specialOrder : orders) {
            String filePath = Utilities.getTelegramFile(specialOrder.getPreviewFileId(), "order");
            specialOrder.setCacheFilePath(filePath);
        }
        specialOrderRepository.saveAll(orders);
    }

    private void cacheStickerForSalePreviews() {
        List<Sticker> stickers = stickerRepository.findAllForSaleUncached();
        for (Sticker sticker : stickers){
            String filePath = Utilities.getTelegramFile(sticker.getForSaleFileId(), "stickerForSale/"+sticker.getPack().getId());
            sticker.setCacheForSaleFilePath(filePath);
        }
        stickerRepository.saveAll(stickers);
    }

    private void cacheStickerPreviews() {
        List<Sticker> stickers = stickerRepository.findAllUncached();
        for (Sticker sticker : stickers){
            String filePath = Utilities.getTelegramFile(sticker.getFileId(), "sticker/"+sticker.getPack().getId());
            sticker.setCacheFilePath(filePath);
        }
        stickerRepository.saveAll(stickers);
    }

    private void cachePackPreviews() {
        List<Pack> packs = packRepository.findAllUncached();
        for (Pack pack : packs) {
            String filePath = Utilities.getTelegramFile(pack.getPreviewFileId(), "pack");
            pack.setCacheFilePath(filePath);
        }

        packRepository.saveAll(packs);
    }

    private void cachePackGifPreviews() {
        List<Pack> packs = packRepository.findAllGifUncached();
        for (Pack pack : packs) {
            String filePath = Utilities.getTelegramFile(pack.getGifPreviewFileId(), "pack");
            pack.setGifCacheFilePath(filePath);
        }

        packRepository.saveAll(packs);
    }
}
