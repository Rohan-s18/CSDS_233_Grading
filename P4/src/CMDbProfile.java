import java.util.NoSuchElementException;
import java.util.ArrayList;

public class CMDbProfile {
  // Name of profile
  private String userName;
  // Profile's queue of movies with ratings
  private PriorityQueue<Integer, String> database;
  // Highest rating given by user in their profile
  private int highestRating;
  // List of movies with highest rating given by user
  private ArrayList<String> favoriteMovies;
  
  public CMDbProfile(String userName) {
    this.userName = userName;
    database = new PriorityQueue<Integer, String>();
    favoriteMovies = new ArrayList<String>();
  }
  
  public String getUserName() {
    return userName;
  }

  public void changeUserName(String userName) {
    this.userName = userName;
  }
  
  public boolean rate(String movie, int rating) {
    if (rating < 1 || rating > 10)
      return false;
    database.add(rating, movie);
    checkNewRating(movie, rating);
    return true;
  }
  
  public boolean changeRating(String movie, int newRating) {
    if (newRating < 0 || newRating > 10)
      return false;
    try {
      checkOldRating(movie, database.update(newRating, movie));
      checkNewRating(movie, newRating);
      return true;
    }
    catch (NoSuchElementException e) {
      return false;
    }
  }
  
  public boolean removeRating(String movie) {
    try {
      checkOldRating(movie, database.poll(movie));
      return true;
    }
    catch (NoSuchElementException n) {
      return false;
    }
  }
  
  public String[] favorite() {
    if (favoriteMovies.size() == 0) return null;
    return favoriteMovies.toArray(new String[favoriteMovies.size()]);
  }
  
  public String[] favorite(int k) {
    String[] favMovies = new String[k];
    try {
      Object[] o = database.peek(k);
      int i = 0;
      for (Object t : o) {
        favMovies[i] = (String) t;
        i++;
      }
      return favMovies;
    }
    catch (IllegalArgumentException i) {
      return favMovies;
    }
  }
  
  public String profileInformation() {
    return getUserName() + ' ' + '(' + database.size() + ')' + "\n" + "Favorite Movie: " + favoriteMovies.toArray()[0];
  }
  
  public boolean equals(Object o) {
    if (o instanceof CMDbProfile) {
      CMDbProfile p = (CMDbProfile) o;
      return p.getUserName().equals(getUserName());
    }
    return false;
  }

  // Recalculates favorite movie storage upon adding a new rating to database
  private void checkNewRating(String movie, int rating) {
    if (rating > highestRating) {
      highestRating = rating;
      favoriteMovies = new ArrayList<String>();
      favoriteMovies.add(movie);
    }
    else if (rating == highestRating)
      favoriteMovies.add(movie);
  }

  // Recalculates favorite movie storage after removing a new rating from database
  private void checkOldRating(String movie, int rating) {
    if (rating == highestRating) {
      favoriteMovies.remove(movie);
      if (favoriteMovies.size() == 0) { // No longer highest rating
        if (database.size() == 0) {
          highestRating = 0; // Reset
        }
        else {
          String tempMovie = database.peek();
          favoriteMovies.add(tempMovie);
          int highestRating = database.poll(tempMovie);
          while (database.size() > 0) {
            tempMovie = database.peek();
            int nextRating = database.poll(tempMovie);
            if (highestRating == nextRating) {
              favoriteMovies.add(tempMovie);
            }
            else { // All highest-rated movies already added
              database.add(nextRating, tempMovie);
              break;
            }
          }
          for (String m : favoriteMovies) {
            database.add(highestRating, m);
          }
        }
      }
    }
  }
}