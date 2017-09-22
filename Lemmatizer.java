package Words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

/**
 * Initiates the lemmatizing process, going through a pipeline to
 * clean the input string and to acquire the necessary POS tags.
 * 
 * @author Leo Pascual
 * @since May 16, 2017
 */
public class Lemmatizer
{
	static int defaultsize = 5;
	static HashTable dictionary = new HashTable(defaultsize);

	/**
	 * Executes the lemmatizing process
	 *
	 * @param args - Input passed in from the command line
	 */
	public static void main(String[] args)
	{
		// Reads in input sentence
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Sentence: ");
		String inputString = scanner.nextLine();

		StringTokenizer string = new StringTokenizer(inputString);
		String[] sent = new String[string.countTokens()];
		String[] posTags = new String[string.countTokens()];

		// Strips and tokenizes the words of the sentence
		sent = puncStripper(string, sent);

		// Loads in the model provide by OpenNLP
		POSModel model = new POSModelLoader().load(new File(
				"C:/Users/Leo Pascual/Desktop/Com Ling/OpenNLP/apache-opennlp-"
						+ "1.6.0-src/opennlp-tools/src/main/resources/"
						+ "en-pos-perceptron.bin"));

		// Tags each word of the sentence with a POS
		POSTaggerME posTagger = new POSTaggerME(model);
		posTags = posTagger.tag(sent);

		// Scans the passed in dictionary 
		scanDic(args, dictionary);

		// Iterates through both lists to derive each word
		String[] lemmas = lemmatize(dictionary, sent, posTags);

		printResults(sent, posTags, lemmas); 

		scanner.close();
	}

	/**
	 * Prints the results of the program
	 * 
	 * @param sent - The input sentence
	 * @param posTags - The corresponding POS of each word
	 * @param lemmas - The corresponding lemma of each word
	 */
	private static void printResults(String[] sent, String[] posTags,
			String[] lemmas)
	{
		// Prints each word of the sentence and it's POS
		for (int i = 0; i < sent.length; i++)
		{
			System.out.println(posTags[i] + " " + sent[i]);
		}
		
		System.out.println("\n");

		// Prints each word and it's lemma
		for (int i = 0; i < sent.length; i++)
		{
			System.out.println(sent[i] + " " + lemmas[i]);
		}

		// Prints entire sentence
		System.out.println("\nSentence: ");

		// Prints the entire sentence with each word as it's lemma 
		for (int i = 0; i < sent.length; i++)
		{
			System.out.print(lemmas[i] + " ");
		}
		
	}

	/**
	 * Strips string of punctuation
	 * 
	 * @param string - The input string
	 * @return the sentence without any punctuation
	 */
	private static String[] puncStripper(StringTokenizer string, String[] sent)
	{
		// Iterates through the list of tokenized words
		for (int i = 0; string.hasMoreTokens(); i++)
		{
			String currToken = string.nextToken().toLowerCase();
			StringBuffer sb = new StringBuffer(currToken);

			if (currToken.endsWith(",") || currToken.endsWith(".") || currToken
					.endsWith("!") || currToken.endsWith("?"))
			{
				currToken = sb.deleteCharAt(currToken.length() - 1).toString();
				sent[i] = currToken;
			}
			else
			{
				sent[i] = currToken;
			}
		}

		return sent;
	}

	/**
	 * Reads in the passed in dictionary into a hashtable
	 * 
	 * @param args - Input passed in from the command line
	 * @param dictionary - List of lemmas of words
	 */
	private static void scanDic(String[] args, HashTable dictionary)
	{
		try
		{
			Scanner scan = new Scanner(new File(args[0]));

			// Scans next word until file is empty
			while (scan.hasNext())
			{
				String word = scan.next().toLowerCase();

				dictionary.insert(word); // Insert word into HashTable
			}
			scan.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}

	}

	/**
	 * Lemmatizes each word within the input sentence
	 * 
	 * @param dictionary - Passed in dictionary
	 * @param sent - The input string
	 * @param pos - The part of speech of each word
	 */
	private static String[] lemmatize(HashTable dictionary, String[] sent,
			String[] pos)
	{
		String[] lemmatizedSent = new String[sent.length];

		for (int i = 0; i < sent.length; i++)
		{
			lemmatizedSent[i] = reduce(dictionary, sent[i], pos[i]);
		}

		return lemmatizedSent;
	}

	/**
	 * Converts words of the passed in string into their respective lemmas
	 * 
	 * @param args - Input string passed in from the command line
	 * @param dictionary - Passed in dictionary
	 * @param pos - POS passed in from command line
	 */
	private static String reduce(HashTable dictionary, String token, String pos)
	{
		// POS objects that perform the lemmatizing process
		NounSuffixes nounReducer = new NounSuffixes();
		VerbSuffixes verbReducer = new VerbSuffixes();
		AdverbSuffixes adverbReducer = new AdverbSuffixes();
		AdjectiveSuffixes adjectiveReducer = new AdjectiveSuffixes();

		String word = token.toLowerCase();
		String lemma = null; 

		if (pos.startsWith("NN"))
		{
			lemma = nounReducer.nounSuffixes(dictionary, word);
		}
		else if (pos.startsWith("JJ"))
		{
			lemma = adjectiveReducer.adjectiveSuffixes(dictionary, word);
		}
		else if (pos.startsWith("VB"))
		{
			lemma = verbReducer.verbSuffixes(dictionary, word);
		}
		else if (pos.startsWith("RB"))
		{
			lemma = adverbReducer.adverbSuffixes(dictionary, word);
		}
		else // Returns the original word as it's lemma
		{
			lemma = word;
			return lemma;
		}
		
		// If a lemma isn't found within the dictionary, 
		// return original word
		if (dictionary.contains(lemma) != true)
		{
			lemma = word;
		}

		return lemma;

	}

}
