package cn.sunxyz;

import cn.sunxyz.domain.UserInfo;
import cn.sunxyz.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangrd on 2017/5/8.
 */
@Component
public class DataInit implements CommandLineRunner {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public void run(String... strings) throws Exception {
        List<UserInfo> userInfos = new ArrayList<>();
        for(int i=0;i<100;i++){
            userInfos.add(new UserInfo("name"+i,20,i%2==0?"MAN":"WOMAN","济南"));
        }
        userInfoRepository.save(userInfos);
    }
}
