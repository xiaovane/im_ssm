package admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import admin.entity.InfoType;
import admin.entity.UserAdmin;

import java.util.List;

/**
 * Created by xp on 2017/4/14.
 *
 * @author whai
 * @Description 类别dao
 */
@Repository
public interface InfoTypeDao {

    /**
     * 添加类别信息
     *
     * @param InfoType
     * @return
     */
    Integer addInfoType(InfoType InfoType);

    /**
     * 删除类别信息
     *
     * @param id
     * @return
     */
    Integer deleteInfoType(Integer id);

    /**
     * 更新类别信息
     *
     * @param InfoType
     * @return
     */
    Integer updateInfoType(InfoType InfoType);

    /**
     * 根据id查询类别信息
     *
     * @param id
     * @return
     */
    InfoType getById(Integer id);

    /**
     * 分页查询类别信息
     *
     * @param start
     * @param end
     * @return
     */
    List<InfoType> listByPage(@Param("start") Integer start, @Param("end") Integer end);

    /**
     * 查询总记录数
     *
     * @return
     */
    Long getTotal();

    /**
     * 获取类别信息
     */
    public List<InfoType> getInfoTypeData();
}
