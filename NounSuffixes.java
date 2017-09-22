package Words;

/**
 * Identifies the suffix of the noun, returning the lemma
 * of the passed in word
 * 
 * @author Leo Pascual
 * @since May 21, 2017
 */
public class NounSuffixes
{
	/**
	 * This method runs the passed in word through the different rules to
	 * extract the words lemma.
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the passed in word
	 */
	public String nounSuffixes(HashTable dictionary, String word)
	{
		String lemma = word; // new word

		if (word.endsWith("s"))
		{
			lemma = s_suffix(dictionary, word);
		}

		if (word.endsWith("es"))
		{
			lemma = es_suffix(dictionary, word);
		}

		if (word.endsWith("acy"))
		{
			lemma = acy_suffix(dictionary, word);
		}

		if (word.endsWith("al"))
		{
			lemma = al_suffix(dictionary, word);
		}

		if (word.endsWith("ance") || word.endsWith("ence"))
		{
			lemma = ae_nce_suffix(dictionary, word);
		}

		if (word.endsWith("dom"))
		{
			lemma = dom_suffix(dictionary, word);
		}

		if (word.endsWith("er") || word.endsWith("or"))
		{
			lemma = eo_r_suffix(dictionary, word);
		}

		if (word.endsWith("ism"))
		{
			lemma = ism_suffix(dictionary, word);
		}

		if (word.endsWith("ist"))
		{
			lemma = ist_suffix(dictionary, word);
		}

		if (word.endsWith("ity") || word.endsWith("ty"))
		{
			lemma = i_ty_suffix(dictionary, word);
		}

		if (word.endsWith("ment"))
		{
			lemma = ment_suffix(dictionary, word);
		}

		if (word.endsWith("ness"))
		{
			lemma = ness_suffix(dictionary, word);
		}

		if (word.endsWith("ship"))
		{
			lemma = ship_suffix(dictionary, word);
		}

		if (word.endsWith("sion") || word.endsWith("tion"))
		{
			lemma = s_t_ion_suffix(dictionary, word);
		}
		
		if (word.endsWith("ing"))
		{
			lemma = ing_suffix(dictionary, word);
		}

		return lemma;
	}

	/** TODO TODO TODO TODO TODO Noun Suffix TODO TODO TODO TODO TODO */

	/**
	 * Method for 's' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String s_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.deleteCharAt(word.length() - 1).toString();

		return lemma;
	}

	/**
	 * Method for 'es' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String es_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 2, word.length()).toString();
		
		if (dictionary.contains(lemma) == false)
		{
			lemma = sb.append('e').toString();
		}

		return lemma;
	}

	/**
	 * Method for 'acy' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String acy_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.replace(word.length() - 2, word.length(), "te").toString();

		return lemma;
	}

	/**
	 * Method for 'al' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String al_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.replace(word.length() - 2, word.length(), "e").toString();

		// Accommodates words that don't need an 'e'
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.delete(word.length() - 2, word.length()).toString();
		}

		return lemma;
	}

	/**
	 * Method for 'ance/ence' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String ae_nce_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 4, word.length()).toString();

		// Accommodates words like 'rely'
		if ((dictionary.contains(lemma) != true) && (lemma.endsWith("i")))
		{
			lemma = sb
					.replace(lemma.length() - 1, word.length(), "y")
					.toString();
		}

		return lemma;
	}

	/**
	 * Method for 'dom' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String dom_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 3, word.length()).toString();

		return lemma;
	}

	/**
	 * Method for 'er/or' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String eo_r_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 2, word.length()).toString();

		// Accommodate words that end with 't' like 'terminator'
		if (dictionary.contains(lemma) != true)
		{
			lemma = sb.append('e').toString();
		}

		return lemma;
	}

	/**
	 * Method for 'ism' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String ism_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 3, word.length()).toString();

		return lemma;
	}

	/**
	 * Method for 'ist' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String ist_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 3, word.length()).toString();

		return lemma;
	}

	/**
	 * Method for 'ity/ty' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String i_ty_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		if (word.endsWith("ity"))
		{
			lemma = sb.delete(word.length() - 3, word.length()).toString();
		}
		else
		{
			lemma = sb.delete(word.length() - 2, word.length()).toString();
		}

		return lemma;
	}

	/**
	 * Method for 'ment' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String ment_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 4, word.length()).toString();

		return lemma;
	}

	/**
	 * Method for 'ness' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String ness_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 4, word.length()).toString();

		return lemma;
	}

	/**
	 * Method for 'ship' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String ship_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 4, word.length()).toString();

		return lemma;
	}

	/**
	 * Method for 'stion/tion' suffix
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param word - current word
	 * @return lemma - the lemma of the current word
	 */
	private static String s_t_ion_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		if (word.endsWith("ation"))
		{
			lemma = sb
					.replace(word.length() - 5, word.length(), "e")
					.toString();
		}
		else
		{
			sb.delete(word.length() - 4, word.length()).toString();
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
	private static String ing_suffix(HashTable dictionary, String word)
	{
		String lemma = null;
		StringBuffer sb = new StringBuffer(word);

		lemma = sb.delete(word.length() - 3, word.length()).toString();

		sb = new StringBuffer(lemma);

		if (dictionary.contains(lemma) == false)
		{
			lemma = sb.append("e").toString();
		}

		return lemma;
	}
}
