// 문제 링크: https://leetcode.com/problems/subarray-sums-divisible-by-k/

/*
   - 나머지 합(Modulo Sum) 알고리즘으로 풀이한 문제.
   - 핵심: 같은 나머지를 가진 두 누적합 위치 사이의 구간 합이 k의 배수라는 성질 이용.
   - S[i] % k == S[j] % k이면 (S[i] - S[j]) % k = 0 → j+1부터 i까지의 구간이 k의 배수.
   - 누적합을 저장하면서 각 나머지를 카운팅하고, 같은 나머지의 조합 nC2 = n*(n-1)/2로 경우의 수 계산.
   - 주의: 자바에서 음수 % 연산은 음수를 반환하므로, tmp < 0이면 tmp += k로 양수로 변환해야 함.
   - 직접 0으로 나누어떨어지는 경우(tmp == 0)는 먼저 카운트하고, 나머지별 조합은 따로 계산.
*/

// 리트코드에 제출해서 통과한 클래스
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int N = nums.length;
        int[] D = new int[N];      // 누적합 배열
        int[] C = new int[k];      // 나머지별 개수 카운팅 배열

        // 1단계: 누적합 배열 구축
        D[0] = nums[0];
        for (int i = 1; i < N; i++){
            D[i] = D[i-1] + nums[i];
        }

        int answer = 0;

        // 2단계: 나머지 카운팅 및 직접 카운트
        for(int i = 0; i < N; i++){
            int tmp = D[i] % k;

            // 음수 나머지 처리 (자바의 음수 % 연산 대응)
            if (tmp < 0){
                tmp += k;
            }

            // 0으로 나누어떨어지는 경우 직접 카운트
            if (tmp == 0){
                answer++;
            }

            // 나머지별 개수 저장
            C[tmp]++;
        }

        // 3단계: 같은 나머지를 가진 쌍의 조합 계산
        for (int i = 0; i < k; i++){
            answer += (C[i] * (C[i] - 1)) / 2;
        }

        return answer;
    }
}

// 인텔리제이에서 실행하기 위한 메인 클래스
public class LT_SubarraySumsDivisibleByK_Medium {
    public static void main(String[] args) {

        Solution sol = new Solution();

        // 테스트 케이스 1
        int[] nums1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println(sol.subarraysDivByK(nums1, k1)); // 출력: 7

        // 테스트 케이스 2
        int[] nums2 = {5};
        int k2 = 9;
        System.out.println(sol.subarraysDivByK(nums2, k2)); // 출력: 0

        // 추가 테스트 케이스 (음수 포함)
        int[] nums3 = {-1, 2, 9};
        int k3 = 2;
        System.out.println(sol.subarraysDivByK(nums3, k3)); // 출력: 2
    }
}

/**
 * 문제 해석:
 * nums 배열에서 합이 k로 나누어떨어지는 부분 배열의 개수를 구하시오.
 * 부분 배열은 연속된 원소로 이루어져야 함.
 */