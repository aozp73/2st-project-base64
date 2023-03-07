package shop.mtcoding.jobara.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class RedisServiceSet {

    @Autowired
    private RedisService redisService;
    
    public void addModel(Model model){
        model.addAttribute("redisService", redisService);
    }
}
