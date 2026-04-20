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

public abstract class World extends Pane {

    private AnimationTimer timer;
    private boolean isTimerRunning = false;
    private Set<KeyCode> keysCurrentlyPressed = new HashSet<KeyCode>();
    
    private boolean wSet = false;
    private boolean hSet = false;

    public World() {
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) {
                if (newVal.doubleValue() > 0) {
                    wSet = true;
                    if (wSet && hSet) {
                        onDimensionsInitialized();
                    }
                }
            }
        });

        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) {
                if (newVal.doubleValue() > 0) {
                    hSet = true;
                    if (wSet && hSet) {
                        onDimensionsInitialized();
                    }
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

        this.setOnKeyPressed(e -> {
            keysCurrentlyPressed.add(e.getCode());
        });
        
        this.setOnKeyReleased(e -> {
            keysCurrentlyPressed.remove(e.getCode());
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                List<Actor> actors = getObjects(Actor.class);
                for (int i = 0; i < actors.size(); i++) {
                    Actor a = actors.get(i);

                    a.act(now);
                }
            }
        };
    }

    public void start() {
        isTimerRunning = true;
        timer.start();
    }

    public void stop() {
        isTimerRunning = false;
        timer.stop();
    }

    public boolean isStopped() {
        return isTimerRunning == false; 
    }

    public void add(Actor actor) {
        getChildren().add(actor);

    }

    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> result = new ArrayList<A>();
        for (Object obj : getChildren()) {
            if (cls.isInstance(obj)) {
                result.add(cls.cast(obj));
            }
        }
        return result;
    }

    public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls) {
        List<A> list = getObjects(cls);
        List<A> atPoint = new ArrayList<A>();
        for (A a : list) {
            if (a.getBoundsInParent().contains(x, y)) {
                atPoint.add(a);
            }
        }
        return atPoint;
    }

    public boolean isKeyPressed(KeyCode code) {
        return keysCurrentlyPressed.contains(code);
    }

    public abstract void act(long now);
    public abstract void onDimensionsInitialized();
}