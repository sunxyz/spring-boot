package cn.sunxyz.controller;

import cn.sunxyz.domain.UserInfo;
import cn.sunxyz.repository.UserInfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangrd on 2017/5/8.
 */
@Api(value = "用户信息查询", description = "用户管理操作API",tags = "UserApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @ApiOperation(value = "get", notes = "根据id查询用户")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUser(@PathVariable("id") UserInfo userInfo) {
        return ResponseEntity.ok(userInfo);
    }


    @ApiOperation(value = "add", notes = "新增用户")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> addUser(UserInfo userInfo) {
        userInfo = userInfoRepository.save(userInfo);
        return ResponseEntity.ok(userInfo);
    }

}
