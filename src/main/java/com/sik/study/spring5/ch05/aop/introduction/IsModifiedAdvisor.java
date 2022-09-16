package com.sik.study.spring5.ch05.aop.introduction;

import org.aopalliance.aop.Advice;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
    public IsModifiedAdvisor(Advice advice) {
        super(advice);
    }

    public IsModifiedAdvisor() {
        super(new IsModifiedMixin());
    }
}
