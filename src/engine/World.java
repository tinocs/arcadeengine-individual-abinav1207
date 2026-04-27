package engine;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
/*
* Author: Abhinav Patti
* Date: 23
* Description: Is this lab correct and working? (yes/no)
*/
public abstract class World extends Pane {


private AnimationTimer timer;
private boolean timerRunning = false;
private Set<KeyCode> currentlyPressed = new HashSet<>();


private boolean wSet = false;
private boolean hSet = false;
private boolean dimensionsInitialized = false;


public World() {
widthProperty().addListener(new ChangeListener<Number>() {
@Override
public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) {
if (newVal.doubleValue() > 0) {
wSet = true;
initializeDimensionsIfReady();
}
}
});


heightProperty().addListener(new ChangeListener<Number>() {
@Override
public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) {
if (newVal.doubleValue() > 0) {
hSet = true;
initializeDimensionsIfReady();
}
}
});


sceneProperty().addListener(new ChangeListener<Scene>() {
@Override
public void changed(ObservableValue<? extends Scene> arg0, Scene oldS, Scene newS) {
if (newS != null) {
requestFocus();
}
}
});


this.setOnKeyPressed(e -> currentlyPressed.add(e.getCode()));
this.setOnKeyReleased(e -> currentlyPressed.remove(e.getCode()));


timer = new AnimationTimer() {
@Override
public void handle(long now) {
act(now);


List<Actor> actors = new ArrayList<>(getObjects(Actor.class));
for (Actor a : actors) {
if (a.getWorld() == World.this) {
a.act(now);
}
}
}
};
}


private void initializeDimensionsIfReady() {
if (!dimensionsInitialized && wSet && hSet) {
dimensionsInitialized = true;
onDimensionsInitialized();
}
}


public void start() {
if (!timerRunning) {
timerRunning = true;
timer.start();
}
}


public void stop() {
if (timerRunning) {
timerRunning = false;
timer.stop();
}
}


public boolean isStopped() {
return !timerRunning;
}


public void add(Actor actor) {
getChildren().add(actor);
actor.addedToWorld();
}


public void remove(Actor actor) {
getChildren().remove(actor);
}


public <A extends Actor> List<A> getObjects(Class<A> cls) {
ArrayList<A> result = new ArrayList<>();
for (Object obj : getChildren()) {
if (cls.isInstance(obj)) {
result.add(cls.cast(obj));
}
}
return result;
}


public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls) {
List<A> list = getObjects(cls);
List<A> atPoint = new ArrayList<>();
for (A a : list) {
if (a.getBoundsInParent().contains(x, y)) {
atPoint.add(a);
}
}
return atPoint;
}


public boolean isKeyPressed(KeyCode code) {
return currentlyPressed.contains(code);
}


public abstract void act(long now);
public abstract void onDimensionsInitialized();
}