package shop.mtcoding.jobara.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.jobara.user.vo.UserVo;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper om;

    public void setValue(String key, Object value) {
        String principalJson = null;
        try {
            principalJson = om.writeValueAsString(value);
        } catch (Exception e) {
            System.out.println("파싱 오류");
        }
        redisTemplate.opsForValue().set(key, principalJson);
    }

    public UserVo getValue(String key) {
        UserVo principal = null;
        try {
            String principalJson = redisTemplate.opsForValue().get("principal");
            principal = om.readValue(principalJson, UserVo.class);
        } catch (Exception e) {
            System.out.println("파싱 실패");
        }

        return principal;
    }

    public void logout(String sessionId) {
        redisTemplate.delete(sessionId);
        UserVo UserVo = new UserVo();
        try {
            String principalJson = om.writeValueAsString(UserVo);
            setValue("principal", principalJson);
        } catch (Exception e) {
            System.out.println("파싱 오류");
        }
    }
}