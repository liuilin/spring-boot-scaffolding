package com.liumulin.services;

import com.liumulin.daos.ConfigDao;
import com.liumulin.daos.ConfigMapper;
import com.liumulin.entity.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.liumulin.common.utils.CheckUtil.*;

/**
 * 处理配置项的服务类
 *
 * @author liuqiang
 */
@Service
@Slf4j
public class ConfigService {


    private final ConfigDao dao;
    @Autowired
    private ConfigMapper configMapper;

    public ConfigService(ConfigDao configDao) {
        this.dao = configDao;
    }

    public Collection<Config> getAll() {
        // 校验通过后打印重要的日志
        log.info("getAll start ...");
        log.info("getAll end, data size:{}", 0);
        return null;
    }

    public long add(Config config) {
        // 参数校验
        notNull(config, "param.is.null");
        notEmpty(config.getName(), "name.is.null");
        notEmpty(config.getValue(), "value.is.null");

        // 校验通过后打印重要的日志
        log.info("add com.liumulin.config:" + config);

        dao.insert(config);

        // 插入对象后，得到新对象id
        long newId = config.getId();

        // 修改操作需要打印操作结果
        log.info("add com.liumulin.config success, id:{}", newId);

        return newId;
    }

    public boolean delete(int id) {
        // 参数校验
        check(id > 0, "id.error", id);

        boolean result = dao.deleteByPrimaryKey(id) > 0;

        // 修改操作需要打印操作结果
        log.info("delete com.liumulin.config success, id: {}, result: {}", id, result);

        return result;
    }

    /**
     * 打印日志示例代码
     */
    public boolean someOpration(long id) {
        // XXX 示例代码
        int opType = getSomeFlag();

        // 校验通过后打印重要的日志
        log.info("someOpration, id: {}, opType: {}", id, opType);

        boolean result = false;

        if (opType == 1) {
            // 做这些事情
        } else {
            // 做那些事情
        }

        // 修改操作需要打印操作结果
        log.info("some Opration success, id: {}, result: {}", id, result);

        return result; // 示例代码
    }

    private int getSomeFlag() {
        return 2;
    }

    public void update(Config config) {

    }

    public long count() {
        return this.dao.count();
    }
}
