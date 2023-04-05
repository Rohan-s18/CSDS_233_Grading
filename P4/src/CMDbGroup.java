import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CMDbGroup {
  // Names of members of this group
  private ArrayList<String> groupNames;
  // All members of this group
  private ArrayList<CMDbProfile> groupMembers;
  // Hash table of movies from all members and their frequency of appearing on lists
  private HashTable<CMDbProfile> groupRatings;
  // All historic members of any CMDbGroup
  private static HashTable<CMDbProfile> allMembers = new HashTable<CMDbProfile>();
  
  // Default constructor for an empty CMDbGroup 
  public CMDbGroup() {
    groupNames = new ArrayList<String>();
    groupMembers = new ArrayList<CMDbProfile>();
    groupRatings = new HashTable<CMDbProfile>();
  }
  
  // Getters and setters for fields 
  private HashTable<CMDbProfile> groupRatings() {return groupRatings;}
  private ArrayList<String> groupNames() {return groupNames;}
  private ArrayList<CMDbProfile> groupMembers() {return groupMembers;}
  public static HashTable<CMDbProfile> registeredUsers() {return allMembers;}
  
  public String[] group() {return (String[]) groupNames().toArray();}
  
  public void addMember(CMDbProfile member) {
    groupNames().add(member.getUserName());
    groupMembers().add(member);
    groupRatings().put(member.getUserName(), member);
    registeredUsers().put(member.getUserName(), member);
  }
  
  public void addMember(CMDbProfile[] members) {
    for (int i = 0; i < members.length; i++) {
      groupNames().add(members[i].getUserName());
      groupMembers().add(members[i]);
      groupRatings().put(members[i].getUserName(), members[i]);
      registeredUsers().put(members[i].getUserName(), members[i]);
    }
  }
  
  public static String favorite(String userName) {
    try {
      return null; //registeredUsers().get(userName).getList().peek();
    }
    catch (NoSuchElementException n) {
      return null;
    }
  }
  
  public String[] groupFavorites() {
    return null;
  }
  
  public String randomMovie(int k) {
    // ArrayList to hold group members randomely chosen
    ArrayList<CMDbProfile> selectedMembers = new ArrayList<CMDbProfile>();
    // Choose a random group member and add it to selectedMembers k times
    for (int i = 0; i < k; i++) {
      int randIndex = (int)(Math.random() * ((groupMembers.size() - 1) + 1));
      selectedMembers.add(groupMembers().get(randIndex));
    }
    // Hash table to count number of times a group member has been selected
    HashTable<Integer> selectedCount = new HashTable<Integer>();
    for (CMDbProfile member : selectedMembers) 
      selectedCount.put(member.getUserName(), 1);
    // Remove duplicate members from selectedMembers
    selectedMembers = removeDuplicates(selectedMembers);
    // Find name from selectedCount in selectedMembers, and assign their favorite count's movies to ArrayList<String> movies
    ArrayList<String> movies = new ArrayList<String>();
    for (CMDbProfile member : selectedMembers) {
      for (String movie : member.favorite(selectedCount.get(member.getUserName())))
        movies.add(movie);
    }
    int randIndex = (int)(Math.random() * ((movies.size() - 1) + 1));
    return movies.get(randIndex);
  }
  
  // Helper method to remove duplicate profiles from a list of profiles
  private ArrayList<CMDbProfile> removeDuplicates(ArrayList<CMDbProfile> list) {
    ArrayList<CMDbProfile> newList = new ArrayList<CMDbProfile>();
    for (CMDbProfile member : list) {
      if (!newList.contains(member))
        newList.add(member);
    }
    return newList;        
  }
}