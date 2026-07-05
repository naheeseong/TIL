
/**
 * 문제 링크: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 * LeetCode 167. Two Sum II - Input Array Is Sorted
 * 유형: 투 포인터 (Two Pointers)
 */
public class LT_TwoSum2_Medium {

    // 릿코드 핵심 풀이 메서드
    public int[] solveTwoSum(int[] numbers, int target) {
        int start_idx = 0;
        int end_idx = numbers.length - 1;
        int[] answer = new int[2];

        // 두 포인터가 겹치기 전까지 좁혀가며 탐색
        while (start_idx < end_idx) {
            int sum = numbers[start_idx] + numbers[end_idx];

            if (sum == target) {
                answer[0] = start_idx + 1; // 1-indexed 반영
                answer[1] = end_idx + 1;
                break;
            }
            else if (sum > target) {
                end_idx--; // 합이 크면 오른쪽 끝 포인터를 왼쪽으로
            }
            else {
                start_idx++; // 합이 작으면 왼쪽 끝 포인터를 오른쪽으로
            }
        }

        return answer;
    }

    // 인텔리제이 테스트용 실행 메서드
    public static void main(String[] args) {
        LT_TwoSum2_Medium test = new LT_TwoSum2_Medium();
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        int[] result = test.solveTwoSum(numbers, target);
        System.out.println("결과 인덱스: [" + result[0] + ", " + result[1] + "]"); // 출력: [1, 2]
    }
}