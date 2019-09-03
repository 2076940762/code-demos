package domain;

import java.util.List;

public class PageBean<T>
    {
        private List<T> list; // 当前页内容 查询
        private int currPage; // 当前页码 传递
        private static int pageSize = 2; // 每页显示的条数 固定
        private int totalCount; // 总条数 查询
//        private int totalPage;// 总页数 计算

        public List<T> getList() {
            return list;
        }

        public PageBean(List<T> list, int currPage, int totalCount) {
            super();
            this.list = list;
            this.currPage = currPage;
            this.totalCount = totalCount;
        }

        public void setList(List<T> list) {
            this.list = list;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public static int getPageSize() {
            return pageSize;
        }

        public static void setPageSize(int pageSize) {
            PageBean.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            int totalPages = totalCount / pageSize;

            if (totalCount % pageSize > 0) {
                totalPages++;
            }
            totalPages--;
            return totalPages;
        }

    }
