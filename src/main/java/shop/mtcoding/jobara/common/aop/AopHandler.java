package shop.mtcoding.jobara.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import shop.mtcoding.jobara.common.util.RedisService;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.user.vo.UserVo;

@Aspect
@Component
public class AopHandler {
    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(shop.mtcoding.jobara.common.aop.CompanyCheck)")
    public void CompanyCheck() {
    }

    @Before("CompanyCheck()")
    public void CompanyCheck(JoinPoint joinPoint) {
        UserVo principal = redisService.getValue("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다", HttpStatus.UNAUTHORIZED, "/#login");
        Verify.checkRole(principal, "company");
    }

    @Pointcut("@annotation(shop.mtcoding.jobara.common.aop.CompanyCheckApi)")
    public void CompanyCheckApi() {
    }

    @Before("CompanyCheckApi()")
    public void CompanyCheckApi(JoinPoint joinPoint) {
        UserVo principal = redisService.getValue("principal");
        Verify.validateApiObject(principal, "로그인이 필요한 기능입니다", HttpStatus.UNAUTHORIZED);
        Verify.checkRoleApi(principal, "company");
    }

    @Pointcut("@annotation(shop.mtcoding.jobara.common.aop.EmployeeCheck)")
    public void EmployeeCheck() {
    }

    @Before("EmployeeCheck()")
    public void EmployeeCheck(JoinPoint joinPoint) {
        UserVo principal = redisService.getValue("principal");
        Verify.validateObject(principal, "로그인이 필요한 기능입니다", HttpStatus.UNAUTHORIZED, "/#login");
        Verify.checkRole(principal, "employee");
    }

    @Pointcut("@annotation(shop.mtcoding.jobara.common.aop.EmployeeCheckApi)")
    public void EmployeeCheckApi() {
    }

    @Before("EmployeeCheckApi()")
    public void EmployeeCheckApi(JoinPoint joinPoint) {
        UserVo principal = redisService.getValue("principal");
        Verify.validateApiObject(principal, "로그인이 필요한 기능입니다", HttpStatus.UNAUTHORIZED);
        Verify.checkRoleApi(principal, "employee");
    }
}
