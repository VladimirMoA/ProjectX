import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends BasicGameState {

    private Shape shape,shape1,shape2 = null;
    private Ellipse ellipse = null;
    private Image background = null;
    private Image playIn = null;
    private Image exitIn = null;
    private Image playAlphaIn = null;

    public static Shape tile(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        float[] points = {x1,y1,x2,y2,x3,y3,x4,y4};
        Shape shape = new Polygon(points);
        return shape;
    }

    public Menu(int state) {

    }

    public int getID() {
        return 0;
    }

    // Отвечает за методы
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        shape = tile(254+60f,295-20f,254+235f,295-20f,254+235f,295-75,254+60f,295-75);
        shape1 = tile(254-50,295+30,254+375,295+30,254+375,295+85,254-50,295+85);
        shape2 = tile(254+65f,295+125f,254+230f,295+125,254+230f,295+180,254+65f,295+180);
        ellipse = new Ellipse(0,0,1,1);

        background = new Image("src/main/resources/Background/Background.png");
        playIn = new Image("src/main/resources/Background/PlayIn.png");
        exitIn = new Image("src/main/resources/Background/ExitIn.png");
        playAlphaIn = new Image("src/main/resources/Background/PlayAlpha.png");
    }

    // Графика статичных объектов
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw();

        graphics.setColor(Color.transparent);
        graphics.draw(ellipse); graphics.draw(shape); graphics.draw(shape1); graphics.draw(shape2);
        graphics.setColor(Color.white);

        playIn.draw(300,175);
        playAlphaIn.draw(200,275);
        exitIn.draw(300,375);
    }

    // Взаимодействие графики друг с другом
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        ellipse.setCenterX(Mouse.getX());
        ellipse.setCenterY(600-Mouse.getY());

        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if ((posX>315&& posX<485)&&(posY>330 && posY<380)) {
            if (Mouse.isButtonDown(0)) {
                stateBasedGame.enterState(2);
            }
        }

        if ((posX>315&& posX<485)&&(posY>230 && posY<280)) {
            if (Mouse.isButtonDown(0)) {
                stateBasedGame.enterState(1);
            }
        }

        if ((posX>315&& posX<485)&&(posY>130 && posY<180)) {
            if (Mouse.isButtonDown(0)) {
                System.exit(0);
            }
        }

        if (shape.contains(ellipse)) { playIn = new Image("src/main/resources/Background/PlayOut.png");} else playIn = new Image("src/main/resources/Background/PlayIn.png");
        if (shape1.contains(ellipse)) { playAlphaIn = new Image("src/main/resources/Background/PlayAlphaOut.png");} else playAlphaIn = new Image("src/main/resources/Background/PlayAlpha.png");
        if (shape2.contains(ellipse)) { exitIn = new Image("src/main/resources/Background/ExitOut.png");} else exitIn = new Image("src/main/resources/Background/ExitIn.png");
    }
}
