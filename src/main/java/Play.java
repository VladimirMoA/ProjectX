import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState {

    private Shape shape = null;
    private boolean collides = false;
    private Ellipse ellipse = null;
    private TiledMap map;
    float MCPositionX = 350;
    float MCPositionY = 150;
    private SpriteSheet uPMove,dMove,rMove,lMove,lDMove,lUPMove,rDMove,rUPMove,MC;
    private Animation uPMoveAnim,dMoveAnim,rMoveAnim,lMoveAnim,lDMoveAnim,lUPMoveAnim,rDMoveAAnim,rUPMoveAnim,MCIdle,Default;

    public Play(int state) {

    }

    public int getID() {
        return 1;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new TiledMap("src/main/resources/Lebel.tmx");
        float[] points = {254,295,382,233,510,296,382,360};
        shape = new Polygon(points);
        uPMove = new SpriteSheet("src/main/resources/UPMove.png",85,169);
        dMove = new SpriteSheet("src/main/resources/DMove.png",85,169);
        rMove = new SpriteSheet("src/main/resources/RMove.png",85,169);
        lMove = new SpriteSheet("src/main/resources/LMove.png",85,169);
        lDMove = new SpriteSheet("src/main/resources/LDMove.png",85,169);
        lUPMove = new SpriteSheet("src/main/resources/LUPMove.png",85,169);
        rDMove = new SpriteSheet("src/main/resources/RDMove.png",85,169);
        rUPMove = new SpriteSheet("src/main/resources/RUPMove.png",85,169);
        MC = new SpriteSheet("src/main/resources/idle.png",84,161);
        uPMoveAnim = new Animation(uPMove,100);
        dMoveAnim = new Animation(dMove,100);
        rMoveAnim = new Animation(rMove,100);
        lMoveAnim = new Animation(lMove,100);
        lDMoveAnim = new Animation(lDMove,100);
        lUPMoveAnim = new Animation(lUPMove,100);
        rDMoveAAnim = new Animation(rDMove,100);
        rUPMoveAnim = new Animation(rUPMove,100);
        MCIdle = new Animation(MC,100);
        Default = MCIdle;

        ellipse = new Ellipse(392,295,30,15);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(350,200, 0);
        MCIdle.draw(MCPositionX,MCPositionY);
        graphics.setColor(Color.transparent);
        graphics.draw(ellipse);
        graphics.draw(shape);
        graphics.setColor(Color.white);
        graphics.drawString("MC X: " + MCPositionX + "\nMC Y: " + MCPositionY,400,20);
        graphics.drawString("Ellipse X: " + ellipse.getCenterX() + "\nEllipse Y: " + ellipse.getCenterY(),400,60);
        graphics.drawString("Collides " + collides,400,100);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        ellipse.setCenterX(MCPositionX+42);
        ellipse.setCenterY(MCPositionY+145);
        ellipse.getCenterX();

        collides = shape.contains(ellipse);

        Input input = gameContainer.getInput();
            if (input.isKeyDown(Input.KEY_DOWN)) {
                MCIdle = dMoveAnim;
                MCPositionY += i * .1f;
                if (collides == false) {
                    if (MCPositionX>340 && MCPositionY>150) {
                        MCPositionX -= i * .1f;
                        MCPositionY -= i * .1f;
                    } else if (MCPositionX<340 && MCPositionY>150) {
                        MCPositionX += i * .1f;
                        MCPositionY -= i * .1f;
                    }
                }

            } else MCIdle = Default;

            if (input.isKeyDown(Input.KEY_UP)) {
                MCIdle = uPMoveAnim;
                MCPositionY -= i * .1f;
                if (collides == false) {
                    if (MCPositionX>340 && MCPositionY<150) {
                        MCPositionX -= i * .1f;
                        MCPositionY += i * .1f;
                    } else if (MCPositionX<340 && MCPositionY<150) {
                        MCPositionX += i * .1f;
                        MCPositionY += i * .1f;
                    }
                }
            }

            if (input.isKeyDown(Input.KEY_RIGHT)) {
                MCIdle = rMoveAnim;
                MCPositionX += i * .1f;
                if (collides == false) {
                    if (MCPositionX>340 && MCPositionY<150) {
                        MCPositionX -= i * .1f;
                        MCPositionY += i * .1f;
                    } else if (MCPositionX>340 && MCPositionY>150) {
                        MCPositionX -= i * .1f;
                        MCPositionY -= i * .1f;
                    }
                }
            }

            if (input.isKeyDown(Input.KEY_LEFT)) {
                MCIdle = lMoveAnim;
                MCPositionX -= i * .1f;
                if (collides == false) {
                    if (MCPositionX<340 && MCPositionY<150) {
                        MCPositionX += i * .1f;
                        MCPositionY += i * .1f;
                    } else if (MCPositionX<340 && MCPositionY>150) {
                        MCPositionX += i * .1f;
                        MCPositionY -= i * .1f;
                    }
                }
            }

            if (input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_DOWN)) {
                MCIdle = lDMoveAnim;
            }

            if (input.isKeyDown(Input.KEY_LEFT) && input.isKeyDown(Input.KEY_UP)) {
                MCIdle = lUPMoveAnim;
            }

            if (input.isKeyDown(Input.KEY_RIGHT) && input.isKeyDown(Input.KEY_DOWN)) {
                MCIdle = rDMoveAAnim;
            }

            if (input.isKeyDown(Input.KEY_RIGHT) && input.isKeyDown(Input.KEY_UP)) {
                MCIdle = rUPMoveAnim;
            }

    }
}
