package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 그냥 @Controller 사용 시엔 논리 뷰 string을 반환하지만
 * RestController 사용하게 되면 그냥 문자열 ok를 반환
 */
@Slf4j
@RestController
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        /**
         * log.trace(" trace log= " + name); +로 사용 하면 안 되는 이유
         * 현재 로그 레벨은 debug 상태 출력시 나오지 그래서 trace는 출력 되지 않음
         * 그럼에도 불구하고, +를 사용하여 연산이 일어남
         * 연산이 일어나도 그 뒤에 trace 파라미터 넘길려니 확인 후 안 넘김
         * 연산은 곧 리소스 사용 그래서 리소스 낭비가 일어난다.
         *
         * log.trace(" trace log={}", name); 경우
         * trace에 파라미터를 넘기는 형식이라
         * 실행 시 trace 메소드를 보고 로그 중지
         * 리소스 낭비 될 일이 없다.
         */
        log.trace(" trace log= " + name);
        log.trace(" trace log={}", name);

        log.debug(" debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);


        return "ok";
    }

}
