package com.tsrs.webedi.modular.system.service;

/**
 * 字典服务
 *
 * @author Tsrs-WebEDI
 * @date 2017-04-27 17:00
 */
public interface IDictService {

    /**
     * 新規字典
     *
     * @author Tsrs-WebEDI
     * @Date 2017/4/27 17:01
     */
    void addDict(String dictName, String dictValues);

    /**
     * 编辑字典
     *
     * @author Tsrs-WebEDI
     * @Date 2017/4/28 11:01
     */
    void editDict(Integer dictId, String dictName, String dicts);

    /**
     * 削除字典
     *
     * @author Tsrs-WebEDI
     * @Date 2017/4/28 11:39
     */
    void delteDict(Integer dictId);

}
