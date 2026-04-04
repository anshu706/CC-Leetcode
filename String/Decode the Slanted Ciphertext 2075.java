class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();

        // Edge case: single row means no transformation
        if (rows == 1) return encodedText;

        int cols = n / rows;
        StringBuilder sb = new StringBuilder();

        // Each diagonal starts at column c (row 0)
        for (int c = 0; c < cols; c++) {
            // Walk down the diagonal: (row, c + row)
            for (int row = 0; row < rows; row++) {
                int col = c + row;
                // col must be within bounds
                if (col >= cols) break;
                sb.append(encodedText.charAt(row * cols + col));
            }
        }

        // Remove trailing spaces (originalText has no trailing spaces)
        int end = sb.length();
        while (end > 0 && sb.charAt(end - 1) == ' ') {
            end--;
        }

        return sb.substring(0, end);
    }
} 
