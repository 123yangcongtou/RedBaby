package bwie.com.redbaby.Utils;

import java.util.List;

/**
 * Created by lishaocong on 2016/11/12.
 */
public class Bean {

    public String message;
    public int status;

    public List<RsBean> rs;

    public static class RsBean {
        public String attriCf;
        public String bigPicture;
        public String categoryGoto;
        public int clickCount;
        public String description;
        public String dirName;
        public String gotoApp;
        public String gotoWap;
        public int id;
        public String ifShowShoppingCart;
        public String imgApp;
        public String imgWap;
        public int level;
        public int parentId;
        public String pcCi;
        public String seoCf;
        public int sort;
        public List<?> advts;
        public boolean isChecked;

        public List<ChildrenBean> children;

        public static class ChildrenBean {
            public String attriCf;
            public String bigPicture;
            public String categoryGoto;
            public int clickCount;
            public String description;
            public String dirName;
            public String gotoApp;
            public String gotoWap;
            public int id;
            public String ifShowShoppingCart;
            public String imgApp;
            public String imgWap;
            public int level;
            public int parentId;
            public String pcCi;
            public String seoCf;
            public int sort;
            public List<?> advts;
            public boolean isHeader;


            public List<ChildrenBean> children;

        }
    }
}
