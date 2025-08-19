package com.fmfiroz.ftpplayer;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class FTPHelper {
    private FTPClient ftpClient;

    public boolean connect(String host, int port) {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            return ftpClient.login("anonymous", "");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] listFiles(String path) {
        try {
            return ftpClient.listNames(path);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public void disconnect() {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
