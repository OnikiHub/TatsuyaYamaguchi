package jp.ac.asojuku.tatsuyayamaguchi;

import android.graphics.Bitmap;


    public class User {
        private Bitmap icon;
        private String user;
        private String comment;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }
    }
