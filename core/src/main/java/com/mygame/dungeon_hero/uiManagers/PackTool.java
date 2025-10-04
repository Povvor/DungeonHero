package com.mygame.dungeon_hero.uiManagers;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import lombok.var;


public class PackTool {
    public static void main(String[] args) {
        var s = new TexturePacker.Settings();
        s.useIndexes = false;
        s.maxWidth = 4096;
        s.maxHeight = 4096;
        s.combineSubdirectories = true;
        s.stripWhitespaceX = s.stripWhitespaceY = true;
        s.duplicatePadding = true;
        TexturePacker.process(s,
            "D:\\packtool\\raw",
            "D:\\packtool\\cook",
            "misc");
    }
}
