public class Roman_to_Integer13 {
    static int getValue(char cc) {
        if (cc == 'I')
            return 1;
        if (cc == 'V')
            return 5;
        if (cc == 'X')
            return 10;
        if (cc == 'L')
            return 50;
        if (cc == 'C')
            return 100;
        if (cc == 'D')
            return 500;
        if (cc == 'M')
            return 1000;
        return 0;
    }

    public static int romanToInt(String s) {

        int total = 0;

        for (int i = 0; i < s.length() - 1; i++) 
        {
            char cc = s.charAt(i);
            char nc = s.charAt(i + 1);

            if ((cc == 'I' && nc == 'V') || (cc == 'I' && nc == 'X'))
            {
                total -= 1;
            } 
            else if ((cc == 'X' && nc == 'L') || (cc == 'X' && nc == 'C')) 
            {
                total -= 10;
            } 
            else if ((cc == 'C' && nc == 'D') || (cc == 'C' && nc == 'M')) 
            {
                total -= 100;
            } 
            else 
            {
                total += getValue(cc);
            }
        }

        total += getValue(s.charAt(s.length() - 1));
        return total;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String roman = sc.nextLine().toUpperCase();
        System.out.println(romanToInt(roman));
    }
}
