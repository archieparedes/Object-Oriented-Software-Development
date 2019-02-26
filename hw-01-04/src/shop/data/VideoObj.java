package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   *     Initialize all object attributes.
   *     Title and director are "trimmed" to remove leading and final space.
   *     @throws IllegalArgumentException if object invariant violated.
   * @param title Video title
   * @param year Year made
   * @param director Director name
   */
  VideoObj(String title, int year, String director) {
    // checks for a valid title
    if (title == null) {
      throw new IllegalArgumentException("Title string is null.");
    } else if(title.length() == 0){
      throw new IllegalArgumentException("Title is empty.");
    } else if (title.charAt(0) == ' ' || title.charAt(title.length()-1) == ' '){
      String newTitle = "";
      for(int i = 1; i < title.length(); i++){
        if(i == title.length() - 1 && title.charAt(i) == ' '){
          break;
        }
        newTitle += title.charAt(i);
      }

      if(newTitle == "" || newTitle.charAt(0) == ' ') throw new IllegalArgumentException("Title entered is empty");
      _title = newTitle;
    } else {
      if (title.length() == 1 && title.charAt(0) == ' '){
        throw new IllegalArgumentException("Title entered is empty");
      }
      _title = title;
    }

    // checks for a valid year
    if (year > 1800 && year < 5000){
      _year = year;
    } else {
      throw new IllegalArgumentException("Year integer is either less than 1800 or greater than 5000.");
    }

    // checks for a valid director
    if (director == null) {
      throw new IllegalArgumentException("director string is null.");
    } else if(director.length() == 0){
      throw new IllegalArgumentException("director is empty.");
    } else if (director.charAt(0) == ' ' || director.charAt(director.length()-1) == ' '){
      String newdirector = "";
      for(int i = 1; i < director.length(); i++){
        if(i == director.length() - 1 && director.charAt(i) == ' '){
          break;
        }
        newdirector += director.charAt(i);
      }
      if(newdirector == "" || newdirector.charAt(0) == ' ') throw new IllegalArgumentException("Director entered is empty");
      _director = newdirector;
    } else {
      _director = director;
    }
  }

  /**
   *
   * @return Director name
   */
  public String director() {
    return _director;
  }

  /**
   *
   * @return Title name
   */
  public String title() {
    return _title;
  }

  /**
   *
   * @return year made
   */
  public int year() {
    return _year;
  }

  public boolean equals(Object thatObject) {
    if (this == thatObject){
      return true;
    }
    if (!(thatObject instanceof VideoObj)){
      return false;
    }

    VideoObj i = (VideoObj) thatObject;
    return( i._year == _year &&
            i._director.equals(_director) &&
            i._title.equals(_title));
  }

  public int hashCode() {
    int code = 17;
    code = 37*code + _title.hashCode();
    code = 37*code + _year;
    code = 37*code + _director.hashCode();

    return (code);
  }

  public int compareTo(Object thatObject) {
    VideoObj that = (VideoObj) thatObject;

    int titleCompare = _title.compareTo(that._title);
    if(titleCompare != 0)   return titleCompare;

    int yearCompare = _year - that._year;
    if(yearCompare != 0)    return yearCompare;

    int directorCompare = _director.compareTo(that._director);
    if(directorCompare != 0)    return directorCompare;

    return 0;
  }

  public String toString() {
    return(_title + " (" +_year + ") : " + _director);
  }
}
