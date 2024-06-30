import java.util.ArrayList;
import java.util.Iterator;

public class TestSpellCheck {
    public static void main(String[] args) {
        SpellCheck sc = new SpellCheck();
        String[] sentences = {
            "This is a test run",
            "Does it works right",
            "CSE two one four homework",
            "How about abcd",
            "Complete the implementation and turn it in before the due date",
            "Any deviations from the instructed deliverable format will result in a deduction of grade",
            "You will be implementing a very simple spell checker for this assignment",
            "In particular you are to implement a method that returns all possible corrections to one or more typos",
            "A typo is characterized by the absence from the dictionary that will be provided to you",
            "The input will be a sentence of English words and the output will be a set of candidate corrections for all typos",
            "The basic algorithm is as follows", 
            "Store all valid words in the dictionary",
            "For each word in the given sentence see if the word is valid",
            "If it is valid then you dont have to provide any candidates",
            "If it is invalid you should prepare a list of valid correction candidates and make it part of the final output",
            "The valid correction candidates are prepared by the following modifications"
        };
        for(String sent : sentences) {
			String[] words = sent.split("\\s");
			ArrayList<String>[] ret = sc.spellCheck(sent);
			if(ret == null) continue;
			for(int i = 0; i < ret.length; i++) {
				String cand = "";
				if(ret[i] == null) {
					System.out.print(words[i] + " ");
					continue;
				}
				Iterator<String> it = ret[i].iterator();
				while(it.hasNext())
					cand += (it.next() + ",");
				System.out.print("(" + cand + ") ");
			}
			System.out.println();
		}
    }
}
