import extra.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;


public class TurnPlay extends BasicGameState {

    private Shape shape,shape1,shape2,shape3,shape4,shape5,shape6,shape7,shape8,shape9,shape10,shape11,shape12,shape13,
            shape14,shape15,shape16,shape17,shape18,shape19,shape20,shape21,shape22,shape23,shape24 = null;
    private boolean collides = false;
    private Ellipse ellipse = null;
    private TiledMap map;
    float xDirection = -100;
    float yDirection = -100;
    float glowDrawPositionX = -50;
    float glowDrawPositionY = -50;
    int playerNumber = 0;
    ArrayList<Coordinates> possibleCoordinates = new ArrayList<Coordinates>();
    ArrayList <NPC> players = new ArrayList<NPC>();
    private SpriteSheet glow,tileGlow,arrowTest;
    private Animation MCIdle,MCIdle1,MCIdle2,MCIdle3,MCIdle4,glowAnim,tileGlowAnim,arrowTestAnim;

    boolean [][] gameLocation = new boolean[5][5];

    // Координаты для анимации передвижения и тайлов
    static Coordinates [][] movingCordArray = {{new Coordinates(262.6f,225),new Coordinates(287.6f,238),new Coordinates(312.6f,251),new Coordinates(337.6f,264),new Coordinates(362.6f,277)},
                                    {new Coordinates(287.6f,212.6f),new Coordinates(312.6f,225),new Coordinates(337.6f,238),new Coordinates(362.6f,251),new Coordinates(387.6f,264)},
                                    {new Coordinates(312.6f,200.2f),new Coordinates(337.6f,212.6f),new Coordinates(362.6f,225),new Coordinates(387.6f,238),new Coordinates(412.6f,251)},
                                    {new Coordinates(337.6f,187.8f),new Coordinates(362.6f,200.2f),new Coordinates(387.6f,212.6f),new Coordinates(412.6f,225),new Coordinates(437.6f,238)},
                                    {new Coordinates(362.6f,175.4f),new Coordinates(387.6f,187.8f),new Coordinates(412.6f,200.2f),new Coordinates(437.6f,212.6f),new Coordinates(462.6f,225)}};

    static Coordinates [][] animCordArray = {{new Coordinates(254,295-12.4f),new Coordinates(254+25.6f,295),new Coordinates(254+51.2f,295+13),new Coordinates(254+76.8f,295+26),new Coordinates(254+102.4f,295+39)},
                                    {new Coordinates(254+25.6f,295-24.8f),new Coordinates(254+51.2f,295-12.4f),new Coordinates(254+76.8f,295),new Coordinates(254+102.4f,295+13),new Coordinates(254+128f,295+26)},
                                    {new Coordinates(254+51.2f,295-37.2f),new Coordinates(254+76.8f,295-24.8f),new Coordinates(254+102.4f,295-12.4f),new Coordinates(254+128f,295),new Coordinates(254+153.6f,295+13)},
                                    {new Coordinates(254+76.8f,295-49.6f),new Coordinates(254+102.4f,295-37.2f),new Coordinates(254+128f,295-24.8f),new Coordinates(254+153.6f,295-12.4f),new Coordinates(254+179.2f,295)},
                                    {new Coordinates(254+102.4f,295-62f),new Coordinates(254+128f,295-49.6f),new Coordinates(254+153.6f,295-37.2f),new Coordinates(254+179.2f,295-24.8f),new Coordinates(254+204.8f,295-12.4f)}};

    // Метод координат для создания квадратов
    public static Shape tile(float x1, float y1,float x2,float y2,float x3, float y3, float x4, float y4) {
        float[] points = {x1,y1,x2,y2,x3,y3,x4,y4};
        Shape shape = new Polygon(points);
        return shape;
    }

    // Рисуем персонажа
    public static void npcDraw(NPC npc, Animation animation) {
        float x = movingCordArray[(int)npc.getCoordinates().getX()][(int)npc.getCoordinates().getY()].getX();
        float y = movingCordArray[(int)npc.getCoordinates().getX()][(int)npc.getCoordinates().getY()].getY();
        animation = new Animation(npc.getSpriteSheet(),100);
        animation.setCurrentFrame(6);
        animation.stop();
        animation.draw(x,y,33,79);
    }

    public TurnPlay(int state) {

    }

    public int getID() {
        return 2;
    }

    // Создаём анимации и рисунки-атласы
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        map = new TiledMap("src/main/resources/Lebel.tmx");

