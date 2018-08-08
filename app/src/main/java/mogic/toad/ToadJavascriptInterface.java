package mogic.toad;

import android.webkit.JavascriptInterface;

public class ToadJavascriptInterface {
    public static class BaseObject {
        protected ToadWebActivity mActivity;

        public BaseObject(ToadWebActivity activity) {
            mActivity = activity;
        }

        public boolean securityCheck() {
            return mActivity.isTrustedPage();
        }
    }

    public static class UrlBlacklist extends BaseObject {
        public UrlBlacklist(ToadWebActivity activity) {
            super(activity);
        }

        @JavascriptInterface
        public boolean intercept(String url) {
            if (!securityCheck())
                return false;
            return ToadUrlInterceptor.intercept(url);
        }

        @JavascriptInterface
        public boolean contain(String key) {
            if (!securityCheck())
                return false;
            return ToadUrlInterceptor.contain(key);
        }

        @JavascriptInterface
        public void add(String key) {
            if (!securityCheck())
                return;
            ToadUrlInterceptor.add(key);
        }

        @JavascriptInterface
        public int size() {
            if (!securityCheck())
                return 0;
            return ToadUrlInterceptor.size();
        }

        @JavascriptInterface
        public void remove(String key) {
            if (!securityCheck())
                return;
            ToadUrlInterceptor.remove(key);
        }

        @JavascriptInterface
        public void removeUrls(String url) {
            if (!securityCheck())
                return;
            ToadUrlInterceptor.removeMatched(url);
        }

        @JavascriptInterface
        public void clear() {
            if (!securityCheck())
                return;
            ToadUrlInterceptor.clear();
        }
    }

    public static class Hosts extends BaseObject {
        public Hosts(ToadWebActivity activity) {
            super(activity);
        }

        @JavascriptInterface
        public String lookup(String host) {
            if (!securityCheck())
                return null;
            return ToadHosts.lookup(host);
        }

        @JavascriptInterface
        public String get(String key) {
            if (!securityCheck())
                return null;
            return ToadHosts.get(key);
        }

        @JavascriptInterface
        public void set(String key, String value) {
            if (!securityCheck())
                return;
            ToadHosts.set(key, value);
        }

        @JavascriptInterface
        public int size() {
            if (!securityCheck())
                return 0;
            return ToadHosts.size();
        }

        @JavascriptInterface
        public void remove(String key) {
            if (!securityCheck())
                return;
            ToadHosts.remove(key);
        }

        @JavascriptInterface
        public void removeHosts(String host) {
            if (!securityCheck())
                return;
            ToadHosts.removeMatched(host);
        }

        @JavascriptInterface
        public void clear() {
            if (!securityCheck())
                return;
            ToadHosts.clear();
        }
    }

    public static class Menu extends BaseObject {
        public Menu(ToadWebActivity activity) {
            super(activity);
        }

        @JavascriptInterface
        public void showItem(int id) {
            if (!securityCheck())
                return;
            mActivity.setCustomMenuItemVisible(id, true);
        }

        @JavascriptInterface
        public void hideItem(int id) {
            if (!securityCheck())
                return;
            mActivity.setCustomMenuItemVisible(id, false);
        }

        @JavascriptInterface
        public void setItem(int id, String text, String url) {
            if (!securityCheck())
                return;
            mActivity.setCustomMenuItem(id, text, url);
        }

        @JavascriptInterface
        public void displayItem(int id, String text, String url) {
            if (!securityCheck())
                return;
            mActivity.displayCustomMenuItem(id, text, url);
        }
    }
}
