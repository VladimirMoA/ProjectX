import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SetupClass extends StateBasedGame {

    public static final String gameName = "The Game";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int turnPlay = 2;

    public SetupClass(String name) {
        super(name);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new TurnPlay(turnPlay));
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(menu).init(gameContainer,this);
        this.getState(play).init(gameContainer,this);
        this.getState(turnPlay).init(gameContainer,this);
        this.enterState(menu);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new SetupClass("The Game"));
        app.setDisplayMode(800,600,false);
        app.start();
    }

}
