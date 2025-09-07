package com.mygame.dungeon_hero.assetManger;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import lombok.var;

public class PackTool {
    public static void main(String[] args) {
        var s = new TexturePacker.Settings();
        s.maxWidth = 4096;
        s.maxHeight = 4096;
        s.combineSubdirectories = true;   // имена будут с путями: enemies/goblin, heroes/001 и т.д.
        s.stripWhitespaceX = s.stripWhitespaceY = true;
        s.duplicatePadding = true;
        TexturePacker.process(s,
            "assets/packtool/raw",     // откуда брать png
            "assets/packtool/cooked",         // куда сложить
            "game");                  // имя атласа: game.atlas + game.png
    }
}
