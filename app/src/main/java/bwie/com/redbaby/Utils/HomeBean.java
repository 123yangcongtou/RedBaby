package bwie.com.redbaby.Utils;

import java.util.List;

/**
 * Created by lishaocong on 2016/11/15.
 */
public class HomeBean {


    public String api;
    public String code;
    public String msg;
    public String v;
    public int version;

    public List<DataBean> data;

    public static class DataBean {
        public int elementShowNumber;
        public int elementType;
        public String modelFullCode;
        public int modelFullId;
        public int modelId;
        public int pmodelFullId;
        public int sequence;


        public List<TagBean> tag;

        public static class TagBean {
            public String businessType;
            public String color;
            public String elementDesc;
            public String elementName;
            public int elementType;
            public int linkType;
            public String linkUrl;
            public int modelFullId;
            public String picUrl;
            public String productSpecialFlag;
            public int sequence;
            public int templateFullId;
            public String trickPoint;
        }
    }
}
