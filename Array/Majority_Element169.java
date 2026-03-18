
class Solution {

    public int majorityElement(int[] nums) {
        int n = nums[0];
        int count = 1;

        if (int n == nums[i])
        {
            count++;
        }
        else
        {
            count--;
        }

        if (count == 0) {
            n = nums[i];
            count = 1;
        }
    }
};
