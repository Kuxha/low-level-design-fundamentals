
public class Main {
    public static void main(String[] args) {

        YoutubeChannelBad mrbest = new YoutubeChannelBad();
        mrbest.uploadVideo("the best of the best");
        // but how many times do we do this
        // we goota keep checking
        System.out.println("User check " + mrbest.getVideo());
    }
}
