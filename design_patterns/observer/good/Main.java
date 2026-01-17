public class Main {
    public static void main(String[] args) {

        YoutubeChannel newMrBestChannel = new YoutubeChannel();
        Subscriber alice = new Subscriber("Alice");
        Subscriber bob = new Subscriber("Bob");
        newMrBestChannel.subscribe(alice);
        newMrBestChannel.subscribe(bob);

        newMrBestChannel.uploadVideo("100 Bees vs 1 Man");
        newMrBestChannel.unsubscribe(bob);
        newMrBestChannel.uploadVideo("200 Bees vs 2 Man");
    }
}
