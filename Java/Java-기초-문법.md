# Java 기초 문법 정리

2026-07-02
### 1. Scanner - nextInt() vs nextLine()
* 문제: `nextInt()` 후 `nextLine()`을 쓰면 버퍼에 엔터(`\n`)가 남아 다음 입력 무시됨.
* 해결법: `sc.nextInt();` 바로 아래에 공백 `sc.nextLine();`을 한 줄 추가해서 버퍼를 비워주기, sc.next(); 사용
.
### 2. toCharArray() 문자열 변환
* 개념: String을 한 글자씩 쪼개서 `char[]` 배열로 만들어 줌.
* 장점: 문자열의 길~~~~이에 맞춰 크기가 자동으로 설정됨.

### 3. BufferedReader.readLine()과 빨간 줄 에러 (`IOException`)

문제 상황: int N = Integer.parseInt(br.readLine());` 코드를 작성했을 때, `readLine()` 부분에 컴파일 에러(빨간 줄)가 발생함.

원인 분석
* `BufferedReader.readLine()`은 외부 장치(키보드, 파일 등)로부터 데이터를 읽어오는 메서드다.
* 자바에서는 이 과정에서 발생할 수 있는 예측 불가능한 입출력 예외(`IOException`)를 컴파일 시점에 체크하는 Checked Exception으로 분류한다.
* 따라서 이에 대한 예외 처리 코드가 없으면 컴파일러가 아예 실행을 막아버린다.

해결 방법
메서드 선언부 옆에 `throws IOException`을 추가하여, 예외가 발생했을 때의 책임을 상위 개념(JVM)으로 위임하여 해결한다.

```java
import java.io.BufferedReader;
import java.io.IOException; // 1. 임포트 추가
import java.io.InputStreamReader;

public class Main {
    // 2. 메서드 시그니처에 throws IOException 추가
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 
    }
}
```

### 4. StringTokenizer
토큰(Token): 문법적으로 더 이상 쪼갤 수 없는 가장 작은 단위의 문자열 조각.

StringTokenizer: 문자열을 지정한 구분자(기본값은 공백) 기준으로 잘게 잘라주는 클래스다.

내부적으로 토큰 주머니를 만들어 관리하며, 대표적인 리모컨 메서드는 다음과 같다:

* hasMoreTokens(): 꺼낼 토큰이 아직 주머니에 남아있는지 여부 반환 (true/false)

* nextToken(): 다음 토큰 조각을 하나씩 꺼내옴 (꺼낸 토큰은 주머니에서 소멸)

* countTokens(): 현재 주머니에 남아있는 토큰의 총 개수 반환

* String.split()과의 차이점: split()은 정규표현식을 기반으로 작동하여 다방면으로 유연하지만, 내부 연산이 복잡해 메모리를 더 먹고 상대적으로 느리다.

StringTokenizer는 문자 자체만을 기준으로 단순하게 쪼개기 때문에 기능은 제한적이지만 속도가 압도적으로 빠르다. 따라서 코딩 테스트 등 최적화가 중요한 상황에서는 StringTokenizer를 사용하는 것이 권장된다.