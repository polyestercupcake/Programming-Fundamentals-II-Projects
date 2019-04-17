package model;

/**
 * This interface defines the methods needed to save data to permanent storage.
 * 
 * @author cberkstresser
 * @version 1.2
 */
public interface IPermanentStorage {
	/**
	 * Saves the values stored in the class to permanent storage.
	 */
	void save();

	/**
	 * Deletes the currently loaded values from permanent storage.
	 */
	void delete();

	/**
	 * Loads a saved circuit from permanent storage.
	 * 
	 * @param id
	 *            The unique identifier of the class item to load.
	 */
	void load(int... id);
}