package com.zgh.app;

import com.zgh.app.bean.PublishInfo;
import com.zgh.app.data.DataUtil;

public class Test2 {
    public static void main(String[] args) throws Exception {
    //    DataUtil.toBase64(DataMock.apkInfos);
        new AppPublishFrame(DataMock.publishInfo);
    }
}
