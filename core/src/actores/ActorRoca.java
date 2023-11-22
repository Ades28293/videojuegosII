package actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorRoca extends Actor {

    private Texture texturaR;

    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public ActorRoca(Texture texturaR) {

        this.texturaR = texturaR;

        setSize(texturaR.getWidth(), texturaR.getHeight());
        this.alive = true;
    }

    @Override
    public void act(float delta) {

        if(isAlive()){
            setX(getX()-250*delta);
        }else{
            setX(getX());
        }




    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texturaR,getX(),getY());
    }

}
