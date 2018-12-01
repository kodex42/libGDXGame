package com.mygdx.game.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.helperClasses.GifDecoder;

public class Battle extends Event {

    private SpriteBatch batch;
    private Animation<TextureRegion> animation;
    private final String ANIM_PATH = "BattleStartAnimation.gif";
    float elapsed;

    public Battle() {
        super(EventType.BATTLE);
        this.batch = new SpriteBatch();
        this.animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(ANIM_PATH).read());

    }

    @Override
    public void trigger(Entity entity) {
        if (entity.willFight()) {
            this.start();
        }
        else
            return;
    }

    @Override
    protected void display(DisplayType type) {

    }

    public void playAnimation() {
        /*elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), 20.0f, 20.0f);
        batch.end();*/
    }
}
