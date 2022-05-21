package ru.tigran.cardcollector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;
import ru.tigran.cardcollector.database.repository.PackRepository;
import ru.tigran.cardcollector.database.repository.StickerRepository;
import ru.tigran.cardcollector.enums.RecacheType;

@Controller
@RequestMapping("/recache")
public class CacheController {
    @Autowired
    PackRepository packRepository;

    @Autowired
    StickerRepository stickerRepository;

    @ResponseBody
    @PostMapping(params = {"packId", "type"})
    public HttpStatus recachePack(Integer packId, RecacheType type){
        System.out.println("here");
        Pack pack = packRepository.getById(packId);
        try {
            switch (type) {
                case UploadPack:
                    cachePackStickers(pack);
                    break;
                case UploadPackGifPreview:
                    cachePackGifPreview(pack);
                    break;
                case UploadPackPreview:
                    cachePackPreview(pack);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }

        packRepository.save(pack);
        return HttpStatus.OK;
    }

    @ResponseBody
    @PostMapping(params = {"stickerId", "type"})
    public HttpStatus recacheSticker(Long stickerId, RecacheType type) {
        Sticker sticker = stickerRepository.getById(stickerId);
        try {
            switch (type) {
                case UploadSticker:
                    cacheSticker(sticker);
                    break;
                case UploadForSaleSticker:
                    cacheForSaleSticker(sticker);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }

        stickerRepository.save(sticker);
        return HttpStatus.OK;
    }

    private void cachePackStickers(Pack pack) {
        for (Sticker sticker : pack.getStickers())
            cacheSticker(sticker);
    }

    private void cachePackGifPreview(Pack pack) {
        String filePath = Utilities.getTelegramFile(pack.getGifPreviewFileId(), "pack");
        pack.setGifCacheFilePath(filePath);
    }

    private void cachePackPreview(Pack pack) {
        String filePath = Utilities.getTelegramFile(pack.getPreviewFileId(), "pack");
        pack.setCacheFilePath(filePath);
    }

    private void cacheSticker(Sticker sticker) {
        String filePath = Utilities.getTelegramFile(sticker.getFileId(), "sticker/"+sticker.getPack().getId());
        sticker.setCacheFilePath(filePath);
    }

    private void cacheForSaleSticker(Sticker sticker) {
        String filePath = Utilities.getTelegramFile(sticker.getForSaleFileId(), "stickerForSale/"+sticker.getPack().getId());
        sticker.setCacheForSaleFilePath(filePath);
    }
}
