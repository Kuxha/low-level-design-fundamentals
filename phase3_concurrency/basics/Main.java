package concurrency.basics;

public class Main {
    public static void main(String[] args) {
        DownloadTask download = new DownloadTask();
        MusicTask music = new MusicTask();
        Thread t1 = new Thread(download);
        // music.run();
        Thread t2 = new Thread(music);

        t1.start();
        t2.start();
    }
}
