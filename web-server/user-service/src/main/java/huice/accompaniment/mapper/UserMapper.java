package huice.accompaniment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huice.accompaniment.pojo.DO.PatientDO;
import huice.accompaniment.pojo.DO.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author welsir
 * @Date 2024/6/3 11:28
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    @Select("update hc_patient set 'default_patient' = 'false' where uid = #{id} and 'default_patient' = true")
    int updateUserDefaultPatient(String uid);

    @Insert("insert into hc_patient (uid, name, phoneNumber, relative, documentType, document, location, defaultPatient) " +
            "values(#{uid}, #{name}, #{phoneNumber}, #{relative}, #{documentType}, #{document}, #{location}, #{defaultPatient})")
    int insertOnePatient(PatientDO patientDO);
}
