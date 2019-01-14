// TODO:  complete the methods
/**
 * @author Archie_Paredes
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * @objecttype Immutable Data Class
 * @objectinvariant
 *   Title is non-null, no leading or final spaces, not empty string.
 * @objectinvariant
 *   Year is greater than 1800, less than 5000.
 * @objectinvariant
 *   Director is non-null, no leading or final spaces, not empty string.
 */
final class VideoObj implements Comparable {
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _title;
  /** @invariant greater than 1800, less than 5000 */
  private final int    _year;
  /** @invariant non-null, no leading or final spaces, not empty string */
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if any object invariant is violated.
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
   * Return the value of the attribute.
   */
  public String director() {
    return _director;
  }

  /**
   * Return the value of the attribute.
   */
  public String title() {
    return _title;
  }

  /**
   * Return the value of the attribute.
   */
  public int year() {
    return _year;
  }

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return deep equality test between this and thatObject.
   */
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

  /**
   * Return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode() {
    int code = 17;
    code = 37*code + _title.hashCode();
    code = 37*code + _year;
    code = 37*code + _director.hashCode();

    return (code);
  }

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param thatObject the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater thatObject.
   * @throws ClassCastException if thatObject has an incompatible type.
   */
  public int compareTo(Object thatObject) {
    VideoObj i = (VideoObj) thatObject;
    // compare year
    if (i._year < _year) return -1;
    if (i._year > _year) return 1;

    // compare title
    int a, b;
    if (i._title.length() < _title.length()) return -1;
    if (i._title.length() > _title.length()) return 1;
    for(int j = 0; j < i._title.length(); j++){
      a = i._title.charAt(j);
      b = _title.charAt(j);
      if (a < b) return -1;
      if (a > b) return 1;
    }

    // compare director
    if (i._director.length() < _director.length()) return -1;
    if (i._director.length() > _director.length()) return 1;
    for(int j = 0; j < i._director.length(); j++){
      a = i._director.charAt(j);
      b = _director.charAt(j);
      if (a < b) return -1;
      if (a > b) return 1;
    }

    return 0;
  }

  /**
   * Return a string representation of the object in the following format:
   * <code>"title (year) : director"</code>.
   */
  public String toString() {
    String s = _title + " (" +_year + ") : " + _director;
    return s;
  }
}
