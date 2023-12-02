import java.util.ArrayList;

public class userProfileInformation {
    private String username;
    private String profileImagePath;
    private int followerCount;
    private int followingCount;
    private int postCount;
    private ArrayList<String> followerNames;
    private ArrayList<String> followingNames;

    public userProfileInformation(String username, String profileImagePath, int followerCount, int followingCount, int postCount, ArrayList<String> followerNames, ArrayList<String> followingNames) {
        this.username = username;
        this.profileImagePath = profileImagePath;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.postCount = postCount;
        this.followerNames = followerNames;
        this.followingNames = followingNames;
    }

    // getter 메소드 추가
    public String getUsername() {
        return username;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public ArrayList<String> getFollowerNames() {
        return followerNames;
    }

    public ArrayList<String> getFollowingNames() {
        return followingNames;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public void addFollowerName(String name) {
        this.followerNames.add(name);
    }

    public void removeFollowerName(String name) {
        this.followerNames.remove(name);
    }

}
