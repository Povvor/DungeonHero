package com.mygame.dungeon_hero.assetManger;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import lombok.var;


public class PackTool {
    public static void main(String[] args) {
        var s = new TexturePacker.Settings();
        s.useIndexes = false;
        s.maxWidth = 4096;
        s.maxHeight = 4096;
        s.combineSubdirectories = true;   // имена будут с путями: enemies/goblin, heroes/001 и т.д.
        s.stripWhitespaceX = s.stripWhitespaceY = true;
        s.duplicatePadding = true;
        TexturePacker.process(s,
            "D:\\packtool\\raw",     // откуда брать png
            "D:\\packtool\\cook",         // куда сложить
            "misc");                  // имя атласа: game.atlas + game.png
    }
}
