package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

import entities.Constants;
import entities.FloorEntity;
import entities.PlayerEntity;
import entities.RocaEntity;

public class GameScreen extends BaseScreen{

    private Stage stage;
    private World world;
    private PlayerEntity player;
    private FloorEntity floor;
    private RocaEntity roca;

    private Boolean colisionDetectada = false;


    private List<FloorEntity> listaSuelos = new ArrayList<FloorEntity>();
    private List<RocaEntity> listaRoca = new ArrayList<RocaEntity>();

    public GameScreen(MyGdxGame game) {
        super(game);
        stage = new Stage(new FitViewport(640,360));
        world = new World(new Vector2(0, -15), true);

    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("dino.png");
        player = new PlayerEntity(world, playerTexture, new Vector2(1, 2));
        stage.addActor(player);

        Texture rocaTexture = game.getManager().get("roca.png");
        listaRoca.add(new RocaEntity(world, rocaTexture, new Vector2(6.5f, 1.4f)));
        listaRoca.add(new RocaEntity(world, rocaTexture, new Vector2(12.5f, 1.4f)));
        listaRoca.add(new RocaEntity(world, rocaTexture, new Vector2(20.5f, 1.4f)));
        //roca = new RocaEntity(world, rocaTexture, new Vector2(6.5f, 1.5f));
        //stage.addActor(roca);
        for (RocaEntity roca : listaRoca){
            stage.addActor(roca);
        }

        Texture floorTexture = game.getManager().get("suelo.png");

        listaSuelos.add(new FloorEntity(world, floorTexture, new Vector2(0.5f, 0.5f)));
        listaSuelos.add(new FloorEntity(world, floorTexture, new Vector2(10, 0.5f)));
        listaSuelos.add(new FloorEntity(world, floorTexture, new Vector2(20, 0.5f)));
        listaSuelos.add(new FloorEntity(world, floorTexture, new Vector2(30, 0.5f)));
        //floor = new FloorEntity(world, floorTexture, new Vector2(0.5f, 0.5f));
        //stage.addActor(floor);
        for (FloorEntity suelo : listaSuelos){
            stage.addActor(suelo);
        }

        world.setContactListener(new ContactListener() {

            private boolean colision(Contact contact, Object userA, Object userB){
                return (contact.getFixtureA().getUserData().equals(userA) &&
                        contact.getFixtureB().getUserData().equals(userB) ||
                        (contact.getFixtureA().getUserData().equals(userB) &&
                                contact.getFixtureB().getUserData().equals(userA)));
            }
            @Override
            public void beginContact(Contact contact) {
                if (colision(contact, "player", "obstaculo")){
                    colisionDetectada = true;
                }

                if (colision(contact, "obstaculo", "player")){
                    colisionDetectada = true;
                    player.setAlive(false);
                }

                if (colision(contact, "obstaculo", "player")){
                    colisionDetectada = false;
                    player.setAlive(true);
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(player.getX() > 100 && player.isAlive()){
            stage.getCamera().translate(2f * delta * Constants.pixelInmedet,0,0);
        }

        stage.act();

        // Manejar la entrada del jugador (salto)
        if (Gdx.input.justTouched() && !colisionDetectada) {
            player.saltar();
        }

        // Mover el jugador a la derecha
        player.moverDerecha();


        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void hide() {
        player.liberar();
        player.remove();

        for (RocaEntity roca : listaRoca){
            roca.liberar();
            roca.remove();
        }

        for (FloorEntity suelo : listaSuelos){
            suelo.liberar();
            suelo.remove();
        }

    }
}
