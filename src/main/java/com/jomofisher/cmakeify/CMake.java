package com.jomofisher.cmakeify;

import java.util.HashMap;
import java.util.Map;

public class CMake {
    final public String[] versions;
    final public Map<String, Remote> remotes;
    CMake() {
        versions = new String[] { "3.7.2" };
        remotes = new HashMap<>();
        remotes.put("3.7.1", remote(3, 7, 1));
        remotes.put("3.7.2", remote(3, 7, 2));
    }

    private static String productionPath(int major, int minor, int point, String os) {
          return String.format("http://cmake.org/files/v%s.%s/cmake-%s.%s.%s-%s.tar.gz",
                major, minor, major, minor, point, os);
    }

    private static String linuxPath(int major, int minor,int point) {
        return productionPath(major, minor, point, "Linux-x86_64");
    }

    private static String darwinPath(int major, int minor,int point) {
        return productionPath(major, minor, point, "Darwin-x86_64");
    }

    private Remote remote(int major, int minor,int point) {
        return new Remote(
                linuxPath(major, minor, point),
                linuxPath(major, minor, point),
                linuxPath(major, minor, point),
                darwinPath(major, minor, point));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    versions: [");
        for (int j = 0; j < versions.length; ++j) {
            if (j != 0) {
                sb.append(", ");
            }
            sb.append(versions[j]);
        }
        sb.append("]\n");
        if (remotes != null) {
            sb.append("    remotes:\n");
            for (String key : remotes.keySet()) {
                Remote remote = remotes.get(key);
                sb.append("      " + key + ":\n");
                sb.append("        linux: " + remote.linux + "\n");
                sb.append("        win32: " + remote.win32 + "\n");
                sb.append("        win64: " + remote.win64 + "\n");
                sb.append("        darwin: " + remote.darwin + "\n");
            }
        }
        return sb.toString();
    }
}
