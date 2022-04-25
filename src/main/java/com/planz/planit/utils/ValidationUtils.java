package com.planz.planit.utils;

import com.planz.planit.config.BaseException;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.planz.planit.config.BaseResponseStatus.*;
import static com.planz.planit.config.BaseResponseStatus.INVALID_ACCESS_TOKEN;

@Log4j2
public class ValidationUtils {

    // 생각해보니 Spring Security Filter에서 이 검사를 모두 해주기 때문에 은지 너가 이 메소드를 호출할 필요가 없다는 걸 깨달았단다! ㅎ하하
    // header에서 userId를 뽑아, 유효한 userId인지 검사
    public static void checkUserIdInHeader(String userId) throws BaseException {

        if (userId == null) {
            log.error("userId를 헤더에 입력해주세요");
            throw new BaseException(NOT_EXIST_USER_ID_IN_HEADER);
        }

        try {
            Long.valueOf(userId);
        } catch (NumberFormatException e) {
            log.error("유효하지 않은 userId입니다. 숫자형태로 입력해주세요.");
            throw new BaseException(INVALID_USER_ID);
        }
    }

}
