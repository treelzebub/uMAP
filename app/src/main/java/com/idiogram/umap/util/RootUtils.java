package com.idiogram.umap.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.idiogram.umap.util.RootUtils.ExecShell.SHELL_CMD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/** @author Kevin Kowalewski */
public class RootUtils {

    public static final String TAG = "RootUtils";

    public static boolean isDeviceRooted(Context c) {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3() || checkRootMethod4(c);
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        try {
            File file = new File("/system/app/Superuser.apk");
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkRootMethod3() {
        return new ExecShell().executeCommand(SHELL_CMD.check_su_binary)!=null;
    }

    private static boolean checkRootMethod4(Context context) {
        return isPackageInstalled("eu.chainfire.supersu", context);
    }

    private static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    static class ExecShell {

        private static String LOG_TAG = ExecShell.class.getName();

        public static enum SHELL_CMD {
            check_su_binary(new String[] { "/system/xbin/which", "su" });

            String[] command;

            SHELL_CMD(String[] command) {
                this.command = command;
            }
        }

        public ArrayList<String> executeCommand(SHELL_CMD shellCmd) {
            String line = null;
            ArrayList<String> fullResponse = new ArrayList<String>();
            Process localProcess = null;
            try {
                localProcess = Runtime.getRuntime().exec(shellCmd.command);
            } catch (Exception e) {
                return null;
            }
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    localProcess.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    localProcess.getInputStream()));
            try {
                while ((line = in.readLine()) != null) {
                    Log.d(LOG_TAG, "--> Line received: " + line);
                    fullResponse.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(LOG_TAG, "--> Full response was: " + fullResponse);
            return fullResponse;
        }
    }
}
