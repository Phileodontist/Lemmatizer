package Words;

/**
 * Identifies the suffix of the adverb, returning the lemma
 * of the passed in word
 * 
 * @author Leo Pascual
 * @since May 21, 2017
 */
public class AdverbSuffixes
{
	/**
	 * This method runs the passed in word through the different rules to
	 * extract the words lemma.
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - specified word
	 * @return lemma - the lemma of the passed in word
	 */
	public String adverbSuffixes(HashTable dictionary, String word)
	{
		String lemma = word;
		
		if(lemma.endsWith("ly"))
		{
			lemma = ly_suffix(dictionary, word);
		}
		
		if(lemma.endsWith("wards") || lemma.endsWith("ward"))
		{
			lemma = ward_s_suffix(dictionary, word);
		}
		
		if(lemma.endsWith("wise"))
		{
			lemma = wise_suffix(dictionary, word);
		}
		
		if (word.endsWith("er"))
		{
			lemma = er_suffix(dictionary,word);
		}
			
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
		
		if(lemma.endsWith("i"))
		{
			sb = new StringBuffer(word);
			lemma = sb.replace
					(word.length() - 3, word.length(), "y").toString();
		}
		
		if(lemma.endsWith("b"))
		{
			sb = new StringBuffer(word);
			lemma = sb.replace
					(word.length() - 2, word.length(), "le").toString();
		}
		
		return lemma;
	}
	
	/**
	 * Method for 'ward/wards' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ward_s_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 5, word.length()).toString();
		
		if(!(dictionary.contains(lemma)))
		{
			sb = new StringBuffer(word);
			lemma = sb.delete(word.length() - 4, word.length()).toString();
			
		}
		return lemma;
	}
	
	/**
	 * Method for 'wise' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String wise_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 4, word.length()).toString();
		
		return lemma;
	}
	
	/**
	 * Method for 'er' suffix
	 *  
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word 
	 */
	public static String er_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 2, word.length()).toString();
		
		if (!dictionary.contains(lemma))
		{
			lemma = sb.append("e").toString();
		}
		return lemma;
	}
	
}
