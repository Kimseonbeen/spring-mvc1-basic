package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        /**
         *  그냥 @Controller에 반환타입이 String은 리턴값이 논리뷰 스트링 값을 찾는데
         * @ResponseBody 하면 http응답에 스트링 그냥 반환
         */
        return "ok";


    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        /**
         *  그냥 @Controller에 반환타입이 String은 리턴값이 논리뷰 스트링 값을 찾는데
         * @ResponseBody 하면 http응답에 스트링 그냥 반환
         */
        return "ok";


    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    // 'String, int, Integer' 등의 단순 타입아면 '@RequestParam'도 생략 가능
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        /**
         *  그냥 @Controller에 반환타입이 String은 리턴값이 논리뷰 스트링 값을 찾는데
         * @ResponseBody 하면 http응답에 스트링 그냥 반환
         */
        return "ok";


    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);

        return "ok";


    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);

        return "ok";


    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";


    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    /**
     * 스프링 MVC는 @ModelAttribute가 있으면 다음을 실행한다.
     * HelloData 객체를 생성한다.
     * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의
     * 값을 입력(바인딩)한다.
     * 예) 파라미터 이름이 username이면 'setUseraname() 메서드를 찾아서 호출하면서 값을 입력한다.
     *
     * 프로퍼티 란 ?
     * 객체에 getUsername(), setUsername() 메서드가 있으면, 이 객체는 username이라는 프로퍼티를 가지고 있다.
     * username 프로퍼티의 값을 변경하면 setUsername()이 호출되고, 조회하면 getUsername이 호출된다.
     */
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";

    }
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    /**
     * 스프링은 해당 생략시 다음과 규칙을 적용한다.
     * 'String, int, Integer'과 같은 단순 타입 = @RequestParam
     * 나머지 = @ModelAttribute 인식하여 생략가능..
     */
    public String modelAttributeV2(HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";

    }


}
