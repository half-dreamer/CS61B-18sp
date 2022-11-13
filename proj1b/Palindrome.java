public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for (int i=0; i<word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }
    public boolean isPalindrome(String word){
        Deque<Character>wordDeque = wordToDeque(word);
        return  isPalindromeHelper(wordDeque);
    }
    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size()<=1) {
            return  true;
        } else {
            if (wordDeque.removeLast() == wordDeque.removeFirst()) {
                return isPalindromeHelper(wordDeque);
            } else {
                return false;
            }
        }
    }
    public boolean isPalindrome(String word,CharacterComparator cc) {
        for (int i=0;i<=word.length()/2-1;i++) {
            if (cc.equalChars(word.charAt(i), word.charAt(word.length()-i-1)) == false) {
                return false;
            }
        }
        return true;
    }
}
    // another solution
    /**    if (word.length()<=1) {
            return true;
        } else {
            for (int i=0;i<=word.length()/2;i++) {
                if (word.charAt(i)!=word.charAt(word.length()-i-1)) {
                    return  false;
                }
            }
            return  true;
        }
    } */