        shape = tile(254,295,254+25.6f,295-12.4f,254+51.2f,295,254+25.6f,295+13);
        shape1 = tile(254+25.6f,295+13,254+51.2f,295,254+76.8f,295+13,254+51.2f,295+26);
        shape2 = tile(254+51.2f,295+26,254+76.8f,295+13,254+102.4f,295+26,254+76.8f,295+39);
        shape3 = tile(254+76.8f,295+39,254+102.4f,295+26,254+128f,295+39,254+102.4f,295+52);
        shape4 = tile(254+102.4f,295+52,254+128f,295+39,254+153.6f,295+52,254+128f,295+65);
        shape5 = tile(254+25.6f,295-12.4f,254+51.2f,295-24.8f,254+76.8f,295-12.4f,254+51.2f,295);
        shape6 = tile(254+51.2f,295,254+76.8f,295-12.4f,254+102.4f,295,254+76.8f,295+13);
        shape7 = tile(254+76.8f,295+13,254+102.4f,295,254+128f,295+13,254+102.4f,295+26);
        shape8 = tile(254+102.4f,295+26,254+128f,295+13,254+153.6f,295+26,254+128f,295+39);
        shape9 = tile(254+128f,295+39,254+153.6f,295+26,254+179.2f,295+39,254+153.6f,295+52);
        shape10 = tile(254+51.2f,295-24.8f,254+76.8f,295-37.2f,254+102.4f,295-24.8f,254+76.8f,295-12.4f);
        shape11 = tile(254+76.8f,295-12.4f,254+102.4f,295-24.8f,254+128f,295-12.4f,254+102.4f,295);
        shape12 = tile(254+102.4f,295,254+128f,295-12.4f,254+153.6f,295,254+128f,295+13);
        shape13 = tile(254+128f,295+13,254+153.6f,295,254+179.2f,295+13,254+153.6f,295+26);
        shape14 = tile(254+153.6f,295+26,254+179.2f,295+13,254+204.8f,295+26,254+179.2f,295+39);
        shape15 = tile(254+76.8f,295-37.2f,254+102.4f,295-49.6f,254+128f,295-37.2f,254+102.4f,295-24.8f);
        shape16 = tile(254+102.4f,295-24.8f,254+128f,295-37.2f,254+153.6f,295-24.8f,254+128f,295-12.4f);
        shape17 = tile(254+128f,295-12.4f,254+153.6f,295-24.8f,254+179.2f,295-12.4f,254+153.6f,295);
        shape18 = tile(254+153.6f,295,254+179.2f,295-12.4f,254+204.8f,295,254+179.2f,295+13);
        shape19 = tile(254+179.2f,295+13,254+204.8f,295,254+230.4f,295+13,254+204.8f,295+26);
        shape20 = tile(254+102.4f,295-49.6f,254+128.4f,295-62f,254+153.6f,295-49.6f,254+128f,295-37.2f);
        shape21 = tile(254+128f,295-37.2f,254+153.6f,295-49.6f,254+179.2f,295-37.2f,254+153.6f,295-24.8f);
        shape22 = tile(254+153.6f,295-24.8f,254+179.2f,295-37.2f,254+204.8f,295-24.8f,254+179.2f,295-12.4f);
        shape23 = tile(254+179.2f,295-12.4f,254+204.8f,295-24.8f,254+230.4f,295-12.4f,254+204.8f,295);
        shape24 = tile(254+204.8f,295,254+230.4f,295-12.4f,254+256f,295,254+230.4f,295+13);

