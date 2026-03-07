/**The purpose of the rareHashCandy class is to implement the logic for the Hash table.
 * It has functionalities such as displaying the count, load factors, printing hash table,
 * adding and deleting entries. */

public class rareHashCandy {
	 private PokeEntry[] pokeEntrytable; 
	 private int counter; // 
	 private final double load_factor = 0.7; // map will resize(increase size) if load factor exceeds 70%, goal is to avoid collisions
	 
private static class PokeEntry {
	String pokeKey;      // The Pokemon's name 
    String pokeValue;    // The line in CSV for Pokemon
    boolean fainted; // Grave stone entry
    //Constructor
public PokeEntry(String pokeKey, String pokeValue) {
    this.pokeKey = pokeKey;
    this.pokeValue = pokeValue;
    this.fainted = false; 
    }
}
/**sets counter to zero and a prime number was chosen for the array size to help reduce collisions
 * ny distributing keys more evenly.
 * 1777 was chosen since it is a prime number greater than 800/.70 */
public rareHashCandy() {
    this.pokeEntrytable = new PokeEntry[1777];
    this.counter = 0;
}
/** Purpose of hashIndex() is to calculate the hash index(i.e where key-values should be stored)
 *  based on its hash code. The method makes sure the hash is positive and stays within
 *  the bounds of the table's array by using modulo */
public int hashIndex(String key) {
    int hash = key.hashCode();
    hash = Math.abs(hash); 
    return hash % pokeEntrytable.length; // makes table fit within the bounds of array, hashes by division
}
/** The purpose of rehash() method is to
 * resize the table when it reaches past 70% load capacity.
 * this helps to keep avg efficiency at Θ(1) and avoid collisions */
private void rehash() {
	PokeEntry[] tempTable = this.pokeEntrytable;
	
	/**increases size of array by 1, if i had time i would have tried 
	 * to instead choose to resize to the next prime number*/
	this.pokeEntrytable = new PokeEntry[tempTable.length + 1]; 
	this.counter = 0;
	
	for (int i = 0; i < tempTable.length; i++) {
		
		if (tempTable[i] != null && !tempTable[i].fainted) {
			this.add(tempTable[i].pokeKey, tempTable[i].pokeValue);
			
		}
	}
}
/** The purpose of add() method is to add new key-values to the HashMap
 * It first checks to see if hash table has reached load capacity, then
 * it loops through the map to look for an empty bucket to insert an entry
 * Lastly it increments the counter to keep track of the number of items in table */

public boolean add(String pokeKey, String pokeValue) {
	// If the load factor (n / N) exceeds the threshold (load_factor λ), rehash to reduce potential collisions
	if (((double)this.counter / this.pokeEntrytable.length) > load_factor) { 

        this.rehash();  
    }
	
	int index = hashIndex(pokeKey); 
	// the idea of this loop is to keep searching for an empty bucket to insert an entry
	while(this.pokeEntrytable[index] != null && !this.pokeEntrytable[index].fainted) {
    	 index = (index + 1) % this.pokeEntrytable.length; // move to next slot if it is occupied, modulo helps wrap around table if end of table is reached
     }
     PokeEntry newEntry = new PokeEntry(pokeKey, pokeValue); //holds key value pair
     
     this.pokeEntrytable[index] = newEntry;
     
     // increments the count of items in the table
     this.counter++;
     
     return true; 
}

/** The purpose of find() method is is to find (using linear probing) the key-value pair for 
 * the pokemon the user is looking for and returning it */

public String find(String pokeKey) {
	int index = hashIndex(pokeKey);
	// tries to find pokemon, if we find it, return the pokemon key value, otherwis keep probing
	
	while(this.pokeEntrytable[index] != null) {
   	if (pokeEntrytable[index].pokeKey.equals(pokeKey) && !pokeEntrytable[index].fainted) {
   		return pokeEntrytable[index].pokeValue;
   	 }
   	else {
   		index = (index + 1) % this.pokeEntrytable.length; // moves to next slot in array, if array reaches end, modulo will help wrap around
   	}
        }
	return null;
}
/** The purpose of the delete() method is to delete entries in the map and
 * replace them with a gravestone "fainted" placeholder.
 * This place holder is needed since the search would stop if a null value was found; the 
 * program would assume the new pokemon inserted was not in the map */
public boolean delete(String pokeKey) {
	int index = hashIndex(pokeKey);
	
	while(this.pokeEntrytable[index] != null) {
	   	if (pokeEntrytable[index].pokeKey.equals(pokeKey) && !pokeEntrytable[index].fainted) {
	   		
	   		pokeEntrytable[index].fainted = true; //once entry is delete, its replaced with grave stone
            this.counter--;
            
            return true;
	   	}
	   	else {
	   		index = (index + 1) % this.pokeEntrytable.length;
	   	}
	        }
	    return false;
}
public int count() {
    return this.counter;
}
/** the purpose of getLoadFactor() is to return the current load factor 
 * so that the developer or user knows how close it is to resizing it */

public double getLoadFactor() { // λ= n/N, current num elements/length of array
    return (double)this.counter / this.pokeEntrytable.length;
}

/** the purpose of getMaxLoadFactor() is to return the max load factor*/
public double getMaxLoadFactor() {
    return this.load_factor;
}
/** The purpose of the printHT() method is to print out the entire pokedex.
 *It automatically skips over any grave stones/null entries */
public String printHT() {
	for (int i = 0; i < this.pokeEntrytable.length; i++) {
		if (this.pokeEntrytable[i] != null && !this.pokeEntrytable[i].fainted) {
			System.out.println(this.pokeEntrytable[i].pokeKey + " : " + this.pokeEntrytable[i].pokeValue);
		}
	}
	return null;
}
}
