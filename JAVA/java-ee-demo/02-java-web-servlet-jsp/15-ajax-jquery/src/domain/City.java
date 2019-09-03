package domain;

public class City
    {
        private int cityId;
        private String cityName;
        private int provinceid;

        public int getCityID() {
            return cityId;
        }

        public void setCityID(int cityId) {
            this.cityId = cityId;
        }

        public String getName() {
            return cityName;
        }

        public void setName(String cityName) {
            this.cityName = cityName;
        }

        public int getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(int provinceid) {
            this.provinceid = provinceid;
        }

    }
