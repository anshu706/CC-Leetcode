class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;

        int[] hash = new int[n+1];

        for (int i=0; i<n; i++) {
            hash[nums[i]]++;

            if(hash[nums[i]] == 2) return nums[i];
        }
        return -1;
    }
}
