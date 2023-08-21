package com.tsrs.webedi.modular.system.factory;

import com.tsrs.webedi.modular.system.transfer.UserDto;
import com.tsrs.webedi.common.persistence.model.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}
