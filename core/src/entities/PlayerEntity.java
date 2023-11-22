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

public class PlayerEntity extends Actor {

    private Texture texture;
    private boolean alive = true;
    private World world;
    private Body body;
    private Fixture fixture;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public PlayerEntity() {
    }

    public PlayerEntity(World world,Texture texture, Vector2 position) {
        this.texture = texture;
        this.world = world;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.4f, 1);

        fixture = body.createFixture(polygonShape, 1);
        fixture.setUserData("player");

        polygonShape.dispose();

        setSize(Constants.pixelInmedet, Constants.pixelInmedet );

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x-0.5f)*Constants.pixelInmedet, (body.getPosition().y-0.5f)*Constants.pixelInmedet);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void moverDerecha() {
        body.setLinearVelocity(2, body.getLinearVelocity().y);
    }

    public void saltar() {
        Vector2 position = body.getPosition();
        body.applyLinearImpulse(0, 5, position.x, position.y, true);
    }

    public void liberar(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }


}
