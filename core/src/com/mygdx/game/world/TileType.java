package com.mygdx.game.world;

import java.util.HashMap;

public enum TileType {

    GROUND(1, false, "Ground"),
    FENCE_LEFT(2, true, "Fence Left"),
    FENCE_RIGHT(3, true, "Fence Right"),
    FENCE_DOWN(4, true, "Fence Down"),
    FENCE_UP(5, true, "Fence Up"),
    FENCE_WIDE_CORNER_SE(6, true, "Fence Wide Corner SE"),
    FENCE_WIDE_CORNER_NE(7, true, "Fence Wide Corner NE"),
    FENCE_WIDE_CORNER_NW(8, true, "Fence Wide Corner NW"),
    FENCE_WIDE_CORNER_SW(9, true, "Fence Wide Corner SW"),
    GRASS_STATE_01(10, false, "Grass State 01"),
    GRASS_STATE_02(11, false, "Grass State 02"),
    GRASS_STATE_03(12, false, "Grass State 03"),
    FENCE_NARROW_CORNER_SE(13, false, "Fence Narrow Corner SE"),
    FENCE_NARROW_CORNER_NE(14, false, "Fence Narrow Corner NE"),
    FENCE_NARROW_CORNER_NW(15, false, "Fence Narrow Corner NW"),
    FENCE_NARROW_CORNER_SW(16, false, "Fence Narrow Corner SW");

    public static final int TILE_SIZE = 32;
    private static HashMap<Integer, TileType> tileMap;
    private int id;
    private boolean collidable;
    private String name;
    private float damage;

    private TileType(int id, boolean collidable, String name){
        this(id, collidable, name, 0);
    }

    private TileType(int id, boolean collidable, String name, float damage){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }

    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType tileType : TileType.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }
}
