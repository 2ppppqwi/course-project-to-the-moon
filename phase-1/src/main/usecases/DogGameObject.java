package usecases;

import adaptors.IGameController;
import adaptors.IGameGraphics;
import entities.Dog;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A class which manages a single dog object
 * Within a game.
 * @author Aria Paydari
 * @since 22 October 2021
 */
public class DogGameObject extends GameObject implements Clickable, Drawable{
    private final Dog myDog; // the dog that this manager handles
    private int coinsEarnedFromLastPet;
    private IGameController controller;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param sprite The sprite of the dog.
     */
    public DogGameObject(int x, int y, SpriteFacade sprite, IGameController controller){
        super(x, y);
        this.myDog = new Dog();
        this.addSprite(sprite);

        DogMover dogMover = new DogMover(this.getSprite(), this.transform, 180, 180);
        this.addMover(dogMover);
        this.controller = controller;

    }

    /**
     * Returns the amount of coins earned from the last pet (click)
     * @return The amount of coins earned from the last pet.
     */
    public int getCoinsEarnedFromLastPet() {
        return this.coinsEarnedFromLastPet;
    }

    /**
     * Checks if the coordinates of the mouse click
     * are on the Dog's sprite.
     * @param mouseX the x coordinates of the mouse click.
     * @param mouseY mouseX the x coordinates of the mouse click.
     * @return whether the sprite was clicked or not.
     */
    @Override
    public boolean clicked(int mouseX, int mouseY) {
        double x = this.getX();
        double y = this.getY();
        int width = super.getSprite().getWidth();
        int height = super.getSprite().getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    /**
     * Pets the Dog, calculate exp and coins earned, update dog
     * when the dog's sprite is clicked.
     */
    @Override
    public void act() {
        int earnedCoin = myDog.calculateCoinsEarned();
        int earnedExp = myDog.calculateExpEarned();

        this.updateDog(earnedCoin, earnedExp);
        controller.getBank().increaseCoins(earnedCoin);
    }

    /**
     * Draw this dog on the screen.
     * @param g The implementation of IGameGraphics to use.
     * @param offsetX How much to offset the drawn image's x coordinate.
     * @param offsetY How much to offset the drawn image's y coordinate.
     */
    @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getSprite().getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        g.drawText("exp: " + Integer.toString(this.myDog.getExp()),
                drawnX + 30, drawnY + 95, Color.WHITE);
    }

    /**
     * Updates the dog's coin and experience
     * @param earnedCoin the number of coins earned.
     * @param earnedExp the amount of experience gained.
     */
    private void updateDog(int earnedCoin, int earnedExp) {
        this.myDog.setCoins(this.myDog.getCoins() + earnedCoin);
        this.myDog.setExp(this.myDog.getExp() + earnedExp);

        this.coinsEarnedFromLastPet = earnedCoin;
    }
    
}
