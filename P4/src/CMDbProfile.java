public class CMDbProfile {
    public CMDbProfile(String userName) {}
    public void changeUserName(String userName) {}
    public boolean rate(String movie, int rating) {return false;}
    public boolean changeRating(String movie, int newRating) {return false;}
    public boolean removeRating(String movie) {return false;}
    public String[] favorite() {return null;}
    public String[] favorite(int k) {return null;}
    public String profileInformation() {return null;}
    public boolean equals(Object o) {return false;}
}
