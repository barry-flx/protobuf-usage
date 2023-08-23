package com.flx.jsch;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.Session;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class JschTest {

    @Test
    public void testPublicKey() {
        Session session = getSessionPubKey();
        // sftp
        String srcFile = "#";
        String distPath = "#";
        testSftp(session, srcFile, distPath);

        // cmd
        String result = testCmd(session, "sh ./test/test.sh");
        System.out.println("key result:" + result);
    }

    @Test
    public void testPwd() {
        Session session = getSessionPwd();
        // sftp
        String srcFile = "#";
        String distPath = "#";
        testSftp(session, srcFile, distPath);

        // cmd
        String result = testCmd(session, "sudo sh ./test/test.sh");
        System.out.println("pwd result:" + result);
    }

    private void testSftp(Session session, String srcFile, String destPath) {
        Sftp sftp = JschUtil.createSftp(session);
        if (!sftp.exist(destPath)) {
            sftp.mkDirs(destPath);
        }
        sftp.put(srcFile, destPath);
    }

    private String testCmd(Session session, String cmd) {
        return JschUtil.exec(session, cmd, StandardCharsets.UTF_8);
    }

    private Session getSessionPwd() {
        String host = "#";
        String user = "#";
        String password = "#";
        return JschUtil.getSession(host, 22, user, password);
    }

    private Session getSessionPubKey() {
        String host = "#";
        String user = "#";
        int port = 22;
        String pubKey = "#";
        return JschUtil.getSession(host, port, user, pubKey, null);
    }

}
