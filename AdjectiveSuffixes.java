package Words;

/**
 * Identifies the suffix of the adjective, returning the lemma
 * of the passed in word
 * 
 * @author Leo Pascual
 * @since May 16, 2017
 */
public class AdjectiveSuffixes
{
	/**
	 * This method runs the passed in word through the different rules to
	 * extract the words lemma.
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - specified word
	 * @return lemma - the lemma of the passed in word
	 */
	public String adjectiveSuffixes(HashTable dictionary, String word)
	{
	
		String lemma = word; // new word
		
		if (word.endsWith("able") || word.endsWith("ible"))
		{
			lemma = able_ible_suffix(dictionary,word);
		}
		
		if (word.endsWith("al"))
		{
			lemma = al_suffix(dictionary,word);
		}
		
		if (word.endsWith("en"))
		{
			lemma = en_suffix(dictionary,word);
		}
		
		if (word.endsWith("ese"))
		{
			lemma = ese_suffix(dictionary,word);
		}
		
		if (word.endsWith("ful"))
		{
			lemma = ese_suffix(dictionary,word);
		}
		
		if (word.endsWith("ic"))
		{
			lemma = ic_suffix(dictionary,word);
		}
		
		if (word.endsWith("ish"))
		{
			lemma = ish_suffix(dictionary,word);
		}
		
		if (word.endsWith("ive"))
		{
			lemma = ive_suffix(dictionary,word);
		}
		
		if (word.endsWith("less"))
		{
			lemma = less_suffix(dictionary,word);
		}
		
		if (word.endsWith("ly"))
		{
			lemma = ly_suffix(dictionary,word);
		}
		
		if (word.endsWith("y"))
		{
			lemma = y_suffix(dictionary,word);
		}
		
		if (word.endsWith("est"))
		{
			lemma = est_suffix(dictionary,word);
		}
		
		if (word.endsWith("ing"))
		{
			lemma = ing_suffix(dictionary,word);
		}
			
		return lemma;
			
	}
	
	/**
	 * Method for 'able/ible' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String able_ible_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 4, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'al' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String al_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 2, word.length()).toString();
		
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.append("e").toString();
		}
		
		return lemma;
	}
	
	/**
	 * Method for 'en' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String en_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 2, word.length()).toString();
		
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.append("e").toString();
		}
		
		return lemma;
	}
	
	/**
	 * Method for 'ese' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ese_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'ful' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ful_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'ic' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ic_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 2, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'ish' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ish_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'ive' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ive_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		
		return lemma;
	}
	
	
	/**
	 * Method for 'less' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String less_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 4, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'ly' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ly_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 2, word.length()).toString();
		
		if (dictionary.contains(lemma) == false)
		{
			lemma = sb.append("ly").toString();
		}
		
		return lemma;
	}
	
	/**
	 * Method for 'y' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String y_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 1, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'est' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - the current word
	 * @return lemma - the lemma of the current word
	 */
	public static String est_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'ing' suffix
	 *  
	 * @param dictionary - Passed in dictionary
	 * @param word - the current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ing_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		lemma = sb.append("e").toString();
		
		return lemma;
	}
	
}
