class Fancy {
    private static final long MOD = 1_000_000_007L;
    private List<Long> seq = new ArrayList<>();
    private long m = 1, a = 0; // current global: value = stored * m + a

    private long power(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    private long modInverse(long x) {
        return power(x, MOD - 2, MOD); // Fermat's little theorem
    }

    public Fancy() {}

    public void append(int val) {
        // Store normalized: we want stored * m + a = val
        // => stored = (val - a) * m^(-1)
        long stored = (val - a % MOD + MOD) % MOD * modInverse(m) % MOD;
        seq.add(stored);
    }

    public void addAll(int inc) {
        a = (a + inc) % MOD;
    }

    public void multAll(int mult) {
        m = m * mult % MOD;
        a = a * mult % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= seq.size()) return -1;
        return (int)((seq.get(idx) * m % MOD + a) % MOD);
    }
} 
