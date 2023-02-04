package com.ihrm.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class MultiInsertUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final int defaultInsertCapacity = 1000;

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

//    private ExecutorService pool = Executors.newFixedThreadPool(8);

    private final String errorKey = "insertBatch:error";

    private final long defaultPageSize = 10;

    /**
     * @param mapperClazz  反射插入数据库类class
     * @param model        entity类class
     * @param method       插入方法
     * @param list         插入集合
     * @param businessName 标识
     * @return
     * @throws Exception
     */
    // @Transactional(rollbackFor = Exception.class)
    public boolean insertDataSingle(Class mapperClazz,
                                    Class model,
                                    String method,
                                    List list,
                                    String businessName) {
        if (CollectionUtils.isEmpty(list)) {
            return true;
        }
        begin();
        Object bean = applicationContext.getBean(mapperClazz);
        Method insert = getMethod(mapperClazz, method);
        List<List<Object>> insertData = getInsertData(list, defaultInsertCapacity);
        //插入数据
        for (int i = 0; i < insertData.size(); i++) {
            int finalI = i;
            List<Object> insertList = insertData.get(finalI);
            try {
                insert.invoke(bean, insertList);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            } catch (InvocationTargetException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        }
        end();
        return true;
    }

    //capacity 个一份list
    private List<List<Object>> getInsertData(List list, int capacity) {
        int n = list.size();
        List<List<Object>> result = new ArrayList<>();
        for (int i = 0; i < (n + capacity - 1) / capacity; i++)
            result.add(new LinkedList<>());
        for (int i = 0; i < list.size(); i++) {
            int index = i / capacity;
            result.get(index).add(list.get(i));
        }
        return result;
    }


    private void begin() {
        threadLocal.set(System.currentTimeMillis());
    }

    private void end() {
        Long begin = threadLocal.get();
        long time = System.currentTimeMillis() - begin;
        System.out.println("批量插入用时:" + time);
    }

    private Method getMethod(Class clazz, String method) {
        Method[] methods = clazz.getMethods();
        for (Method mth : methods) {
            if (mth.getName().equals(method) && mth.getParameterTypes().length == 1){
                return mth;
            }
        }
        throw new RuntimeException("方法名错误,请检查");
    }

    //通过类获取bean
    public <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    //通过name获取bean
    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    //通过类和name获取bean
    public <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
