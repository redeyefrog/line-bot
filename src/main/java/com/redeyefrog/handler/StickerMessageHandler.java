package com.redeyefrog.handler;

import com.linecorp.bot.model.message.StickerMessage;
import com.redeyefrog.enums.Stickers;
import org.springframework.stereotype.Service;

@Service
public class StickerMessageHandler extends CommonHandler {

    public StickerMessage getRandomSticker() {
        Stickers sticker = Stickers.random();

        return StickerMessage.builder().packageId(sticker.getPkgId()).stickerId(sticker.getStkId()).build();
    }

}
