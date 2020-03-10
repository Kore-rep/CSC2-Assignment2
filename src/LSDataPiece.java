/** @author Angus Longmore
 *  @author lngang002@myuct.ac.za
 *  @version 1.0
 *    
 */

/** A class that stores data given by the text file
 * 
 */
public class LSDataPiece implements Comparable<LSDataPiece> {

    private String sourceLine;
    private String key;
    private String areas;
    public Integer opCount;

    /** Constructs the object with given text file line
     * 
     * @param line Specified line from text file to be broken down
     */
    public LSDataPiece(String line) {
        this.sourceLine = line;
        this.processLine(line);
    }

    /** Breaks down the given line into it's parts
     *  
     * @param line specified line from text file
     */
    private void processLine(String line) {
        int spaceIndex = line.indexOf(" ");

        if (spaceIndex == -1) {
            this.key = line;
        } else {
            this.key = line.substring(0, spaceIndex);
            this.areas = line.substring(spaceIndex + 1);
        }
    }


    /** Accessor method for sourceLine
     * 
     * @return The source line
     */
    public String getSource() {
        return this.sourceLine;
    }

    /** Accessor method for key
     * 
     * @return The object key
     */
    public String getKey() {
        return this.key;
    }

    /** Accessor method for areas
     * 
     * @return The object areas
     */
    public String getAreas() {
        return this.areas;
    }

    /** Determines if a given object is equal to this one
     *  
     * @param o object to be compared
     */
    public boolean equals(Object o) {
        if (this.getClass( ) != o.getClass( ))
           return false;
        else {
           LSDataPiece otherLSDataPiece = (LSDataPiece)o;
           if (otherLSDataPiece.getKey().compareTo(this.getKey()) == 0) {
              if (otherLSDataPiece.getAreas().compareTo(this.getAreas()) == 0) {
                 return true;
              }
           }
        }
        return false;
    }

    /** Compares two LSDataPiece objects
     * 
     * @param other the other LSDataPiece to be compared to
     */
    public int compareTo(LSDataPiece other) {
        return this.getKey().compareTo(other.getKey());
    }

    /** Returns a string representation of this object
     * 
     * @return The source line
     */
    public String toString() {
        return this.sourceLine;
    }

}