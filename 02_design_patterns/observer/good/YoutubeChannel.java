import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel implements Subject {
    List<Observer> observerList;

    YoutubeChannel() {
        this.observerList = new ArrayList<>();
    }

    public void subscribe(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        observerList.remove(o);
    }

    public void uploadVideo(String s) {
        notifySubscribers(s);
    }

    @Override
    public void notifySubscribers(String s) {
        for (Observer observer : observerList) {
            observer.update(s);
        }
    }

}
