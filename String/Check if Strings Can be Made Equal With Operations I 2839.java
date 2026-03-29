class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Even indices (0,2) can swap freely
        // Odd indices (1,3) can swap freely
        // Check if each group has the same multiset of characters

        // Check even positions: 0 and 2
        boolean evenMatch = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                            (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));

        // Check odd positions: 1 and 3
        boolean oddMatch = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                           (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));

        return evenMatch && oddMatch;
    }
} 
