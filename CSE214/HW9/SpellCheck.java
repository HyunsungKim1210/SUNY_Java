import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * 
 * Name: Hyunsung Kim
 * Your implementation should be as efficient as possible in terms of the time complexity. You're free to import and use any of the data structures we learned in class so far.
 */
public class SpellCheck {
	final String path = "dictionary.txt"; // Don't change this path in your final submission.
	HashMap<Integer,TreeMap<Double,Node>> dictionary;
	final ArrayList<Integer> primeNumbers;
	final HashMap<Double,Integer> primeDiffer;

	class Node{
		String word;
		String smallCaseWord;
		final int[] hashCode;
		double hashSum;
		Node next;

		public Node(String word,Node next){//단어 길이만큼
			this.word = word;
			this.next = next;
			smallCaseWord = word.toLowerCase();//얘도 대충 단어 길이만큼 
			hashCode = new int[word.length()];
			for(int i=0;i<word.length();i++){//단어 길이만큼
				hashCode[i]=primeNumbers.get(smallCaseWord.charAt(i) - 'a');//O(1)
			}
			hashSum = hashSum();
		}

		public double hashSum(){
			double hashSum =0;
			for(int i=0;i<hashCode.length;i++){
				hashSum += Math.sqrt(hashCode[i]);
			}
			return hashSum;
		}

		public int hashMultiple(){
			int hashMultiple = 1;
			for(int i=0;i<hashCode.length;i++){
				hashMultiple *= hashCode[i];
			}
			return hashMultiple;
		}

		public int compareForSameHashSum(String word){//단어길이
			String smallCaseWord = word.toLowerCase();//단어길이
			if(this.smallCaseWord.equals(smallCaseWord)) return 0;//단어 길이
			for(int i=0;i<word.length()-1;i++){//단어길이
				if(this.smallCaseWord.charAt(i) != smallCaseWord.charAt(i)){//1
					smallCaseWord = swap(smallCaseWord,i);//단어길이
					if(this.smallCaseWord.equals(smallCaseWord)) return 1;//단어 길이
					break;
				}
			}
			return -1;
		}

		public String swap(String word, int i){//단어길이
			if(i>=word.length()-1) return null;//1
			String newString = "";
			if(i != 0) newString += word.substring(0,i);//단어길이
			newString += Character.toString(word.charAt(i+1)) + Character.toString(word.charAt(i));//1
			if(i+2 != word.length()) newString += word.substring(i+2);//단어길이
			return newString;
		}

		public int indexSum(){//단어길이
			int indexSum = 0;
			for(int i=0;i<word.length();i++){
				indexSum += smallCaseWord.charAt(i) - 'a'+1;
			}
			return indexSum;
		}

		public boolean isReplacement(Node word){//단어길이
			double key = Math.abs(this.hashSum() - word.hashSum());//단어 길이, abs는 O(1)이라 알려짐
			int indexDistance = Math.abs(this.indexSum() - word.indexSum());//최대 25,최소 1,단어길이
			for(int i=0;i<primeNumbers.size()-indexDistance;i++){//대충 상수번 반복
				double criteriaKey = Math.abs(Math.sqrt(primeNumbers.get(i+indexDistance))-Math.sqrt(primeNumbers.get(i)));//1?
				if(compareFloatingValue(criteriaKey, key)){
					for(int j=0;j<word.word.length()-1;j++){
						if(this.smallCaseWord.charAt(j) != word.smallCaseWord.charAt(j))
							return this.smallCaseWord.substring(j+1).equals(word.smallCaseWord.substring(j+1));
					}
					return true;
				}
			}
			return false;
		}

		public boolean compareFloatingValue(double d1,double d2){
			if(Math.abs(d1-d2)<=0.0000001) return true;
			return false;
		}

		public boolean isContains(Node word){
			String largerWord,shorterWord;
			if(this.word.length() > word.word.length()){
				largerWord = this.smallCaseWord;
				shorterWord = word.smallCaseWord;
			}
			else{
				largerWord = word.smallCaseWord;
				shorterWord = this.smallCaseWord;
			}
			for(int i=0;i<shorterWord.length();i++){
				if(largerWord.charAt(i) != shorterWord.charAt(i)){
					return largerWord.substring(i+1).equals(shorterWord.substring(i));
				}
			}
			return true;
		}
	}

