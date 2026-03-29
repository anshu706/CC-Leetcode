class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            // Check s → t mapping
            if (sToT.containsKey(sc)) {
                if (sToT.get(sc) != tc) return false;  // conflict
            } else {
                sToT.put(sc, tc);
            }

            // Check t → s mapping (ensures bijection)
            if (tToS.containsKey(tc)) {
                if (tToS.get(tc) != sc) return false;  // conflict
            } else {
                tToS.put(tc, sc);
            }
        }

        return true;
    }
}