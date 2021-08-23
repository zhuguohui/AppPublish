package com.zgh.app;

import com.google.gson.reflect.TypeToken;
import com.zgh.app.bean.ApkInfo;
import com.zgh.app.bean.PublishInfo;
import com.zgh.app.data.DataUtil;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        //  ApkBuildFrame frame=new ApkBuildFrame(DataMock.apkInfos);
//        AppPublishFrame frame=new AppPublishFrame(DataMock.publishInfo);

        if (args == null || args.length < 2) {
            System.err.println("参数不全");
            System.exit(-1);
            return;
        }
        String action = args[0];
        if ("build".equals(action)) {

            List<ApkInfo> apkInfos = DataUtil.getData(args[1],new TypeToken<List<ApkInfo>>(){}.getType());
            new ApkBuildFrame(apkInfos);

        } else if ("publish".equals(action)) {
            PublishInfo data = DataUtil.getData(args[1], PublishInfo.class);
            new AppPublishFrame(data);
        } else {
            System.err.println("不支持的action action=" + action);
            System.exit(-1);
            return;
        }


    }


}
