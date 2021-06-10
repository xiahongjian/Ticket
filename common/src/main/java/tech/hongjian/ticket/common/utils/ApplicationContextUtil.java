package tech.hongjian.ticket.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by xiahongjian on 2021/6/10.
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext appCtx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }

    public static <T> T getBean(Class<T> clazz) {
        return appCtx.getBean(clazz);
    }
}
