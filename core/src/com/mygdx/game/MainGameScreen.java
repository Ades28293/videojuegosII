package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.ActorJugador;
import actores.ActorRoca;

public class MainGameScreen extends BaseScreen {

    public MainGameScreen(MyGdxGame game) {
        super(game);
        texturaJugador = new Texture("dino.png");
        texturaRoca = new Texture("roca.png");
    }

    private Stage stage;
    private ActorJugador jugador;
    private ActorRoca roca;
    private Texture texturaJugador;
    private Texture texturaRoca;

    @Override
    public void show() {
        this.stage = new Stage();
        this.stage.setDebugAll(true);
        this.jugador = new ActorJugador(texturaJugador);
        this.roca = new ActorRoca(texturaRoca);
        this.stage.addActor(this.jugador);
        this.stage.addActor(this.roca);

        this.jugador.setPosition(20, 100);
        this.roca.setPosition(400, 100);
    }

    @Override
    public void hide() {
        this.stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        comprobarColisiones();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        texturaJugador.dispose();
        texturaRoca.dispose();
    }

    //BoundingBox
    private void comprobarColisiones() {

        if (jugador.isAlive() && jugador.getX() + jugador.getWidth() > roca.getX()) {
            System.out.println("Colision detectada!");
            jugador.setAlive(false);
            roca.setAlive(false);
        }

    }
}
