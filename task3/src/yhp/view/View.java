package yhp.view;

import yhp.bean.AirInfo;
import yhp.dao.AirInfoDao;
import yhp.dao.impl.AirInfoDaoImpl;
import yhp.exception.OutOfRangeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    public void menu() {
        int num = -1;
        AirInfoDao infoDao = new AirInfoDaoImpl();
        List<AirInfo> infoList;
        while (true) {
            System.out.println("****************欢迎使用航班信息管理系统****************");
            System.out.println("请选择操作（1.列出所有航班，2.按起飞时间查询，3.按目的地查询，4.删除航班，5.更新航班,6.离开系统）");
            String userInput = getUserInput();
            try {
                num = judgeInput(userInput, 1, 6);
                break;
            } catch (OutOfRangeException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }
        }
        if (num == 1) {
            infoList = infoDao.findAll();
            System.out.println("编号\t航班号\t目的地\t起飞时间");
            for (AirInfo air : infoList) {
                System.out.println(air.getAirid() + "\t" + air.getAirnumber() + "\t" + air.getAddress() + "\t" + air.getAirdate());
            }
            menu();

        } else if (num == 2) {
            boolean result = true;
            String input = "";
            while (result) {
                System.out.println("请输入日期，格式\" yyyy-mm-dd\"");
                input = getUserInput();
                result = isValidDate(input);
            }
            infoList = infoDao.findByTime(input);
            printResult(infoList);
            menu();

        } else if (num == 3) {
            String input = "";
            System.out.println("目的地");
            input = getUserInput();
            infoList = infoDao.findByAddress(input);
            printResult(infoList);
            menu();

        } else if (num == 4) {
            System.out.println("请输入航班号：");
            String input = getUserInput();
            int result = infoDao.delete(input);
            System.out.println(result > 0 ? "删除成功" : "无该航班");
            menu();

        } else if (num == 5) {
            String date = null;
            String number;
            String address;
            boolean result = true;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);

            System.out.println("请输入编号：");
            String input = getUserInput();

            AirInfo air = infoDao.findById(input);
            if (air == null) {
                System.out.println("无该航班");
            } else {
                System.out.println("请输入新的航班号");
                number = getUserInput();
                System.out.println("请输入新的地址");
                address = getUserInput();
                while (result) {
                    System.out.println("请输入新的日期,格式\"yyyy-MM-dd\"");
                    date = getUserInput();
                    result = isValidDate(date);
                }
                air.setAirnumber(number);
                air.setAddress(address);
                try {
                    air.setAirdate(dateFormat.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                infoDao.update(air,date);
                System.out.println("更新成功！");
            }
            menu();
        } else {
            System.out.println("谢谢使用");
            System.exit(0);
        }

    }

    //获取用户输入
    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    //判断输入是否正确
    private int judgeInput(String input, int begin, int end) throws NumberFormatException, OutOfRangeException {
        try {
            int num = Integer.valueOf(input);
            if (num < begin || num > end) {
                throw new OutOfRangeException("输入正确数字");
            }
            return num;

        } catch (NumberFormatException e) {
            throw new NumberFormatException("输入数字");
        }
    }

    //判断时间
    private boolean isValidDate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException pe) {
            System.err.println("Wrong date format!");
            return true;
        }
        return false;
    }

    //打印内容
    private void printResult(List<AirInfo> infoList) {
        if (infoList.isEmpty()) {
            System.out.println("没有该航班");
        } else {
            System.out.println("编号\t航班号\t目的地\t起飞时间");
            for (AirInfo air : infoList) {
                System.out.println(air.getAirid() + "\t" + air.getAirnumber() + "\t" + air.getAddress() + "\t" + air.getAirdate());
            }
        }
    }

}
