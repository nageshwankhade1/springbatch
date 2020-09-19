package com.nmw.example.springbatch.batch;

import com.nmw.example.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserProcessor implements ItemProcessor<User, User> {

    private static final Map<String, String> DEPT_NAMES =
            new HashMap<>();

    public UserProcessor() {
        DEPT_NAMES.put("001", "SE");
        DEPT_NAMES.put("002", "CM");
        DEPT_NAMES.put("003", "RM");
        DEPT_NAMES.put("004", "PS");
        DEPT_NAMES.put("005", "RDM");
    }


    @Override
    public User process(User user) throws Exception {
        String deptCode = user.getDept();
        String dept = DEPT_NAMES.get(deptCode);

        user.setDept(dept);

        System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
        return user;
    }
}
