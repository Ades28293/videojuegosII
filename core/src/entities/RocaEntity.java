package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RocaEntity extends Actor {

    private Texture texture;
    private boolean alive;
    private World world;
    private Body body;
    private Fixture fixture;
    public RocaEntity() {
    }

    public RocaEntity(World world, Texture texture, Vector2 position) {
        this.texture = texture;
        this.world = world;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.4f, 0.5f);
        fixture = body.createFixture(polygonShape, 1);

        fixture.setUserData("obstaculo");

        polygonShape.dispose();

        setSize(Constants.pixelInmedet, Constants.pixelInmedet);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x-0.5f)*Constants.pixelInmedet, (body.getPosition().y-0.5f)*Constants.pixelInmedet);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void moverIzquierda() {
        body.setLinearVelocity(-1, body.getLinearVelocity().y);
    }

    public void liberar(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
