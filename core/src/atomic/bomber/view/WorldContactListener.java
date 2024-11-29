package atomic.bomber.view;

import atomic.bomber.view.menus.GameMenu;
import atomic.bomber.view.sprites.*;
import com.badlogic.gdx.physics.box2d.*;

import static atomic.bomber.view.CollisionCategory.*;

public class WorldContactListener implements ContactListener {
    short categoryA, categoryB, maskA, maskB;
    Body bodyA, bodyB;
    Fixture fixtureA, fixtureB;

    public WorldContactListener() {

    }
    @Override
    public void beginContact(Contact contact) {
        this.fixtureA = contact.getFixtureA();
        this.fixtureB = contact.getFixtureB();
        this.categoryA = fixtureA.getFilterData().categoryBits;
        this.categoryB = fixtureB.getFilterData().categoryBits;
        this.maskA = fixtureA.getFilterData().maskBits;
        this.maskB = fixtureB.getFilterData().maskBits;
        this.bodyA = fixtureA.getBody();
        this.bodyB = fixtureB.getBody();

        if (((categoryA & maskB) == 0)) {
            contact.setEnabled(false);
        } else if (fixtureA.isSensor() || fixtureB.isSensor()) {
            if (fixtureA.isSensor()) {
                if (bodyB.getUserData() != null) ((Attacker) bodyA.getUserData()).doAction((GameObject) bodyB.getUserData());
            } else {
                if (bodyA.getUserData() != null) ((Attacker) bodyB.getUserData()).doAction((GameObject) bodyA.getUserData());
            }
        } else {
            resolveContact(true);
            resolveContact(false);
        }
    }

    public void resolveContact(boolean isA) {
        short category, otherCategory, mask;
        Body body;
        Fixture fixture;
        if (isA) {
            category = categoryA;
            otherCategory = categoryB;
            mask = maskA;
            body = bodyA;
            fixture = fixtureA;
        } else {
            category = categoryB;
            otherCategory = categoryA;
            mask = maskB;
            body = bodyB;
            fixture = fixtureB;
        }

        if (category == BOMB || category == BULLET || category == NUKE_BONUS || category == CLUSTER_BONUS) {
            if (otherCategory == GROUND) {
                if (body.getUserData() instanceof Bomb) GameMenu.setBigBlastAnimation((GameObject) body.getUserData());
                else GameMenu.setNukeAnimation((GameObject) body.getUserData());
            }
            ((GameObject) body.getUserData()).die();
            GameMenu.removeBody((GameObject) body.getUserData());
            GameMenu.removeObject((GameObject) body.getUserData());
        } else if (category == TARGET) {
            if (body.getUserData() instanceof Building) {
                GameMenu.addNukeBonus((GameObject) body.getUserData());
            } else if (body.getUserData() instanceof Bunker) {
                GameMenu.addClusterBonus((GameObject) body.getUserData());
            }
        } else if (category == PLANE) {
            if (otherCategory != CLUSTER_BONUS && otherCategory != NUKE_BONUS) GameMenu.setAirBlastAnimation((GameObject) body.getUserData());
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
