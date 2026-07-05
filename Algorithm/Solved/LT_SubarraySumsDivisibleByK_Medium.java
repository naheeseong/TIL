
/**
 * LeetCode 974. Subarray Sums Divisible by K
 * 유형: 구간 합(Prefix Sum) + 나머지 연산(Modulo)
 */
public class LT_SubarraySumsDivisibleByK_Medium {

    // 1. 릿코드에 제출할 핵심 풀이 메서드 (static을 빼고 작성)
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

    // 2. 인텔리제이 테스트 실행용 메인 메서드
    public static void main(String[] args) {
        // 내 자신을 인스턴스화해서 호출
        LT_SubarraySumsDivisibleByK_Medium tester = new LT_SubarraySumsDivisibleByK_Medium();

        int[] nums1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println(tester.subarraysDivByK(nums1, k1)); // 출력: 7
    }
}