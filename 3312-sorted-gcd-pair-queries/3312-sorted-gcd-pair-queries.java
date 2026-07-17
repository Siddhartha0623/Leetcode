class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max=0;
        for(int num : nums)
        max=Math.max(max,num);
        int[]freq=new int [max+1];
        for(int num:nums)
        freq[num]++;
        long []divisibleCount=new long[max+1];
        for(int i=1;i<=max;i++){
            for(int multiple = i;multiple<=max;multiple+=i){
                divisibleCount[i]+=freq[multiple];
            }
        }
        long []exactPair=new long[max+1];
        for(int i=max;i>=1;i--){
            long cnt=divisibleCount[i];
    exactPair[i]=cnt * (cnt-1)/2;
            for(int j=i*2;j<=max;j+=i){
                exactPair[i] -= exactPair[j];
            }
        }
        long[]prefix=new long[max+1];
        for(int i=1;i<=max;i++){
            prefix[i]=prefix[i-1]+exactPair[i];
        }
        int[]ans=new int[queries.length];
        for(int i=0;i<queries.length;i++){
            long target = queries[i] + 1;
            int low=1,high=max;
            while(low<high){
                int mid = low +(high-low)/2;
                if(prefix[mid] >= target)
                high= mid;
                else
                low= mid+1;
            }
            ans[i]=low;
        }
        return ans;
    }
}