        glow = new SpriteSheet("src/main/resources/GlowAnim.png",64,31);
        tileGlow = new SpriteSheet("src/main/resources/TileAnim.png",64,31);
        players.add(new NPC("ГГ", new Coordinates(0,0),5,0,0,0,0,0,0,0,new SpriteSheet("src/main/resources/MCAtlas.png",85,169),true));
        players.add(new NPC("ГГ", new Coordinates(0,1),5,0,0,0,0,0,0,0,new SpriteSheet("src/main/resources/MCAtlas.png",85,169),true));
        players.add(new NPC("ГГ", new Coordinates(0,2),5,0,0,0,0,0,0,0,new SpriteSheet("src/main/resources/MCAtlas.png",85,169),true));
        players.add(new NPC("ГГ", new Coordinates(0,3),5,0,0,0,0,0,0,0,new SpriteSheet("src/main/resources/MCAtlas.png",85,169),true));
        players.add(new NPC("ГГ", new Coordinates(0,4),5,0,0,0,0,0,0,0,new SpriteSheet("src/main/resources/MCAtlas.png",85,169),true));
        arrowTest = new SpriteSheet("src/main/resources/ArrowAnim/RUpArrow/1Tile/0Empty.png",130,72);
        glowAnim = new Animation(glow,100);
        tileGlowAnim = new Animation(tileGlow,100);
        arrowTestAnim = new Animation(arrowTest,75);
        ellipse = new Ellipse(0,0,1,1);
    }

    // Рисуем анимации и квадраты
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(350,200, 0);

        glowAnim.draw(glowDrawPositionX,glowDrawPositionY,50,23);
        graphics.draw(ellipse);
        graphics.draw(shape); graphics.draw(shape1); graphics.draw(shape2); graphics.draw(shape3); graphics.draw(shape4);
        graphics.draw(shape5); graphics.draw(shape6); graphics.draw(shape7); graphics.draw(shape8); graphics.draw(shape9);
        graphics.draw(shape10); graphics.draw(shape11); graphics.draw(shape12); graphics.draw(shape13); graphics.draw(shape14);
        graphics.draw(shape15); graphics.draw(shape16); graphics.draw(shape17); graphics.draw(shape18); graphics.draw(shape19);
        graphics.draw(shape20); graphics.draw(shape21); graphics.draw(shape22); graphics.draw(shape23); graphics.draw(shape24);

        // Отображения возможного выбора перемещения
        if (players.get(playerNumber).getAP()>0) {
            try {
                for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                    tileGlowAnim.draw(animCordArray[(int)players.get(playerNumber).getCoordinates().getX() + k][(int)players.get(playerNumber).getCoordinates().getY()].getX(), animCordArray[(int)players.get(playerNumber).getCoordinates().getX() + k][(int)players.get(playerNumber).getCoordinates().getY()].getY(), 50, 23);
                }
            } catch (ArrayIndexOutOfBoundsException e) { }
            try {
                for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                    tileGlowAnim.draw(animCordArray[(int)players.get(playerNumber).getCoordinates().getX() - k][(int)players.get(playerNumber).getCoordinates().getY()].getX(), animCordArray[(int)players.get(playerNumber).getCoordinates().getX() - k][(int)players.get(playerNumber).getCoordinates().getY()].getY(), 50, 23);
                }
            } catch (ArrayIndexOutOfBoundsException e) { }
            try {
                for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                    tileGlowAnim.draw(animCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY() + k].getX(), animCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY() + k].getY(), 50, 23);
                }
            } catch (ArrayIndexOutOfBoundsException e) { }
            try {
                for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                    tileGlowAnim.draw(animCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY() - k].getX(), animCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY() - k].getY(), 50, 23);
                }
            } catch (ArrayIndexOutOfBoundsException e) { }
        }

        arrowTestAnim.draw(xDirection,yDirection);

        if (players.size() == 2) {
            npcDraw(players.get(0),MCIdle); gameLocation[(int) players.get(0).getCoordinates().getX()][(int) players.get(0).getCoordinates().getY()] = true;
        } else if (players.size() == 4) {
            npcDraw(players.get(0),MCIdle); gameLocation[(int) players.get(0).getCoordinates().getX()][(int) players.get(0).getCoordinates().getY()] = true;
            npcDraw(players.get(1),MCIdle1); gameLocation[(int) players.get(1).getCoordinates().getX()][(int) players.get(1).getCoordinates().getY()] = true;
        } else if (players.size() == 6) {
            npcDraw(players.get(0),MCIdle); gameLocation[(int) players.get(0).getCoordinates().getX()][(int) players.get(0).getCoordinates().getY()] = true;
            npcDraw(players.get(1),MCIdle1); gameLocation[(int) players.get(1).getCoordinates().getX()][(int) players.get(1).getCoordinates().getY()] = true;
            npcDraw(players.get(2),MCIdle2); gameLocation[(int) players.get(2).getCoordinates().getX()][(int) players.get(2).getCoordinates().getY()] = true;
        } else if (players.size() == 8) {
            npcDraw(players.get(0),MCIdle); gameLocation[(int) players.get(0).getCoordinates().getX()][(int) players.get(0).getCoordinates().getY()] = true;
            npcDraw(players.get(1),MCIdle1); gameLocation[(int) players.get(1).getCoordinates().getX()][(int) players.get(1).getCoordinates().getY()] = true;
            npcDraw(players.get(2),MCIdle2); gameLocation[(int) players.get(2).getCoordinates().getX()][(int) players.get(2).getCoordinates().getY()] = true;
            npcDraw(players.get(3),MCIdle3); gameLocation[(int) players.get(3).getCoordinates().getX()][(int) players.get(3).getCoordinates().getY()] = true;
        } else if (players.size() == 10) {
            npcDraw(players.get(0),MCIdle); gameLocation[(int) players.get(0).getCoordinates().getX()][(int) players.get(0).getCoordinates().getY()] = true;
            npcDraw(players.get(1),MCIdle1); gameLocation[(int) players.get(1).getCoordinates().getX()][(int) players.get(1).getCoordinates().getY()] = true;
            npcDraw(players.get(2),MCIdle2); gameLocation[(int) players.get(2).getCoordinates().getX()][(int) players.get(2).getCoordinates().getY()] = true;
            npcDraw(players.get(3),MCIdle3); gameLocation[(int) players.get(3).getCoordinates().getX()][(int) players.get(3).getCoordinates().getY()] = true;
            npcDraw(players.get(4),MCIdle4); gameLocation[(int) players.get(4).getCoordinates().getX()][(int) players.get(4).getCoordinates().getY()] = true;
        }
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        // Стави кругу координаты мышки, для рисования мигающих анимаций
        ellipse.setCenterX(Mouse.getX());
        ellipse.setCenterY(600-Mouse.getY());

        collides = shape.contains(ellipse);

        Input input = gameContainer.getInput();

            // Заполняем лист для проверки того, может ли персонаж идти в определённое место
            if (players.get(playerNumber).getAP() > 0) {
                try {
                    for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                        possibleCoordinates.add(new Coordinates((int)players.get(playerNumber).getCoordinates().getX() + k, (int)players.get(playerNumber).getCoordinates().getY()));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                        possibleCoordinates.add(new Coordinates((int)players.get(playerNumber).getCoordinates().getX() - k, (int)players.get(playerNumber).getCoordinates().getY()));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                        possibleCoordinates.add(new Coordinates((int)players.get(playerNumber).getCoordinates().getX(), (int)players.get(playerNumber).getCoordinates().getY() + k));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    for (int k = 1; k <= players.get(playerNumber).getAP(); k++) {
                        possibleCoordinates.add(new Coordinates((int)players.get(playerNumber).getCoordinates().getX(), (int)players.get(playerNumber).getCoordinates().getY() - k));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }


            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                // Создаём координату, на которую должен переместиться ГГ
                Coordinates destination = new Coordinates((int)players.get(playerNumber).getCoordinates().getX(), (int)players.get(playerNumber).getCoordinates().getY());
                if (shape.contains(ellipse)) { destination = new Coordinates(0, 0); }
                if (shape1.contains(ellipse)) { destination = new Coordinates(0, 1); }
                if (shape2.contains(ellipse)) { destination = new Coordinates(0, 2); }
                if (shape3.contains(ellipse)) { destination = new Coordinates(0, 3); }
                if (shape4.contains(ellipse)) { destination = new Coordinates(0, 4); }
                if (shape5.contains(ellipse)) { destination = new Coordinates(1, 0); }
                if (shape6.contains(ellipse)) { destination = new Coordinates(1, 1); }
                if (shape7.contains(ellipse)) { destination = new Coordinates(1, 2); }
                if (shape8.contains(ellipse)) { destination = new Coordinates(1, 3); }
                if (shape9.contains(ellipse)) { destination = new Coordinates(1, 4); }
                if (shape10.contains(ellipse)) { destination = new Coordinates(2, 0); }
                if (shape11.contains(ellipse)) { destination = new Coordinates(2, 1); }
                if (shape12.contains(ellipse)) { destination = new Coordinates(2, 2); }
                if (shape13.contains(ellipse)) { destination = new Coordinates(2, 3); }
                if (shape14.contains(ellipse)) { destination = new Coordinates(2, 4); }
                if (shape15.contains(ellipse)) { destination = new Coordinates(3, 0); }
                if (shape16.contains(ellipse)) { destination = new Coordinates(3, 1); }
                if (shape17.contains(ellipse)) { destination = new Coordinates(3, 2); }
                if (shape18.contains(ellipse)) { destination = new Coordinates(3, 3); }
                if (shape19.contains(ellipse)) { destination = new Coordinates(3, 4); }
                if (shape20.contains(ellipse)) { destination = new Coordinates(4, 0); }
                if (shape21.contains(ellipse)) { destination = new Coordinates(4, 1); }
                if (shape22.contains(ellipse)) { destination = new Coordinates(4, 2); }
                if (shape23.contains(ellipse)) { destination = new Coordinates(4, 3); }
                if (shape24.contains(ellipse)) { destination = new Coordinates(4, 4); }

                // Перемещаем ГГ а также рисуем стрелки передвижения
                for (int j = 0; j < possibleCoordinates.size(); j++) {
                    if (possibleCoordinates.get(j).equals(destination)) {
                        if (destination.getX() > (int)players.get(playerNumber).getCoordinates().getX()) {
                            arrowTestAnim.stop();
                            if ((destination.getX() - (int)players.get(playerNumber).getCoordinates().getX()) == 1) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RUpArrow/1Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(2);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-1);
                            } else if ((destination.getX() - (int)players.get(playerNumber).getCoordinates().getX()) == 2) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RUpArrow/2Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(4);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-2);
                            } else if ((destination.getX() - (int)players.get(playerNumber).getCoordinates().getX()) == 3) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RUpArrow/3Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(6);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-3);
                            } else if ((destination.getX() - (int)players.get(playerNumber).getCoordinates().getX()) == 4) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RUpArrow/4Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(8);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-4);
                            } else if ((destination.getX() - (int)players.get(playerNumber).getCoordinates().getX()) == 5) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RUpArrow/5Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(10);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-5);
                            }
                            xDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getX() + 50.2f;
                            yDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getY();
                        } else if ((int)players.get(playerNumber).getCoordinates().getY() > destination.getY()) {
                            arrowTestAnim.stop();
                            if (((int)players.get(playerNumber).getCoordinates().getY() - destination.getY()) == 1) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LUPArrow/1Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(2);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-1);
                            } else if (((int)players.get(playerNumber).getCoordinates().getY() - destination.getY()) == 2) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LUPArrow/2Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(4);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-2);
                            } else if (((int)players.get(playerNumber).getCoordinates().getY() - destination.getY()) == 3) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LUPArrow/3Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(6);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-3);
                            } else if (((int)players.get(playerNumber).getCoordinates().getY() - destination.getY()) == 4) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LUPArrow/4Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(8);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-4);
                            } else if (((int)players.get(playerNumber).getCoordinates().getY() - destination.getY()) == 5) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LUPArrow/5Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(10);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-5);
                            }
                            xDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getX() - 100;
                            yDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getY() - 26;
                        } else if ((int)players.get(playerNumber).getCoordinates().getY() < destination.getY()) {
                            arrowTestAnim.stop();
                            if ((destination.getY() - (int)players.get(playerNumber).getCoordinates().getY()) == 1) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RDArrow/1Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(2);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-1);
                            } else if ((destination.getY() - (int)players.get(playerNumber).getCoordinates().getY()) == 2) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RDArrow/2Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(4);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-2);
                            } else if ((destination.getY() - (int)players.get(playerNumber).getCoordinates().getY()) == 3) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RDArrow/3Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(6);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-3);
                            } else if ((destination.getY() - (int)players.get(playerNumber).getCoordinates().getY()) == 4) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RDArrow/4Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(8);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-4);
                            } else if ((destination.getY() - (int)players.get(playerNumber).getCoordinates().getY()) == 5) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/RDArrow/5Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(10);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-5);
                            }
                            xDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getX() + 50;
                            yDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getY() + 50;
                        } else if (destination.getX() < (int)players.get(playerNumber).getCoordinates().getX()) {
                            arrowTestAnim.stop();
                            if ((int)players.get(playerNumber).getCoordinates().getX() - (destination.getX()) == 1) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LDArrow/1Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(2);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-1);
                            } else if ((int)players.get(playerNumber).getCoordinates().getX() - (destination.getX()) == 2) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LDArrow/2Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(4);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-2);
                            } else if ((int)players.get(playerNumber).getCoordinates().getX() - (destination.getX()) == 3) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LDArrow/3Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(6);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-3);
                            } else if ((int)players.get(playerNumber).getCoordinates().getX() - (destination.getX()) == 4) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LDArrow/4Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(8);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-4);
                            } else if ((int)players.get(playerNumber).getCoordinates().getX() - (destination.getX()) == 5) {
                                SpriteSheet TiledArrow = new SpriteSheet("src/main/resources/ArrowAnim/LDArrow/5Tile/TiledArrow.png", 130, 72);
                                Animation TiledArrowAnim = new Animation(TiledArrow, 75);
                                arrowTestAnim = TiledArrowAnim;
                                arrowTestAnim.stopAt(10);
                                players.get(playerNumber).setAP(players.get(playerNumber).getAP()-5);
                            }
                            xDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getX() - 140;
                            yDirection = movingCordArray[(int)players.get(playerNumber).getCoordinates().getX()][(int)players.get(playerNumber).getCoordinates().getY()].getY() + 50;
                        }
                        arrowTestAnim.restart();
                        arrowTestAnim.setLooping(false);
                        possibleCoordinates = new ArrayList<Coordinates>();
                        players.get(playerNumber).setCoordinates(new Coordinates((int) destination.getX(),(int) destination.getY()));
                    }
                }
            }

        if ((playerNumber < (players.size()/2)-1) && (players.get(playerNumber).getAP() == 0)) {
            playerNumber++;
        }

        // Меняем координаты анимации, которая мигает, если мышка будет на этой локации
        if (shape.contains(ellipse)) { glowDrawPositionX = 254; glowDrawPositionY = 295-12.4f;}
        if (shape1.contains(ellipse)) { glowDrawPositionX = 254+25.6f; glowDrawPositionY = 295;}
        if (shape2.contains(ellipse)) { glowDrawPositionX = 254+51.2f; glowDrawPositionY = 295+13;}
        if (shape3.contains(ellipse)) { glowDrawPositionX = 254+76.8f; glowDrawPositionY = 295+26;}
        if (shape4.contains(ellipse)) { glowDrawPositionX = 254+102.4f; glowDrawPositionY = 295+39;}

        if (shape5.contains(ellipse)) { glowDrawPositionX = 254+25.6f; glowDrawPositionY = 295-24.8f;}
        if (shape6.contains(ellipse)) { glowDrawPositionX = 254+51.2f; glowDrawPositionY = 295-12.4f;}
        if (shape7.contains(ellipse)) { glowDrawPositionX = 254+76.8f; glowDrawPositionY = 295;}
        if (shape8.contains(ellipse)) { glowDrawPositionX = 254+102.4f; glowDrawPositionY = 295+13;}
        if (shape9.contains(ellipse)) { glowDrawPositionX = 254+128f; glowDrawPositionY = 295+26;}

        if (shape10.contains(ellipse)) { glowDrawPositionX = 254+51.2f; glowDrawPositionY = 295-37.2f;}
        if (shape11.contains(ellipse)) { glowDrawPositionX = 254+76.8f; glowDrawPositionY = 295-24.8f;}
        if (shape12.contains(ellipse)) { glowDrawPositionX = 254+102.4f; glowDrawPositionY = 295-12.4f;}
        if (shape13.contains(ellipse)) { glowDrawPositionX = 254+128f; glowDrawPositionY = 295;}
        if (shape14.contains(ellipse)) { glowDrawPositionX = 254+153.6f; glowDrawPositionY = 295+13;}

        if (shape15.contains(ellipse)) { glowDrawPositionX = 254+76.8f; glowDrawPositionY = 295-49.6f;}
        if (shape16.contains(ellipse)) { glowDrawPositionX = 254+102.4f; glowDrawPositionY = 295-37.2f;}
        if (shape17.contains(ellipse)) { glowDrawPositionX = 254+128f; glowDrawPositionY = 295-24.8f;}
        if (shape18.contains(ellipse)) { glowDrawPositionX = 254+153.6f; glowDrawPositionY = 295-12.4f;}
        if (shape19.contains(ellipse)) { glowDrawPositionX = 254+179.2f; glowDrawPositionY = 295;}

        if (shape20.contains(ellipse)) { glowDrawPositionX = 254+102.4f; glowDrawPositionY = 295-62f;}
        if (shape21.contains(ellipse)) { glowDrawPositionX = 254+128f; glowDrawPositionY = 295-49.6f;}
        if (shape22.contains(ellipse)) { glowDrawPositionX = 254+153.6f; glowDrawPositionY = 295-37.2f;}
        if (shape23.contains(ellipse)) { glowDrawPositionX = 254+179.2f; glowDrawPositionY = 295-24.8f;}
        if (shape24.contains(ellipse)) { glowDrawPositionX = 254+204.8f; glowDrawPositionY = 295-12.4f;}

    }
}
