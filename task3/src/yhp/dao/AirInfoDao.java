package yhp.dao;

import yhp.bean.AirInfo;

import java.util.List;

public interface AirInfoDao {

    //1。查询信息
    public List<AirInfo> findAll();

    //2。根据时间查
    public List<AirInfo> findByTime(String time);

    //3。根据目的查
    public List<AirInfo> findByAddress(String address);

    //4。删除
    public int delete(String number);

    //5。更新
    public void update(AirInfo aitInfo, String time);

    //6。查询编号是否存在
    public AirInfo findById(String number);
}
