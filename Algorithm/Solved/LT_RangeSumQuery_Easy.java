// 문제 링크: https://leetcode.com/problems/range-sum-query-immutable/

/*
   - 리트코드 첫 문제 솔브! 백준과 달리 입출력(BufferedReader)을 직접 짤 필요가 없음.
   - prefixSum 배열의 크기를 N+1로 안 잡고 N으로 잡으면, sumRange에서 left-1 할 때 left=0인 경우 마이너스 인덱스 에러(ArrayIndexOutOfBoundsException)가 발생함.
   - 무조건 누적 합은 [0번 인덱스 = 0]으로 비워두고, 1번부터 차례대로 쌓는 게 좋을 것 같음.
*/

// 리트코드에 제출해서 통과한 클래스
class NumArray {
    private int[] prefixSum;

    public NumArray(int[] nums) {
        int n = nums.length;
        prefixSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}

// 인텔리제이에서 실행하기 위한 메인 클래스
public class LT_RangeSumQuery_Easy {
    public static void main(String[] args) {

        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);

        System.out.println(obj.sumRange(0, 2)); // 출력: 1
        System.out.println(obj.sumRange(2, 5)); // 출력: -1
        System.out.println(obj.sumRange(0, 5)); // 출력: -3
    }
}