	public SpellCheck() {
		primeNumbers = new ArrayList<Integer>();
		initializePrimeNumbers();
		primeDiffer = new HashMap<Double,Integer>();
		initializePrimeDiffer();
		dictionary = new HashMap<Integer,TreeMap<Double,Node>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				// 'line' is a valid word
				if(!dictionary.containsKey(line.length())){
					dictionary.put(line.length(),new TreeMap<Double,Node>());
				}
				TreeMap<Double,Node> lengthTreeMap = dictionary.get(line.length());
				Node newNode = new Node(line,null);
				Entry<Double,Node> compare = lengthTreeMap.ceilingEntry(newNode.hashSum-0.000001);
				if(compare!=null&&Math.abs(compare.getKey()-newNode.hashSum)<0.000001){//lengthTreeMap.containsKey(newNode.hashSum())
					Node nextNode = compare.getValue();
					double previousKey = compare.getKey();
					newNode.next = nextNode;
					lengthTreeMap.put(previousKey,newNode);
				}
				else lengthTreeMap.put(newNode.hashSum, newNode);
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}		
	}


	public void initializePrimeNumbers(){

		primeNumbers.add(2);
		primeNumbers.add(3);
		primeNumbers.add(5);
		primeNumbers.add(7);
		primeNumbers.add(11);
		primeNumbers.add(13);
		primeNumbers.add(17);
		primeNumbers.add(19);
		primeNumbers.add(23);
		primeNumbers.add(29);
		primeNumbers.add(31);
		primeNumbers.add(37);
		primeNumbers.add(41);
		primeNumbers.add(43);
		primeNumbers.add(47);
		primeNumbers.add(53);
		primeNumbers.add(59);
		primeNumbers.add(61);
		primeNumbers.add(67);
		primeNumbers.add(71);
		primeNumbers.add(73);
		primeNumbers.add(79);
		primeNumbers.add(83);
		primeNumbers.add(89);
		primeNumbers.add(97);
		primeNumbers.add(101);
	}
	
	public void initializePrimeDiffer(){
		primeDiffer.put(0.0,primeNumbers.get(0));
		for(int i=1;i<primeNumbers.size();i++){
			double key = (Math.sqrt(primeNumbers.get(i))-Math.sqrt(primeNumbers.get(i-1)));
			primeDiffer.put(key, primeNumbers.get(i));
		}
	}
	/*
	 * The length of the return array should be the same as the number of words in 'sentence'.
	 * The i-th element of the return array is the substitute candidate for the i-th word in the sentence.
	 * If the i-th word is a valid word (i.e., not a typo), then the array should be empty.
	 */

	 /*
	  * Algorithm:
	  *
	  * The constructor for this class stores words in the dictionary in HashMap. The key is the length of the word and the value is TreeMap.
	  * The treeMap's key is the "hashSum" of the word and the value is a Node that has word as a data.
	  * The Node has a pointer that points other node. If there is an anagram, the anagram node gets connected with preexisting node. 
	  * 
	  * The basic logic of the class Node is that the class represents each word with prime numbers.
	  * I chose prime numbers to represent a word for two reasons.
	  * First, the multiple of prime numbers is unique. 16 is 4*4 or 8*2. However, 26 is 13*2.
	  * Second, for prime numbers between 2 to 101, the difference between two adjacent square root prime numbers are unique.
	  * sqrt(m)-sqrt(n) are unique for every (m,n) that m is not n, m and n are prime numbers, and 2<= m <= 101 and 2<= n <= 101.
	  * For these reasons, 'a' is represented as 2, 'b' is represented as 3, 'c' is represented as 5, ..., and 'z' is represented as 101.
	  * I call these "representations" as "hashes".
	  * Thus, hashMultiple() returns the multiple of each character's hash in a word.
	  * hashSum() returns the sum of each character's square root hash in a word. 
	  *
	  * In the perspective of word's length, the candidates for typos has two types.
	  *
	  * First, the candidates' length and typos' length is the same.
	  * For this case, there are also three different cases.
	  * First, the typo is valid(I know the word is not "typo" in this case, but for convenience I'll use it.).
	  * Second, the typo is replacement of a single character.
	  * Third, the typo is swapped.
	  * For the first and third cases, the candidates and typos has the same hashSum.
	  * For the potential candidate Node-obtained by TreeNode's get method-the compareForSameHashSum method compares the Node and typo by each characters.
	  * When the typo is a valid word, you make the ArrayList variable to null and moves to next word.
	  * When the typo is swapped, you append the candidate to the ArrayList.
	  * After the comparison is done, the Node moves to next Node because of anagrams.
	  * For the second case, the spellCheck method searches candidates for specific hashSum range.
	  * The maximum hashSum value is when typo is "[][][]a" and candidate is "[][][]z". So, the maximum range is typo's hashSum + ('z'-'a').
	  * The minimum is when typo is "[][][]z" and candidate is "[][][]a". So, the minimum range is typo's hashSum - ('z'-'a').
	  * For the potential candidate Node, the isReplacement method compares the Node and typo by each characters.
	  * Then, the Node move to the next anagram Node.
	  *
	  * Second, the candidates' length and typos' length are one more of less than each other.
	  * Regardless the candidates' length is longer or shorter, the spellCheck method's algorithm is the same.
	  * You get the candidate Node with hashSum range hashSum to hashSum + ('z'-'a') or hashSum-('z'-'a') to hashSum.
	  * Then, for each anagram Nodes, the isContains method compares the Node and typo by each characters.
	  *
	  * After repeating above operations for every words, you returns the ArrayList array.
	  *
	  * Time Complexity:
	  *
	  * Everything that is not mentioned below is just an assignment or a comparison.
	  *
	  * The split method iterates from the first index of the input string to the last index, so the time complexity is O(n), where n is the length of the input string.
	  *
	  * The for loop iterates from the first index of the words array to the last index. 
	  * The maximum length of the array is when the sentence is constituted with one character words and space.
	  * That is, the maximum length of the array is the same as input string's length.
	  * Thus, the loop repeats for input string's length times.
	  *
	  * The constructor for Node class has for loop that iterates for words[i]'s length times.
	  * Thus, the constructor's time complexity is O(m), where m is the length of the input word.
	  *
	  * For HashMap's get method, the map can access directly to its array with hash algorithm. Thus, its time complexity is O(1).
	  *
	  * For TreeMap's ceilingEntry and higherEntry method, they both search the targeted entry and returns it.
	  * Since the data structure is red-black tree and the height of the tree is upper bounded by log, their time complexities are O(logk).
	  * k is the number of words in the dictionary. More precisely, the hight follows the number of same length words in the dictionary.
	  * However, that number in upper bounded by the number of words in the dictionary, so k is the number of word in the dictionary.
	  *
	  * For Node class's hashSum() and hashMultiple(), they both iterates from the first index of input word to the last index.
	  * Thus, their time complexities are O(m), where m is the length of the input word.
	  *
	  * For Entry class's getKey() and getValue(), they both simply returns its field's data. Thus, their time complexities are O(1).
	  *
	  * In this method, there are two kinds of while loop.
	  *
      * The first while loop iterates for 10.0499 range. This iteration is upper bounded by 26^100. Thus, the repeat itself's time complexity is O(1).
	  *
	  * The second while loop iterates for the number of anagrams. Since the number of anagrams is upper bounded by the number of words in dictionary,
	  * the repeat itself's time complexity is O(k). k is the number of words in dictionary.
	  *
	  * primeNumber array's contains method iterates from the first index of the array to the last index.
	  * However, the array's length is fixed to 26. Thus, the contains method's time complexity is O(1).
	  *
	  * Node class's isContains(), compareForSameHashSum(), and isReplacement() methods' time complexities are O(m), where m is the length of the input word.
	  * They iterates from the first index of the input word to the last index.
	  *
	  * For valid and swapped cases, the overall while loop's time complexity is O(m+logk+km)=O(logk+km). km is because of while loop and compareForSameHashSum(). They're nested.
	  * For replace case, the overall while loop's time complexity is O(m+km+logk)=O(logk+km). km is because of while loop and isReplacement(). They're nested.
	  * For addition and removal cases, the overall time complexity is O(m+logk+km)=O(logk+km). km is because of while loop and isContains(). They're nested.
	  * Thus, the overall time complexity of for loop is O(n*(logk+km)).
	  * Therefore, the overall time complexity of spellCheck method is O(n+n*(logk+km))=O(n*(logk+km)).
	  * n is the length of the input string, m is the length of input word, and k is the number of words in dictionary.
	  */
	public ArrayList<String>[] spellCheck(String sentence) {//valid는 아닌데 후보는 없을경우? 그럼 아무것도 못 넣는거니 []이게 나와야할듯.
		String[] words = sentence.split("\\s"); // 'words' is the list of words in 'sentence' 기본적으로 전체 따라가는 느낌이라 O(n)인듯.
		ArrayList<String>[] returnList = new ArrayList[words.length];
		final double maxDiffer = Math.sqrt(101)-Math.sqrt(2);
		for(int i=0;i<words.length;i++){//단어 개수만큼 반복
			returnList[i] = new ArrayList<String>();
			Node word = new Node(words[i], null);//단어 길이만큼
			TreeMap<Double,Node> sameLength = dictionary.get(words[i].length());//O(1)
			if(sameLength == null){//이 경우 쁠마 중 하나에 있다는 뜻.
				//자릿수 다름
				//빼기
				TreeMap<Double,Node> shortLength = dictionary.get(words[i].length()-1);//O(1)
				Entry<Double,Node> shortEntry = shortLength.ceilingEntry(word.hashSum-maxDiffer);//엔트리는 로그.
				while(shortEntry != null && shortEntry.getKey()<= word.hashSum+0.000001){//이거 반복은 범위에 있는 수만큼(26의 100승까지)->1.
					Node currentNode = shortEntry.getValue();
					while(currentNode != null){//아나그램 수만큼
						if(currentNode.isContains(word)) returnList[i].add(currentNode.word);//짧은거 길이만큼 반복
						currentNode=currentNode.next;
					}
					shortEntry=shortLength.higherEntry(shortEntry.getKey());//로그
				}
				//더하기
				TreeMap<Double,Node> longLength = dictionary.get(words[i].length()+1);//1
				Entry<Double,Node> longEntry = longLength.ceilingEntry(word.hashSum-0.000001);//로그
				while(longEntry != null && longEntry.getKey()<=word.hashSum+maxDiffer){//1
					Node currentNode = longEntry.getValue();
					while(currentNode!=null){//아나그램 수
						if(currentNode.isContains(word)) returnList[i].add(currentNode.word);//짧은거 길이
						currentNode=currentNode.next;
					}
					longEntry=longLength.higherEntry(longEntry.getKey());//로그
				}
				continue;
			}

			//자릿수 같음
			Entry<Double,Node> sameHashEntry = sameLength.ceilingEntry(word.hashSum-0.000001);//로그
			Node sameHashSum = sameHashEntry.getValue();
			while(sameHashSum != null){//아나그램
				int whichCase = sameHashSum.compareForSameHashSum(words[i]);//단어길이
				switch(whichCase){
					case 0:			//아예 같음->sum 같음
						returnList[i] = null;
						break;
					case 1:			//뒤집힘->sum 같음
						returnList[i].add(sameHashSum.word);
						break;
				}
				if(returnList[i] == null) break;
				sameHashSum = sameHashSum.next;
			}
			if(returnList[i] == null) continue;
			//하나만 틀림->sum 다름

			Entry<Double,Node> entry = sameLength.ceilingEntry(word.hashSum-maxDiffer);//로그
			while(entry != null && entry.getKey()<=word.hashSum+maxDiffer){//1
				Node currentNode = entry.getValue();
				while(currentNode != null){//아나그램
					if(currentNode.isReplacement(word)) returnList[i].add(currentNode.word);//단어길이
					currentNode=currentNode.next;
				}
				entry = sameLength.higherEntry(entry.getKey());//로그
			}
			//자릿수 다름
			Node test = new Node("deviation",null);
			//빼기
			TreeMap<Double,Node> shortLength = dictionary.get(words[i].length()-1);//1
			Entry<Double,Node> shortEntry = shortLength.ceilingEntry(word.hashSum-maxDiffer);//로그
			while(shortEntry != null && shortEntry.getKey()<= word.hashSum+0.000001){//1
				Node currentNode = shortEntry.getValue();
				while(currentNode != null){//아나그램
					if(currentNode.isContains(word)) returnList[i].add(currentNode.word);//짧은거길이
					currentNode=currentNode.next;
				}
				shortEntry=shortLength.higherEntry(shortEntry.getKey());//로그
			}
			//더하기
			TreeMap<Double,Node> longLength = dictionary.get(words[i].length()+1);//1
			Entry<Double,Node> longEntry = longLength.ceilingEntry(word.hashSum-0.000001);//로그
			while(longEntry != null && longEntry.getKey()<=word.hashSum+maxDiffer){//1
				Node currentNode = longEntry.getValue();
				while(currentNode!=null){//아나그램
					if(currentNode.isContains(word)) returnList[i].add(currentNode.word);//짧은거길이
					currentNode=currentNode.next;
				}
				longEntry=longLength.higherEntry(longEntry.getKey());//로그
			}

		}
		
		return returnList;
	}

	
	public static void main(String[] args) {
		SpellCheck sc = new SpellCheck();
		String[] sentences = {"I ate an x", "paint the banel", "shee is a riend", "kangaru"};
		// Feel free to change the following printout routine
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
