package yhp.dao.impl;

import yhp.bean.AirInfo;
import yhp.dao.AirInfoDao;
import yhp.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirInfoDaoImpl extends DBUtils implements AirInfoDao {

    @Override
    public List<AirInfo> findAll() {
        ArrayList arrayList = new ArrayList<AirInfo>();
        String sql = "select * from airinfo;";
        resultSet = query(sql,null);
        try {
            while(resultSet.next()){
                AirInfo air = new AirInfo();
                air.setAirid(resultSet.getInt("airid"));
                air.setAirnumber(resultSet.getString("airnumber"));
                air.setAddress(resultSet.getString("address"));
                air.setAirdate(resultSet.getDate("airdate"));
                arrayList.add(air);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return arrayList;
    }

    @Override
    public List<AirInfo> findByTime(String time) {
        ArrayList arrayList = new ArrayList<AirInfo>();
        try {
            String sql = "select * from airinfo where airdate = ?;";
            ArrayList params = new ArrayList<>();
            params.add(time);
            resultSet = query(sql,params);
            while(resultSet.next()){
                AirInfo air = new AirInfo();
                air.setAirid(resultSet.getInt("airid"));
                air.setAirnumber(resultSet.getString("airnumber"));
                air.setAddress(resultSet.getString("address"));
                air.setAirdate(resultSet.getDate("airdate"));
                arrayList.add(air);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return arrayList;
    }

    @Override
    public List<AirInfo> findByAddress(String address) {
        ArrayList arrayList = new ArrayList<AirInfo>();
        try {
            String sql = "select * from airinfo where address = ?;";
            ArrayList params = new ArrayList<>();
            params.add(address);
            resultSet = query(sql,params);
            while(resultSet.next()){
                AirInfo air = new AirInfo();
                air.setAirid(resultSet.getInt("airid"));
                air.setAirnumber(resultSet.getString("airnumber"));
                air.setAddress(resultSet.getString("address"));
                air.setAirdate(resultSet.getDate("airdate"));
                arrayList.add(air);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return arrayList;
    }

    @Override
    public int delete(String number) {
        int update = 0;
        try {
            String sql = "delete from airinfo where airnumber = ?;";
            ArrayList params = new ArrayList<>();
            params.add(number);
            update = update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return update;
    }

    @Override
    public void update(AirInfo airInfo, String time) {
        try {
            String sql = "update airinfo set airnumber = ?, address = ?, airdate = ? where airid = ?;";
            ArrayList params = new ArrayList<>();
            params.add(airInfo.getAirnumber());
            params.add(airInfo.getAddress());
            params.add(time);
            params.add(airInfo.getAirid());
            update(sql,params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }

    @Override
    public AirInfo findById(String id) {
      AirInfo air = null;
        try {
            String sql = "select * from airinfo where airid = ?";
            ArrayList params = new ArrayList<>();
            params.add(id);
            resultSet = query(sql,params);
            while(resultSet.next()){
                air = new AirInfo();
                air.setAirid(resultSet.getInt("airid"));
                air.setAirnumber(resultSet.getString("airnumber"));
                air.setAddress(resultSet.getString("address"));
                air.setAirdate(resultSet.getDate("airdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return air;
    }
}
