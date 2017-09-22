package Words;


/**
 * Identifies the suffix of the verb, returning the lemma
 * of the passed in word 
 * 
 * @author Leo Pascual
 * @since May 21, 2017
 */
public class VerbSuffixes
{
	/**
	 * This method runs the passed in word through the different rules to
	 * extract the words lemma.
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - specified word
	 * @return lemma - the lemma of the passed in word
	 */
	public String verbSuffixes(HashTable dictionary, String word)
	{
		String lemma = word; // new word
		
		if (word.matches("is|been|being"))
		{
			lemma = be_lemma(dictionary, word);
			return lemma;
		}
		
		if (word.endsWith("ing"))
		{
			lemma = ing_suffix(dictionary, word);
		}
		
		if (word.endsWith("ed"))
		{
			lemma = ed_suffix(dictionary,word);
		}
		
		if ( word.endsWith("en"))
		{
			lemma = en_suffix(dictionary,word);
		}
		
		if ( word.endsWith("ify"))
		{
			lemma = ify_suffix(dictionary,word);
		}
		
		if ( word.endsWith("ize"))
		{
			lemma = ize_suffix(dictionary,word);
		}
		
		if ( word.endsWith("s"))
		{
			lemma = s_suffix(dictionary,word);
		}
		
		return lemma;
		
	}
	
	/**
	 * Method for 'ing' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ing_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();
		
		sb = new StringBuffer(lemma);
		
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.append("e").toString();
		}
		
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.delete(lemma.length() - 1, lemma.length()).toString();
		}
		
		return lemma;
		
	}
	
	/**
	 * Method for 'ed' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ed_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 2, word.length()).toString();

		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.append('e').toString();
		}
		
		sb = new StringBuffer(lemma);
		
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.delete(lemma.length() - 2, lemma.length()).toString();
			lemma = sb.append('y').toString();
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

		return lemma;
	}
	
	/**
	 * Method for 'ify' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ify_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.replace(word.length() - 3, word.length(), "ity").toString();
		
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.replace
					(word.length() - 3, word.length(), "y").toString();
		}
		return lemma;
	}
	
	/**
	 * Method for 'ize' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String ize_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 3, word.length()).toString();

		return lemma;
	}
	
	/**
	 * Method for 's' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	public static String s_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);
		
		lemma = sb.delete(word.length() - 1, word.length()).toString();

		return lemma;
	}
	
	/**
	 * Returns 'be' as the lemma of the 'be' verbs Ex. 'is' 'are' etc.
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - the current word
	 * @return lemma - the lemma of the current word
	 */
	public static String be_lemma(HashTable dictionary, String word)
	{
		String lemma = null;

		return lemma = "be";
	}
}
