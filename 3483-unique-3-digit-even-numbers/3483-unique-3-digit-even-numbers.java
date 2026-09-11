class Solution {
    public int totalNumbers(int[] digits) {
        // Count frequency of each digit
        int[] freq = new int[10];

        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // Check every 3-digit number
        for (int num = 100; num <= 999; num++) {

            // Last digit must be even
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;          // hundreds
            int b = (num / 10) % 10;   // tens
            int c = num % 10;          // ones

            // Temporarily use the digits
            freq[a]--;
            freq[b]--;
            freq[c]--;

            // If all frequencies are >= 0,
            // the number can be formed
            if (freq[a] >= 0 && freq[b] >= 0 && freq[c] >= 0) {
                count++;
            }

            // Restore frequencies
            freq[a]++;
            freq[b]++;
            freq[c]++;
        }

        return count;
        
    }
}